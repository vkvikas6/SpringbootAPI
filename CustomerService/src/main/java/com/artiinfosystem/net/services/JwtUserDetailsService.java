package com.artiinfosystem.net.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.artiinfosystem.net.model.JwtUser;
import com.artiinfosystem.net.repository.UserRepository;


@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userDao;




	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		JwtUser user = userDao.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				new ArrayList<>());
	}
	
	public JwtUser save(JwtUser user) {
		JwtUser jwtUser = new JwtUser();
		jwtUser.setUserName(user.getUserName());
		jwtUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		return userDao.save(jwtUser);
	}
}