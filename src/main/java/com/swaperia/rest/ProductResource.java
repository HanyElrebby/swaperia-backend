package com.swaperia.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.swaperia.model.Product;
import com.swaperia.service.ProductService;
import com.swaperia.service.dto.ProductDTO;

@RestController
@RequestMapping("/api")
public class ProductResource {
	private ProductService productService;

	public ProductResource(ProductService productService) {
		this.productService = productService;
	}
	
	public ResponseEntity<Product> createProduct(@RequestBody ProductDTO productDTO,
			@RequestParam MultipartFile image) {
		Product body =  productService.saveProduct(productDTO, image);
		return ResponseEntity.status(HttpStatus.OK).body(body);
	}
	
}
