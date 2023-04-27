package com.swaperia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.swaperia.config.DeliveryConstants;
import com.swaperia.model.Category;
import com.swaperia.model.Delivery;
import com.swaperia.model.Product;
import com.swaperia.model.User;
import com.swaperia.repository.CategoryRepository;
import com.swaperia.repository.DeliveryRepository;
import com.swaperia.repository.ProductRepository;
import com.swaperia.repository.UserRepository;
import com.swaperia.security.SecurityUtils;
import com.swaperia.service.dto.ProductDTO;

@Service
public class ProductService {
	private ProductRepository productRepository;
	private UserService userService;
	private DeliveryRepository deliveryRepository;
	private CategoryRepository categoryRepository;
	
	
	public ProductService(ProductRepository productRepository, UserService userService,
			DeliveryRepository deliveryRepository, CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.userService = userService;
		this.deliveryRepository = deliveryRepository;
		this.categoryRepository = categoryRepository;
	}

	public Product saveProduct(ProductDTO productDTO) {
		Product product = new Product();
		System.out.println("111111");
		product.setName(productDTO.getName());
		System.out.println("2222222222");

		product.setDescription(productDTO.getDescription());
		System.out.println("333333333");

		product.setImage(productDTO.getImage());
		System.out.println("444444444");

		product.setCondition(productDTO.getCondition());
		System.out.println("55555555555");

		product.setValue(productDTO.getValue());
		System.out.println("6666666666666");

//		Delivery delivery = deliveryRepository.findByType(productDTO.getDelivery()).orElseThrow(() -> new RuntimeException("Delivery method not found"));
//		System.out.println("7777777777777");

		product.setDelivery(productDTO.getDelivery());
		System.out.println("88888888888");

		String userEmail = SecurityUtils.getCurrentUserUsername().orElse("");
		System.out.println("999999999999"+ userEmail);

		User user = userService.getUserWithAuthorities().get();
		System.out.println("/////////////////"+ user);

		product.setUser(user);
//		System.out.println("--------------------------------"+user);
		System.out.println("--------------------------------");

		Category category = categoryRepository.findByName(productDTO.getCategory());
		System.out.println("******************************");

		product.setCategory(category);
		System.out.println("+++++++++++++++++++++++++++++");

//		System.out.println("+++++++++++++++++++++++++++++" + product);
		System.out.println("========================");

		productRepository.save(product);
		System.out.println("6564512308"
				+ "463510354");
		
		return product;
	}
	
	public Page<Product> listAllproducts() {
		return productRepository.findAll(PageRequest.of(0, 20));
	}
	
	public void deleteProduct(Product product) {
		productRepository.delete(product);
	}

}
