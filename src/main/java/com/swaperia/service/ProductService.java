package com.swaperia.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.swaperia.model.Product;
import com.swaperia.repository.ProductRepository;
import com.swaperia.service.dto.ProductDTO;

@Service
public class ProductService {
	private ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public Product saveProduct(ProductDTO productDTO, MultipartFile image) {
		Product product = new Product();
		product.setName(productDTO.getName());
		try {
			product.setImage(ImageUtil.compressImage(image.getBytes()));
		} catch (Exception e) {
			// TODO: handle exception
		}

		product.setDescription(productDTO.getDescription());
		product.setValue(productDTO.getValue());
		//product.setUser(user);
		
		return product;
	}
	
	public boolean uplaodProductImage(MultipartFile image) {
		boolean isUpload = false;
		try {
			
			isUpload = true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return isUpload;
	}
}
