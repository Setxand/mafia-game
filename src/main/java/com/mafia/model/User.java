package com.mafia.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

	public enum UserStatus {
		ROOM_CONNECTION_STATUS
	}

	@Id
	private Integer chatId;
	private Card card;
	private String roomId;

	@Enumerated(EnumType.STRING)
	private UserStatus status;


}
