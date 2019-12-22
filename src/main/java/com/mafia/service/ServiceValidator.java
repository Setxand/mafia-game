package com.mafia.service;

import com.mafia.exceprion.BotException;
import com.mafia.model.Room;
import com.mafia.model.User;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import telegram.Message;

@Component
public class ServiceValidator {

	@Value("app.mode") private Boolean testMode;

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

	public void validateStartGame(User user, Room room, Message message) {
		if (room.getUsers().size() < 5 && !testMode) throw new BotException(message, "Minimum players is 5!");

		if (!user.getChatId().equals(room.getHostId()))
			throw new BotException(message, "You are not a host of the game");
	}
}
