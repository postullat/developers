package com.epam.lab.developers.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private User user;
	
	public MyUserDetails(User user){
		this.user = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		
		class UserRole implements GrantedAuthority{

			@Override
			public String getAuthority() {
				return user.getRole();
			}
			
		}
		
		List<UserRole> roles = new ArrayList<UserRole>();
		roles.add(new UserRole());
		
		return roles;
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		
		return this.user.getName();
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
		return true;
	}

	public User getUser() {
		return user;
	}

}
