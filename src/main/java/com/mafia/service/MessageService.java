package com.mafia.service;

import com.mafia.client.TelegramClient;
import com.mafia.exceprion.BotException;
import com.mafia.model.Room;
import com.mafia.model.User;
import org.springframework.stereotype.Service;
import telegram.Message;

@Service
public class MessageService {

	private final CommandService commandService;
	private final RoomService roomService;
	private final UserService userService;
	private final TelegramClient telegramClient;

	public MessageService(CommandService commandService, RoomService roomService, UserService userService,
						  TelegramClient telegramClient) {
		this.commandService = commandService;
		this.roomService = roomService;
		this.userService = userService;
		this.telegramClient = telegramClient;
	}

	public void messageFromBot(Message message, User user) {


		if (message.getText().contains("/")) {
			commandService.commandToBot(message, user);

		} else if (user.getStatus() != null) {
			messageByStatus(message, user);
		} else {
			throw new BotException(message, "I don't know this command");
		}

	}

	private void messageByStatus(Message message, User user) {

		switch (user.getStatus()) {

			case ROOM_CONNECTION_STATUS:
				roomConnection(message, user);
				break;

			default:
				throw new BotException(message, "This feature is not done yet");
		}

	}

	private void roomConnection(Message message, User user) {
		Room room = validateAndGetRoom(message);

		User host = userService.getUser(room.getHostId());

		telegramClient.simpleMessage("Wait for host answer...", message);///todo add the pool of rejected users
		message.getChat().setId(host.getChatId());
		telegramClient.connectionQuestion(message, host.getChatId(), user.getChatId());
	}

	private Room validateAndGetRoom(Message message) {
		Room room = null;

		try {
			room = roomService.getRoom(message.getText());
		} catch (IllegalArgumentException ex) {
			throw new BotException(message, "Invalid room ID");
		}
		return room;
	}
}
