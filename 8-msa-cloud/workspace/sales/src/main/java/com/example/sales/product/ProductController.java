package com.example.sales.product;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.sales.Result;

@RestController
public class ProductController {
	private ProductService service;

	public ProductController(ProductService service) {
		this.service = service;
	}

	@PostMapping(value = "/products")
	public ProductCreateResponse addProduct(@RequestBody ProductCreateRequest productRequest) {

		// 데이터 검증

		// DB에 저장할 객체 생성
		Product product = Product.builder().id(1).name(productRequest.getName()).code("P0001")
				.unitPrice(productRequest.getUnitPrice()).build();

		// DB에 저장
		// repo.save(product)

		// (event)외부 시스템에 추가된 데이터 보내기
		service.sendProduct(product);

		return ProductCreateResponse.builder().product(product).result(new Result("00", "정상적으로 생성")).build();
	}
}
