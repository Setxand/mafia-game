package com.mafia.controller;

import com.mafia.client.TelegramClient;
import com.mafia.exceprion.BotException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@Autowired TelegramClient telegramClient;

	private static final Logger log = Logger.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(BotException.class)
	public void handleBotException(final BotException ex) {
		telegramClient.simpleMessage(ex.getMessage(), ex.getTelegramMessage());
	}

	@ExceptionHandler(Exception.class)
	public void handleException(final Exception ex) {
		log.warn("Failed to process the operation : ", ex);
	}

}
