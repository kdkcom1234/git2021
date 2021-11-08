package com.example.commerce.product;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
	
	ProductRepository repo;
	
	public ProductController(ProductRepository repo) {
		this.repo = repo;
	}

	@Cacheable(value = "products"
				// 카테고리별 앞쪽 10개의 레코드까지만 캐시함
				, condition="(#page + 1) * #size <= 10" 
				// 캐시키이름) 카테고리-페이지-사이즈  ex) all-0-10, beverage-0-5
				, key = "#category+'-'+#page+'-'+#size") 
	@GetMapping(value="/products/{category}")
	// Page객체는 캐시 생성후 조회가 안 됨(기본 생성자 사용불가)
	// Page 데이터를 응답하는 별도의 응답객체 ProductPageResponse를 만들어서 처리함	
	public ProductPageResponse getProductsByCategory(
			@PathVariable String category, 
			@RequestParam int page, 
			@RequestParam int size){
		
		if(category.equals("all")) {
			// 전체조회
			Page<Product> productsPage = repo.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
			// Page<Product> 객체를 ProductPageResponse 객체로 변환하여 리턴			
			return ProductPageResponse.builder()
					.isLast(productsPage.isLast())
					.totalElements(productsPage.getTotalElements())
					.content(productsPage.getContent())
					.build();
		} else {
			// 카테고리 필터 조회
			Page<Product> productsPage = repo.findByCategory(PageRequest.of(page, size, Sort.by("id").descending()), category);
			// Page<Product> 객체를 ProductPageResponse 객체로 변환하여 리턴
			return ProductPageResponse.builder()
					.isLast(productsPage.isLast())
					.totalElements(productsPage.getTotalElements())
					.content(productsPage.getContent())
					.build();
		}
	}
}
