package com.swaperia.rest;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.swaperia.model.Category;
import com.swaperia.model.Product;
import com.swaperia.repository.CategoryRepository;
import com.swaperia.service.ProductService;
import com.swaperia.service.dto.ProductDTO;

import jakarta.validation.Valid;

import org.springframework.ui.Model;

@RestController
@RequestMapping("/api")
public class ProductResource {
	private ProductService productService;
	private CategoryRepository categoryRepository;
	
	public ProductResource(ProductService productService, CategoryRepository categoryRepository) {
		this.productService = productService;
		this.categoryRepository = categoryRepository;
	}


	@GetMapping("/product-form")
	public ModelAndView productForm(Principal principal) {
		ModelAndView model = new ModelAndView("add-product");
		List<Category> categories = categoryRepository.findAll();
        model.addObject("categories", categories);
        model.addObject("product", new ProductDTO());
		return model;
	}
	
	
	@PostMapping("/product")
	public ResponseEntity<Product> createProduct(@Valid @ModelAttribute ProductDTO productDTO,
			@RequestParam("image") MultipartFile image) {
		Product body =  productService.saveProduct(productDTO, image);
		return ResponseEntity.status(HttpStatus.OK).body(body);
	}
	
	
}
