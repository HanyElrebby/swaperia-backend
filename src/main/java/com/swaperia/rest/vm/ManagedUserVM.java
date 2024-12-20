package com.swaperia.rest.vm;

import com.swaperia.service.dto.UserDTO;

import jakarta.validation.constraints.Size;

public class ManagedUserVM extends UserDTO {
	public static final int PASSWORD_MIN_LENGTH = 6;

    public static final int PASSWORD_MAX_LENGTH = 100;
    
    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
    private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "ManagedUserVM [password=" + password + "]";
	}
}
