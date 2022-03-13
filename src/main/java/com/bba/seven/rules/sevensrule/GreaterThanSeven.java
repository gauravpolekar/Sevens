package com.bba.seven.rules.sevensrule;

import com.bba.seven.beans.Card;
import com.bba.seven.enums.Face;
import com.bba.seven.enums.Suit;
import com.bba.seven.rules.Facts;
import com.bba.seven.rules.Rule;
import com.bba.seven.rules.RuleClass;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RuleClass
public class GreaterThanSeven implements Rule {
	@Override
	public boolean condition(Facts facts) {
		Card card = (Card) facts.get(RuleConstants.FACT_CARD);
		if (card.getFace().getValue() < Face.SEVEN.getValue()) {
			return false;
		}
		Map<Suit, Set<Card>> cardsOnTable = (Map<Suit, Set<Card>>)
				facts.get(RuleConstants.FACT_CARDS_ON_TABLE);
		if (cardsOnTable != null && !cardsOnTable.isEmpty()
				&& cardsOnTable.get(card.getSuit()) != null) {
			Set<Card> cardsOfSameSuit = cardsOnTable.get(card.getSuit());
			Optional<Card> biggerCard = cardsOfSameSuit.stream().filter(c ->
					c.getFace().getValue() == card.getFace().getValue() - 1).findAny();
			return biggerCard.isPresent();
		}
		return false;
	}
}
