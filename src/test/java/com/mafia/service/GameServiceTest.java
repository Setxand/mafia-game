package com.mafia.service;

import com.mafia.model.Card;
import com.mafia.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static com.mafia.model.Card.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest extends UnitTest {

	@InjectMocks
	private GameService gameService;

	@Test
	public void useStart() {
		List<User> fiveUsers = start(5);
		List<User> eightUsers = start(8);
		List<User> thirteenUsers = start(13);

		checkPerson(fiveUsers, MAFIA, 1);
		checkPerson(fiveUsers, POLICE, 1);
		checkPerson(fiveUsers, CITIZEN, 2);
		checkPerson(fiveUsers, DOCTOR, 1);

		checkPerson(eightUsers, MAFIA, 2);
		checkPerson(eightUsers, POLICE, 1);
		checkPerson(eightUsers, CITIZEN, 4);
		checkPerson(eightUsers, DOCTOR, 1);

		checkPerson(thirteenUsers, MAFIA, 3);
		checkPerson(thirteenUsers, POLICE, 1);
		checkPerson(thirteenUsers, CITIZEN, 8);
		checkPerson(thirteenUsers, DOCTOR, 1);
	}

	private void checkPerson(List<User> users, Card card, int countPerson) {
		assertEquals(users.stream().filter(u -> u.getCard() == card).count(), countPerson);
	}

	private List<User> start(int count) {

		List<User> userList = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			userList.add(createUser());
		}

		gameService.arrangeCards(userList);

		return userList;
	}
}