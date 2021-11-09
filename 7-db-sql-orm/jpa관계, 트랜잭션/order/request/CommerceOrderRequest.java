package com.example.commerce.order.request;

import com.example.commerce.product.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
public class CommerceOrderRequest {

    private String name;	// 주문자명
    private String address;	// 주소
    private String date;	// 주문일자
    private List<CommerceOrderDetail> details;

    @Data
    @NoArgsConstructor
    public static class CommerceOrderDetail {
        private int productId;

        // 주문당시에 정보로 기록한다.
        private String productName;	// 제품명
        private int unitPrice;	// 단가
        private int quantity;	// 수량
    }
}
