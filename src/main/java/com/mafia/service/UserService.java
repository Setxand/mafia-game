package com.mafia.service;

import com.mafia.model.User;
import com.mafia.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User createUser(Integer id) {
		return userRepository.findById(id).orElseGet(() -> {
			User user = new User();
			user.setChatId(id);
			return userRepository.saveAndFlush(user);
		});
	}
}
