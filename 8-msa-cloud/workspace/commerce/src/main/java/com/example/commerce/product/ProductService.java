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
	@CacheEvict(key="products", allEntries = true)
	@RabbitListener(queues = "sales.product.create")
	public void receiveSalesProduct(SalesProduct salesProduct) {
		System.out.println(salesProduct);
		saveProduct(salesProduct);
	}

	// 미작성 부분
	public Product saveProduct(SalesProduct salesProduct) {
		Product product = Product
							.builder()
							.productCode(salesProduct.getCode())
							.productName(salesProduct.getName())
							.salesProductId(salesProduct.getId())
							.build();
		repo.save(product);

		return product;
	}
}
