package com.mafia.client;

import com.mafia.config.UrlConfig;
import com.mafia.utils.PayloadUtils;
import org.springframework.stereotype.Component;
import telegram.Markup;
import telegram.Message;
import telegram.TelegramRequest;
import telegram.button.InlineKeyboardButton;

import java.util.ResourceBundle;

import static com.mafia.service.CallBackQueryPayload.CONNECTION_PL;
import static com.mafia.service.CallBackQueryPayload.QUESTION_PL;

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

	@Override
	public void simpleQuestion(String arg, String text, Message message) {
		String yes = ResourceBundle.getBundle("dictionary").getString("YES");
		String no = ResourceBundle.getBundle("dictionary").getString("NO");
		Markup buttonListMarkup = this.createButtonListMarkup(true,
				new InlineKeyboardButton(yes, PayloadUtils
						.createPayloadWithParams(QUESTION_PL.name(), arg, "QUESTION_YES")),
				new InlineKeyboardButton(no, PayloadUtils
						.createPayloadWithParams(QUESTION_PL.name(), arg, "QUESTION_NO")));

		this.sendButtons(buttonListMarkup, text, message);
	}

	public void connectionQuestion(Message message, Integer hostId, Integer userId) {
		String text = "Connect user " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName() + "?";

		simpleQuestion(CONNECTION_PL.name() + "&" + userId, text, message);
	}
}
