package com.bba.seven.beans;

import java.util.Collections;
import java.util.List;

public class GameDeck {

	private List<Card> deck;
	public List<Card> getCards() {
		deck = Deck.getDeck().getCards();
		shuffle();
		return deck;
	}

	public void shuffle() {
		Collections.shuffle(deck);
	}
}
