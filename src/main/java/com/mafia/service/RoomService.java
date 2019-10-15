package com.mafia.service;

import com.mafia.model.Room;
import com.mafia.model.User;
import com.mafia.repository.RoomRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RoomService {

	private final RoomRepository roomRepo;

	public RoomService(RoomRepository roomRepo) {
		this.roomRepo = roomRepo;
	}

	@Transactional
	public Room createRoom(User host) {
		Room room = new Room();
		room.setHostId(host.getChatId());
		room.getUsers().add(host);
		Room createdRoom = roomRepo.saveAndFlush(room);
		host.setRoomId(createdRoom.getId());
		return createdRoom;
	}

	@Transactional
	public List<User> exitTheRoom(User user) {
		Room room = getRoom(user.getRoomId());

		if (room.getHostId().equals(user.getChatId())) {
			ArrayList<User> copyList = new ArrayList<>(room.getUsers());

			room.getUsers().forEach(u -> {
				u.setRoomId(null);
				if (u.getCard() != null) u.setCard(null);
			});

			room.setUsers(new ArrayList<>());
			roomRepo.delete(room);

			return copyList;
		} else {

			room.getUsers().remove(user);
			return Collections.singletonList(user);
		}
	}

	public Room getRoom(String roomId) {
		return roomRepo.findById(roomId).orElseThrow(() -> new IllegalArgumentException("Invalid User ID"));
	}

	@Transactional
	public Room connectUserToRoom(User user, User userToConnect) {
		Room room = getRoom(user.getRoomId());
		room.getUsers().add(userToConnect);
		userToConnect.setRoomId(room.getId());
		return room;
	}
}
