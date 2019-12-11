package com.mafia.service;

import com.mafia.model.User;

import java.util.Random;
import java.util.UUID;

public class UnitTest {

	protected User createUser() {
		User user = new User();
		user.setName(UUID.randomUUID().toString());
		user.setRoomId(UUID.randomUUID().toString());
		user.setChatId(new Random().nextInt());
		return user;
	}

}
