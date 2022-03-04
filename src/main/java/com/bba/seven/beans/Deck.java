package com.bba.seven.beans;

import com.bba.seven.enums.Face;
import com.bba.seven.enums.Suit;

import java.util.ArrayList;
import java.util.List;

public class Deck {

	private static Deck deck;

	private static List<Card> cards;

	public static Card SEVEN_OF_HEART = null;
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
		cards.add(new Card(Face.ACE, "\uD83C\uDCA1", Suit.SPADE));
		cards.add(new Card(Face.TWO, "\uD83C\uDCA2", Suit.SPADE));
		cards.add(new Card(Face.THREE, "\uD83C\uDCA3", Suit.SPADE));
		cards.add(new Card(Face.FOUR, "\uD83C\uDCA4", Suit.SPADE));
		cards.add(new Card(Face.FIVE, "\uD83C\uDCA5", Suit.SPADE));
		cards.add(new Card(Face.SIX, "\uD83C\uDCA6", Suit.SPADE));
		cards.add(new Card(Face.SEVEN, "\uD83C\uDCA7", Suit.SPADE));
		cards.add(new Card(Face.EIGHT, "\uD83C\uDCA8", Suit.SPADE));
		cards.add(new Card(Face.NINE, "\uD83C\uDCA9", Suit.SPADE));
		cards.add(new Card(Face.TEN, "\uD83C\uDCAA", Suit.SPADE));
		cards.add(new Card(Face.JACK, "\uD83C\uDCAB", Suit.SPADE));
		cards.add(new Card(Face.QUEEN, "\uD83C\uDCAD", Suit.SPADE));
		cards.add(new Card(Face.KING, "\uD83C\uDCAE", Suit.SPADE));


		cards.add(new Card(Face.ACE, "\uD83C\uDCB1", Suit.HEART));
		cards.add(new Card(Face.TWO, "\uD83C\uDCB2", Suit.HEART));
		cards.add(new Card(Face.THREE, "\uD83C\uDCB3", Suit.HEART));
		cards.add(new Card(Face.FOUR, "\uD83C\uDCB4", Suit.HEART));
		cards.add(new Card(Face.FIVE, "\uD83C\uDCB5", Suit.HEART));
		cards.add(new Card(Face.SIX, "\uD83C\uDCB6", Suit.HEART));
		SEVEN_OF_HEART = new Card(Face.SEVEN, "\uD83C\uDCB7", Suit.HEART);
		cards.add(SEVEN_OF_HEART);
		cards.add(new Card(Face.EIGHT, "\uD83C\uDCB8", Suit.HEART));
		cards.add(new Card(Face.NINE, "\uD83C\uDCB9", Suit.HEART));
		cards.add(new Card(Face.TEN, "\uD83C\uDCBA", Suit.HEART));
		cards.add(new Card(Face.JACK, "\uD83C\uDCBB", Suit.HEART));
		cards.add(new Card(Face.QUEEN, "\uD83C\uDCBD", Suit.HEART));
		cards.add(new Card(Face.KING, "\uD83C\uDCBE", Suit.HEART));


		cards.add(new Card(Face.ACE, "\uD83C\uDCC1", Suit.DIAMOND));
		cards.add(new Card(Face.TWO, "\uD83C\uDCC2", Suit.DIAMOND));
		cards.add(new Card(Face.THREE, "\uD83C\uDCC3", Suit.DIAMOND));
		cards.add(new Card(Face.FOUR, "\uD83C\uDCC4", Suit.DIAMOND));
		cards.add(new Card(Face.FIVE, "\uD83C\uDCC5", Suit.DIAMOND));
		cards.add(new Card(Face.SIX, "\uD83C\uDCC6", Suit.DIAMOND));
		cards.add(new Card(Face.SEVEN, "\uD83C\uDCC7", Suit.DIAMOND));
		cards.add(new Card(Face.EIGHT, "\uD83C\uDCC8", Suit.DIAMOND));
		cards.add(new Card(Face.NINE, "\uD83C\uDCC9", Suit.DIAMOND));
		cards.add(new Card(Face.TEN, "\uD83C\uDCCA", Suit.DIAMOND));
		cards.add(new Card(Face.JACK, "\uD83C\uDCCB", Suit.DIAMOND));
		cards.add(new Card(Face.QUEEN, "\uD83C\uDCCD", Suit.DIAMOND));
		cards.add(new Card(Face.KING, "\uD83C\uDCCE", Suit.DIAMOND));

		cards.add(new Card(Face.ACE, "\uD83C\uDCD1", Suit.CLUB));
		cards.add(new Card(Face.TWO, "\uD83C\uDCD2", Suit.CLUB));
		cards.add(new Card(Face.THREE, "\uD83C\uDCD3", Suit.CLUB));
		cards.add(new Card(Face.FOUR, "\uD83C\uDCD4", Suit.CLUB));
		cards.add(new Card(Face.FIVE, "\uD83C\uDCD5", Suit.CLUB));
		cards.add(new Card(Face.SIX, "\uD83C\uDCD6", Suit.CLUB));
		cards.add(new Card(Face.SEVEN, "\uD83C\uDCD7", Suit.CLUB));
		cards.add(new Card(Face.EIGHT, "\uD83C\uDCD8", Suit.CLUB));
		cards.add(new Card(Face.NINE, "\uD83C\uDCD9", Suit.CLUB));
		cards.add(new Card(Face.TEN, "\uD83C\uDCDA", Suit.CLUB));
		cards.add(new Card(Face.JACK, "\uD83C\uDCDB", Suit.CLUB));
		cards.add(new Card(Face.QUEEN, "\uD83C\uDCDD", Suit.CLUB));
		cards.add(new Card(Face.KING, "\uD83C\uDCDE", Suit.CLUB));
	}

}
