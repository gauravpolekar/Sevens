package com.bba.seven.beans;

import java.util.Collections;
import java.util.List;

public class GameDeck {

	private List<Card> deck;

	public GameDeck() {
		deck = Deck.getDeck().getCards();
		shuffle();
	}
	public List<Card> getCards() {
		return deck;
	}

	public void shuffle() {
		Collections.shuffle(deck);
	}
}
