package com.mafia.service;

import com.mafia.client.TelegramClient;
import com.mafia.exceprion.BotException;
import com.mafia.model.Room;
import com.mafia.model.User;
import com.mafia.utils.PayloadUtils;
import org.springframework.stereotype.Service;
import telegram.CallBackQuery;
import telegram.Message;

import javax.transaction.Transactional;

@Service
public class CallBackQueryService {

	private final RoomService roomService;
	private final UserService userService;
	private final TelegramClient telegramClient;

	public CallBackQueryService(RoomService roomService, UserService userService, TelegramClient telegramClient) {
		this.roomService = roomService;
		this.userService = userService;
		this.telegramClient = telegramClient;
	}

	@Transactional
	public void callBackQueryToBot(CallBackQuery callBackQuery, User user) {
		String commonPayload = PayloadUtils.getCommonPayload(callBackQuery.getData());
		switch (CallBackQueryPayload.valueOf(
				commonPayload)) {

			case QUESTION_PL:
				question(callBackQuery, user);
				break;

			default:
				throw new BotException(callBackQuery.getMessage(), "This feature has not been implemented yet");
		}

	}

	private void question(CallBackQuery callBackQuery, User user) {
		String[] params = PayloadUtils.getParams(callBackQuery.getData());


		switch (CallBackQueryPayload.valueOf(params[0])) {
			case CONNECTION_PL:
				connectionToRoom(callBackQuery, user, params);
				break;
			default:
				throw new BotException(callBackQuery.getMessage(), "This feature has not been implemented yet");
		}
	}

	private void connectionToRoom(CallBackQuery callBackQuery, User user, String[] params) {
		telegramClient.deleteMessage(callBackQuery.getMessage());

		String userId = params[1];
		User userToConnect = userService.getUser(Integer.valueOf(userId));

		if (params[2].equals("QUESTION_YES")) {
			Room room = roomService.connectUserToRoom(user, userToConnect);
			Message message = callBackQuery.getMessage();
			userToConnect.setStatus(null);

			room.getUsers().forEach(u -> {////todo create broadcast method in the room service
				message.getChat().setId(u.getChatId());
				telegramClient.simpleMessage(String.format("User %s has connected to the room", u.getName()), message);
			});

		} else {
			Message message = callBackQuery.getMessage();
			message.getChat().setId(userToConnect.getChatId());
			userToConnect.setStatus(null);
			telegramClient.simpleMessage("The host of the room has rejected your request", message);
		}

		userToConnect.setStatus(null);
	}
}
