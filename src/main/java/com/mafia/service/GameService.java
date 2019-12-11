package com.mafia.service;

import com.mafia.model.Card;
import com.mafia.model.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class GameService {

	private static final Random random = new Random();


	@Transactional
	public void start(List<User> users) {
		Stack<Card> cardPack = createCardPack(users.size());

		users.forEach(u -> u.setCard(cardPack.pop()));
	}

	private static Stack<Card> createCardPack(int size) {
		List<Card> cards = new ArrayList<>();

		int countOfMafia = 1;

		if (size >= 7) countOfMafia += 1;
		if (size >= 12) countOfMafia += 1;


		for (int i = 0; i < countOfMafia; i++) {
			cards.add(Card.MAFIA);
		}
		cards.add(Card.POLICE);

		for (int i = 0; i < size - countOfMafia - 1; i++) {
			cards.add(Card.CITIZEN);
		}

		for (int i = 0; i < size * 3; i++) {
			int f = random.nextInt(size);
			int s = f;

			while (s == f) {
				s = random.nextInt(size);
			}

			Collections.swap(cards, f, s);
		}

		Stack<Card> cardStack = new Stack<>();

		for (Card card : cards) {
			cardStack.push(card);
		}

		return cardStack;

	}
}
