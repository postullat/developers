package com.epam.lab.developers.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.epam.lab.developers.dao.impl.UserDAO;
import com.epam.lab.developers.entity.MyUserDetails;
import com.epam.lab.developers.entity.User;

public class CustomUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		

		UserDAO userDAO = new UserDAO();
		User user = userDAO.getByName(username);
		
		if(user!=null){
			return new MyUserDetails(user);
		} else{
			throw new UsernameNotFoundException("Invalid user name");
		}
	}

}
