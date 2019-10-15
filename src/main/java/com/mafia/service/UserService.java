package com.mafia.service;

import com.mafia.model.User;
import com.mafia.repository.UserRepository;
import org.springframework.stereotype.Service;
import telegram.Message;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User createUser(Message message) {
		return userRepository.findById(message.getChat().getId()).orElseGet(() -> {
			User user = new User();
			user.setChatId(message.getChat().getId());
			user.setName(message.getFrom().getFirstName() + " " + message.getFrom().getLastName());
			return userRepository.saveAndFlush(user);
		});
	}

	public void setUserStatus(User.UserStatus roomConnectionStatus, User user) {

	}

	public User getUser(Integer id) {
		return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid User ID"));
	}
}
