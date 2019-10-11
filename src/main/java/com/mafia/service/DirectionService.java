package com.mafia.service;

import com.mafia.client.Platform;
import com.mafia.model.User;
import org.springframework.stereotype.Service;
import telegram.Update;

@Service
public class DirectionService {

	private final UserService userService;
	private final MessageService messageService;
	private final CallBackQueryService callBackQUeryService;

	public DirectionService(UserService userService, MessageService messageService,
							CallBackQueryService callBackQUeryService) {
		this.userService = userService;
		this.messageService = messageService;
		this.callBackQUeryService = callBackQUeryService;
	}


	public void directUpdate(Update update) {

		if (update.getMessage() != null) {
			update.getMessage().setPlatform(Platform.COMMON);
			User user = createUser(update.getMessage().getChat().getId());
			messageService.messageFromBot(update.getMessage(), user);

		} else if (update.getCallBackQuery() != null) {
			update.getCallBackQuery().getMessage().setPlatform(Platform.COMMON);
			User user = createUser(update.getCallBackQuery().getMessage().getChat().getId());
			callBackQUeryService.callBackQueryToBot(update.getCallBackQuery(), user);
		}


	}

	private User createUser(Integer id) {
		return userService.createUser(id);
	}
}
