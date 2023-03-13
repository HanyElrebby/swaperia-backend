package com.swaperia.model;

import java.io.Serializable;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "authority", schema = "public")
public class Authority implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@NotNull
    @Size(max = 50)
    @Column(length = 50)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Authority [name=" + name + "]";
	}
}
