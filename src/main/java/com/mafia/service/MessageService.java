package com.mafia.service;

import com.mafia.model.User;
import org.springframework.stereotype.Service;
import telegram.Message;

@Service
public class MessageService {

	private final CommandService commandService;

	public MessageService(CommandService commandService) {
		this.commandService = commandService;
	}

	public void messageFromBot(Message message, User user) {

		if (message.getText().contains("/")) {
			commandService.commandToBot(message	, user);
		}

	}
}
