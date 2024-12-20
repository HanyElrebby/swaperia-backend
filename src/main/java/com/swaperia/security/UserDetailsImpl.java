package com.swaperia.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.swaperia.model.User;

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;

	private User user;
	
	public UserDetailsImpl(User user) {
		this.user = user;
	}

	@Override
	public Set<GrantedAuthority> getAuthorities() {
		return user.getAuthorities()
				.stream()
				.map(authority -> new SimpleGrantedAuthority(authority.getName()))
				.collect(Collectors.toSet());
	}
	
	public Long getId() {
		return user.getId();
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}
	
	public String getEmail() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return user.isActivated();
	}

	
}
