package com.swaperia.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.swaperia.model.Product;
import com.swaperia.model.User;
import com.swaperia.repository.ProductRepository;
import com.swaperia.repository.UserRepository;
import com.swaperia.security.SecurityUtils;
import com.swaperia.service.dto.ProductDTO;

@Service
public class ProductService {
	private ProductRepository productRepository;
	private UserRepository userRepository;
	public ProductService(ProductRepository productRepository, UserRepository userRepository) {
		this.productRepository = productRepository;
		this.userRepository = userRepository;
	}
	
	public Product saveProduct(ProductDTO productDTO, MultipartFile image) {
		Product product = new Product();
		product.setName(productDTO.getName());
		try {
			product.setImage(ImageUtil.compressImage(image.getBytes()));
		} catch (Exception e) {
			System.out.println(e);
		}

		product.setDescription(productDTO.getDescription());
		product.setValue(productDTO.getValue());
		String userEmail = SecurityUtils.getCurrentUserUsername().orElse("");
		User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new UserNotFoundException("User is not found"));
		product.setUser(user);
		product.setCategory(productDTO.getCategory());
		
		return productRepository.save(product);
	}

}
