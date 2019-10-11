package com.mafia.client;

import com.mafia.config.UrlConfig;
import org.springframework.stereotype.Component;
import telegram.Message;
import telegram.TelegramRequest;

import java.util.ResourceBundle;

@Component
public class TelegramClient extends telegram.client.TelegramClient {

	public TelegramClient(UrlConfig urlConfig) {
		super(urlConfig.getServer(), urlConfig.getWebhook(), urlConfig.getTelegramUrls());
	}

	@Override
	public void helloMessage(Message message) {
		String helloMessage = String.format(ResourceBundle.getBundle("dictionary").getString("HELLO_MESSAGE"),
				message.getFrom().getFirstName() + " " + message.getFrom().getLastName());

		int chatId = message.getChat().getId();
		this.sendMessage(new TelegramRequest(helloMessage, chatId, message.getPlatform()));
	}
}
