package com.mafia;

import com.mafia.client.TelegramClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class StartServer {

	@Autowired TelegramClient telegramClient;

	@PostConstruct
	public void setUp() {
		telegramClient.setWebHooks();
	}

}
