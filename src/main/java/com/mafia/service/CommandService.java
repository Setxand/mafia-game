package com.mafia.service;

import com.mafia.exceprion.BotException;
import com.mafia.model.User;
import org.springframework.stereotype.Service;
import telegram.Message;
import telegram.client.TelegramClient;

@Service
public class CommandService {

	private final TelegramClient telegramClient;

	public CommandService(TelegramClient telegramClient) {
		this.telegramClient = telegramClient;
	}

	public void commandToBot(Message message, User user) {

		String command = message.getText();

		switch (command) {
			case "/start":
				telegramClient.helloMessage(message);
				break;
			default:
				throw new BotException(message, "Invalid command");
		}

	}
}
