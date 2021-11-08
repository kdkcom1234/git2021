package com.example.commerce.product;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.example.commerce.product.event.SalesProduct;

@Service
public class ProductService {
	
	ProductRepository repo;
	
	public ProductService(ProductRepository repo) {
		this.repo = repo;
	}


	// 데이터가 갱신되는 시점에 캐시 삭제
	// ex) 타 시스템에서 데이터를 받아와서 저장함
	@CacheEvict(value="products", allEntries = true)
	@RabbitListener(queues = "sales.product.create")
	public void receiveSalesProduct(SalesProduct salesProduct) {
		System.out.println(salesProduct);
		saveProduct(salesProduct);
	}

	public Product saveProduct(SalesProduct salesProduct) {
		Product product = Product
							.builder()
							.category(salesProduct.getCategory())
							.productCode(salesProduct.getCode())
							.productName(salesProduct.getName())
							.salesProductId(salesProduct.getId())
							.build();
		repo.save(product);

		return product;
	}
}
