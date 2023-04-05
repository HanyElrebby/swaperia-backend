package com.swaperia.rest;

import java.net.http.HttpRequest;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swaperia.model.User;
import com.swaperia.repository.UserRepository;
import com.swaperia.rest.vm.EmailVM;
import com.swaperia.rest.vm.ManagedUserVM;
import com.swaperia.rest.vm.NewPasswordVM;
import com.swaperia.service.UserService;
import com.swaperia.service.dto.PasswordChangeDTO;
import com.swaperia.service.dto.UserDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:8081")

public class AccountResource {
	   
	private static class AccountResourceException extends RuntimeException {
		private AccountResourceException(String message) {
			super(message);
	    }
	}

	
    private final UserRepository userRepository;

    private final UserService userService;
	
	
	public AccountResource(UserRepository userRepository, UserService userService) {
		this.userRepository = userRepository;
		this.userService = userService;
	}
	
	@GetMapping("/authenticate")
	public String isAuthenticated(HttpServletRequest request) {
		System.out.println(request.getRemoteUser());
		return request.getRemoteUser();
	}
	

	@PostMapping("/register")
    public void registerAccount(@Valid @RequestBody ManagedUserVM managedUserVM) {
        userService.registerUser(managedUserVM, managedUserVM.getPassword());
        //mailService.sendActivationEmail(user);
    }
	
	@GetMapping("/account")
	public UserDTO getAccount(HttpServletRequest request) {
		System.out.println(request.getHeader("Authorization"));
		return userService.getUserWithAuthorities()
				.map(UserDTO::new)
				.orElseThrow(() -> new AccountResourceException("User could not be found"));
	}
	
	@PostMapping(path = "/account/reset-password/init")
    public String requestPasswordReset(@RequestBody EmailVM emailVM) throws Exception {
	    User user = userService.requestPasswordReset(emailVM.getEmail());
	    String response = "http://localhost:8080/reset-password?token=" + user.getResetKey();
	    return response;
    }
	
	@PostMapping(path = "/account/reset-password/finish")
    public void finishPasswordReset(@RequestParam("token")String token, @RequestBody NewPasswordVM password) throws Exception {
	    userService.completePasswordReset(password.getNewPassword(), token);
    }
	
	@PostMapping("/account/change-password")
	public void changePassword(@RequestBody PasswordChangeDTO passwordChangeDTO) {
		userService.changePassword(passwordChangeDTO.getCurrentPassword(), passwordChangeDTO.getNewPassword());
	}
	
	
}