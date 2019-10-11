package com.mafia.controller;

import com.mafia.client.TelegramClient;
import com.mafia.exceprion.BotException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@Autowired TelegramClient telegramClient;

	@ExceptionHandler(BotException.class)
	public void handleBOtException(final BotException ex) {
		telegramClient.simpleMessage(ex.getMessage(), ex.getTelegramMessage());
	}

}
