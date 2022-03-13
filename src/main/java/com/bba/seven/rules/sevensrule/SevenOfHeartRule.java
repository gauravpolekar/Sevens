package com.bba.seven.rules.sevensrule;

import com.bba.seven.beans.Card;
import com.bba.seven.beans.Deck;
import com.bba.seven.rules.Facts;
import com.bba.seven.rules.Rule;
import com.bba.seven.rules.RuleClass;

@RuleClass
public class SevenOfHeartRule implements Rule {
	@Override
	public boolean condition(Facts facts) {
		if (facts.get(RuleConstants.FACT_CARD) != null) {
			Card card = (Card) facts.get(RuleConstants.FACT_CARD);
			return card.equals(Deck.SEVEN_OF_HEART);
		}
		return false;
	}
}
