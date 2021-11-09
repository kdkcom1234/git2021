package com.example.commerce.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.commerce.order.request.CommerceOrderRequest;
import com.example.commerce.product.Product;

@Service
public class CommerceOrderService {

    private CommerceOrderRepository orderRepo;
    private CommerceOrderDetailRepository detailRepo;

    private RabbitTemplate rabbit;

    @Autowired
    public CommerceOrderService(CommerceOrderRepository orderRepo, CommerceOrderDetailRepository detailRepo, RabbitTemplate rabbit){
        this.orderRepo = orderRepo;
        this.detailRepo = detailRepo;
        this.rabbit = rabbit;
    }

    // 예외처리가 발생하면 rollback 함
    @Transactional(rollbackOn = Exception.class)
    public CommerceOrder saveOrder(CommerceOrderRequest reqOrder) {
        // 금액 합산
        long total = 0;
        for(CommerceOrderRequest.CommerceOrderDetail reqDetail : reqOrder.getDetails()){
            total += reqDetail.getQuantity() * reqDetail.getUnitPrice();
        }

        // 요청객체 -> 엔티티 객체로 변환
        CommerceOrder toSaveOrder = CommerceOrder.builder()
                .name(reqOrder.getName())
                .address(reqOrder.getAddress())
                .date(reqOrder.getDate())
                .totalAmount(total)
                .status("00") // 주문 금액 합계
                .createdTime(new Date().getTime()) // 처리상태 예) 주문 요청: 00
                .build();

        // 주문 정보 저장
        CommerceOrder savedOrder = orderRepo.save(toSaveOrder);

        // 주문 상세정보 id 설정
        List<CommerceOrderDetail> toSaveDetails = new ArrayList<CommerceOrderDetail>();
        for(CommerceOrderRequest.CommerceOrderDetail reqDetail : reqOrder.getDetails()){
            CommerceOrderDetail detail = CommerceOrderDetail.builder()
                    .commerceOrderId(savedOrder.getId())            // 상위 레코드의 id값
                    .seq(reqOrder.getDetails().indexOf(reqDetail) + 1)  // 주문 제품 순번
                    .product(Product.builder().id(reqDetail.getProductId()).build()) // 주문 제품
                    .productName(reqDetail.getProductName())
                    .unitPrice(reqDetail.getUnitPrice())
                    .quantity(reqDetail.getQuantity())
                    .build();

            toSaveDetails.add(detail);
        }

        // 주문 상세 정보 저장
        List<CommerceOrderDetail> savedOrderDetails = detailRepo.saveAll(toSaveDetails);

        savedOrder.setDetails(savedOrderDetails);

        return savedOrder;
    }
    
    public void sendOrder(CommerceOrder order) {
        // 메시지큐 보내기
        // rabbit.convertAndSend("commerce.order.request", order);
    }
	
}
