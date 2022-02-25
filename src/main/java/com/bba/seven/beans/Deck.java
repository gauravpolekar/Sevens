package com.bba.seven.beans;

import com.bba.seven.enums.Suit;

import java.util.ArrayList;
import java.util.List;

public class Deck {

	private static Deck deck;

	private static List<Card> cards;

	private Deck() {
		initializeDeck();
	}

	public static Deck getDeck() {
		if (deck == null) {
			deck = new Deck();
		}
		return deck;
	}

	public List<Card> getCards() {
		if (cards == null) {
			initializeDeck();
		}
		return new ArrayList<>(cards);
	}

	private static void initializeDeck() {
		cards = new ArrayList<>();
		cards.add(new Card("1A", "\uD83C\uDCA1", Suit.SPADE));

		cards.add(new Card("1A", "\uD83C\uDCB1", Suit.HEART));

		cards.add(new Card("1A", "\uD83C\uDCC1", Suit.DIAMOND));

		cards.add(new Card("1A", "\uD83C\uDCD1", Suit.CLUB));

	}

}
