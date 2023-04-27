package com.swaperia.service.dto;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

import com.swaperia.config.ConditionConstants;
import com.swaperia.config.DeliveryConstants;
import com.swaperia.model.Category;
import com.swaperia.model.Product;

import jakarta.persistence.Lob;

public class ProductDTO {
	private Long id;
	private String name;
	private String description;
	private String category;
	
	private byte[] image;
	private String condition;
	private BigDecimal value;
	
	private String delivery;
	
	
	public ProductDTO() {
		
	}
	
	
	
	public ProductDTO(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.description = product.getDescription();
		this.category = product.getCategory().getName();
		this.condition = product.getCondition();
		this.value = product.getValue();
		this.delivery = product.getDelivery();
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	//	public byte[] getImage() {
//		return image;
//	}
//	public void setImage(byte[] image) {
//		this.image = image;
//	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
	
	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getDelivery() {
		return delivery;
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}

	@Override
	public String toString() {
		return "ProductDTO [id=" + id + ", name=" + name + ", description=" + description + ", category=" + category
				+ ", image=" + Arrays.toString(image) + ", condition=" + condition + ", value=" + value + ", delivery="
				+ delivery + "]";
	}
	
}
