package com.bba.seven.beans;

import com.bba.seven.enums.Face;
import com.bba.seven.enums.Suit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Card implements Comparable<Card> {
	private Face face;
	private String unicode;
	private Suit suit;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Card card = (Card) o;
		return face.equals(card.face) && suit.equals(card.suit);
	}

	@Override
	public int hashCode() {
		return Objects.hash(face, suit);
	}

	@Override
	public int compareTo(Card o) {
		int suitCompare = this.getSuit().compareTo(o.getSuit());
		if (suitCompare == 0) {
			return Integer.compare(this.face.getValue(), o.face.getValue());
		}
		return suitCompare;
	}

}
