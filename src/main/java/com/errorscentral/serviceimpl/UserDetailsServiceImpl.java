package com.errorscentral.serviceimpl;

import java.util.ArrayList;
import com.errorscentral.entity.User;
import com.errorscentral.repository.UserRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = this.userRepository.findByUsername(email);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + email);
		}
		return new org.springframework.security.core.userdetails.User(user.getFirstName(), user.getPassword(),
				new ArrayList<>());
	}

	public String decodeBase64( String value ) {

		byte[] decodeByte = Base64.decodeBase64(value.getBytes());
		String decodeString = new String(decodeByte);
		return decodeString;
	}

	public User save(User user) {
		User newUser = new User();
		newUser.setPassword(this.bcryptEncoder.encode(user.getPassword()));
		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		return this.userRepository.save(newUser);
	}
}