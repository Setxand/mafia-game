package com.mafia.service;

import com.mafia.exceprion.BotException;
import com.mafia.model.Room;
import com.mafia.model.User;
import org.springframework.stereotype.Component;
import telegram.Message;

@Component
public class ServiceValidator {

	private final RoomService roomService;

	public ServiceValidator(RoomService roomService) {
		this.roomService = roomService;
	}

	public void checkUserInRoom(Message message, User user) {////todo refactor
		if (user.getRoomId() != null)
			throw new BotException(message, "You can't do this, cause you are already in the game");
	}

	public void checkUserNotInRoom(Message message, User user) {
		if (user.getRoomId() == null)
			throw new BotException(message, "You aren't in the room now!");
	}

	public void checkCountInTheGame(int size, Message message) {
		if (size < 5) throw new BotException(message, "Minimum players is 5!");
	}
}
