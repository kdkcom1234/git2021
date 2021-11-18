package com.example.sales.product;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	private RabbitTemplate rabbit;

	private ProductService(RabbitTemplate rabbit) {
		this.rabbit = rabbit;
	}

	public void sendProduct(Product product) {
		System.out.println(product);
		rabbit.convertAndSend("sales.product.create", product);
	}
}
