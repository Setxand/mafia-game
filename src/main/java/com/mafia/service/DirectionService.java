package com.mafia.service;

import com.mafia.client.Platform;
import com.mafia.model.User;
import org.springframework.stereotype.Service;
import telegram.Update;

@Service
public class DirectionService {

	private final UserService userService;
	private final MessageService messageService;

	public DirectionService(UserService userService, MessageService messageService) {
		this.userService = userService;
		this.messageService = messageService;
	}


	public void directUpdate(Update update) {

		if (update.getMessage() != null) {
			update.getMessage().setPlatform(Platform.COMMON);
			User user = createUser(update.getMessage().getChat().getId());
			messageService.messageFromBot(update.getMessage(), user);

		} else if (update.getCallBackQuery() != null) {
			update.getCallBackQuery().getMessage().setPlatform(Platform.COMMON);
			User user = createUser(update.getCallBackQuery().getMessage().getChat().getId());
		}


	}

	private User createUser(Integer id) {
		return userService.createUser(id);
	}
}
