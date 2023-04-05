package com.swaperia.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.swaperia.rest.vm.LoginVM;
import com.swaperia.security.UserDetailsImpl;
import com.swaperia.security.jwt.JWTFilter;
import com.swaperia.security.jwt.JwtTokenUtil;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:8081")
public class UserJWTController {
	
	@Autowired
	private AuthenticationManagerBuilder authenticationManagerBuilder;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> authorize(@Valid @RequestBody LoginVM loginVM, HttpServletRequest request) throws Exception {
        Map<String, Object> responseMap = new HashMap<>();
		try {
			System.out.println(loginVM.getPassword());
			Authentication authentication = authenticationManagerBuilder.getObject().authenticate(
					new UsernamePasswordAuthenticationToken(loginVM.getEmail(), loginVM.getPassword()));
			System.out.println();
			if (authentication.isAuthenticated()) {
				UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
				//boolean rememberMe = (loginVM.getRememberMe() == null) ? false : loginVM.getRememberMe();
				String jwtToken = jwtTokenUtil.generateToken(user, false);
				HttpHeaders httpHeaders = new HttpHeaders();
			    httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwtToken);
				return new ResponseEntity<>(new JWTToken(jwtToken), HttpStatus.OK);
			} else {
				responseMap.put("error", true);
                responseMap.put("message", "Invalid Credentials");
                return ResponseEntity.status(401).body(responseMap);
			}
		} catch (DisabledException e) {
            e.printStackTrace();
            responseMap.put("error", true);
            responseMap.put("message", "User is disabled");
            return ResponseEntity.status(500).body(responseMap);
        } catch (BadCredentialsException e) {
            responseMap.put("error", true);
            responseMap.put("message", "Invalid Credentials");
            return ResponseEntity.status(401).body(responseMap);
        } catch (Exception e) {
            e.printStackTrace();
            responseMap.put("error", true);
            responseMap.put("message", "Something went wrong");
            return ResponseEntity.status(500).body(responseMap);
        }
		//SecurityContextHolder.getContext().setAuthentication(authentication);

	}
	
	static class JWTToken {
		private String idToken;

        public JWTToken(String idToken) {
			this.idToken = idToken;
		}

		@JsonProperty("id_token")
        String getIdToken() {
            return idToken;
        }

		void setIdToken(String idToken) {
            this.idToken = idToken;
        }
	}
}
