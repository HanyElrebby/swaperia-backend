package com.swaperia.model;

import com.swaperia.config.DeliveryConstants;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "delivery", schema = "public")
public class Delivery {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	private DeliveryConstants type;
	
	@Column(name = "description")
	private String description;
	
	
}
