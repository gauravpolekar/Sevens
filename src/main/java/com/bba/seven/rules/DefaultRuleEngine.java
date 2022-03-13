package com.bba.seven.rules;

import java.util.List;

public class DefaultRuleEngine implements RuleEngine {

	@Override
	public boolean evaluate(List<Rule> rules, Facts facts) {
		if (rules == null) {
			return false;
		}
		for (Rule rule: rules) {
			if (rule.condition(facts)) {
				return true;
			}
		}
		return false;
	}
}
