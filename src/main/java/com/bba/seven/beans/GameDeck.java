package com.bba.seven.beans;

import java.util.Collections;
import java.util.List;

public class GameDeck {

	public List<Card> getCards() {
		List<Card> deck = Deck.getDeck().getCards();
		Collections.shuffle(deck);
		return deck;
	}
}
