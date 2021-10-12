package com.example.commerce.product;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.example.commerce.product.event.SalesProduct;

@Service
public class ProductService {

	@RabbitListener(queues = "sales.product.create")
	public void receiveSalesProduct(SalesProduct salesProduct) {
		System.out.println(salesProduct);

		saveProduct(salesProduct);
	}

	public Product saveProduct(SalesProduct salesProduct) {
		Product product = Product.builder().id(1).productCode(salesProduct.getCode())
				.productName(salesProduct.getName()).salesProductId(salesProduct.getId()).build();

		// repo.save(product);

		return product;
	}
}
