package com.mafia.exceprion;

import lombok.Getter;
import telegram.Message;

@Getter
public class BotException extends RuntimeException {

	private final Message telegramMessage;

	public BotException(Message telegramMessage, String message) {
		super(message);
		this.telegramMessage = telegramMessage;
	}
}
