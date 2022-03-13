package com.bba.seven.rules;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class DefaultRuleEngine implements RuleEngine {

	private static final Logger LOGGER = LogManager.getLogger(DefaultRuleEngine.class);

	@Override
	public boolean evaluate(List<Rule> rules, Facts facts) {
		if (rules == null) {
			return false;
		}
		for (Rule rule: rules) {
			LOGGER.info("Rule " + rule.getClass());
			if (rule.condition(facts)) {
				return true;
			}
		}
		return false;
	}
}
