package com.bba.seven.rules.sevensrule;

import com.bba.seven.beans.Card;
import com.bba.seven.enums.Face;
import com.bba.seven.enums.Suit;
import com.bba.seven.rules.Facts;
import com.bba.seven.rules.Rule;
import com.bba.seven.rules.RuleClass;

import java.util.Map;
import java.util.Set;

@RuleClass
public class SevenRule implements Rule {
	@Override
	public boolean condition(Facts facts) {
		Map<Suit, Set<Card>>  cardsOnTable = (Map<Suit, Set<Card>>)
				facts.get(RuleConstants.FACT_CARDS_ON_TABLE);
		Card card = (Card) facts.get(RuleConstants.FACT_CARD);
		if ( cardsOnTable != null && !cardsOnTable.isEmpty()) {
			if (card.getFace().equals(Face.SEVEN)) {
				return true;
			}
		}
		return false;
	}
}
