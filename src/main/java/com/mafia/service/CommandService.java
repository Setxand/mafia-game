package com.mafia.service;

import com.mafia.exceprion.BotException;
import com.mafia.model.Card;
import com.mafia.model.Room;
import com.mafia.model.User;
import org.springframework.stereotype.Service;
import telegram.Message;
import telegram.client.TelegramClient;

import javax.transaction.Transactional;
import java.util.*;

import static com.mafia.model.User.UserStatus.ROOM_CONNECTION_STATUS;

@Service
public class CommandService {

	private final TelegramClient telegramClient;
	private final RoomService roomService;
	private final ServiceValidator validator;
	private final GameService gameService;

	public CommandService(TelegramClient telegramClient, RoomService roomService, ServiceValidator validator,
						  GameService gameService) {
		this.telegramClient = telegramClient;
		this.roomService = roomService;
		this.validator = validator;
		this.gameService = gameService;
	}

	@Transactional
	public void commandToBot(Message message, User user) {
		user.setStatus(null);
		String command = message.getText();

		switch (command) {
			case "/start":
				telegramClient.helloMessage(message);
				break;

			case "/createroom":
				createRoom(message, user);
				break;

			case "/exittheroom":
				exitTheRoom(message, user);
				break;

			case "/connecttoroom":
				connectToRoom(message, user);
				break;

			case "/startgame":
				startGame(message, user);
				break;

			default:
				throw new BotException(message, "Invalid command");
		}

	}

	private void startGame(Message message, User user) {

		Room room = roomService.getRoom(user.getRoomId());
		List<User> users = room.getUsers();

		gameService.start(users);
	}


	private void connectToRoom(Message message, User user) {
		user.setStatus(ROOM_CONNECTION_STATUS);
		telegramClient.simpleMessage("Enter id to connect (from host who has created the room): ", message);
	}

	private void exitTheRoom(Message message, User user) {
		validator.checkUserNotInRoom(message, user);
		List<User> users = roomService.exitTheRoom(user);

		users.forEach(u -> {
			message.getChat().setId(u.getChatId());
			telegramClient.simpleMessage("You have exited the room", message);
		});
	}

	private void createRoom(Message message, User user) {
		validator.checkUserInRoom(message, user);

		Room room = roomService.createRoom(user);
		telegramClient.simpleMessage("Your room has been created, it's id: ", message);
		telegramClient.simpleMessage(room.getId(), message);
	}
}
