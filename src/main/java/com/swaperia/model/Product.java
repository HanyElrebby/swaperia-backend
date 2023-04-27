package com.swaperia.model;

import java.math.BigDecimal;
import java.util.Arrays;
import org.hibernate.annotations.Type;

import org.springframework.security.crypto.encrypt.BytesEncryptor;

import com.swaperia.config.ConditionConstants;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product", schema = "public")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column
	private String description;
	
	@Column(name = "value")
	private BigDecimal value;

	@Column(name = "image")
	private byte[] image;
		
	@Column(name = "condition")
	private String condition;
	
	@ManyToOne
	@JoinColumn(name = "owner_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "category_name")
	private Category category;

	@Column(name = "delivery")
	private String delivery;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
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

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getDelivery() {
		return delivery;
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", value=" + value + ", image="
				+ Arrays.toString(image) + ", condition=" + condition + ", user=" + user + ", category=" + category
				+ ", delivery=" + delivery + "]";
	}
	
	
	
}
