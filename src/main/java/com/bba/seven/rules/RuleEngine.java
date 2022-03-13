package com.bba.seven.rules;

import java.util.List;

public interface RuleEngine {

	/**
	 * Returns true if any rule matches
	 * @param rules
	 * @param facts
	 * @return
	 */
	boolean evaluate(List<Rule> rules, Facts facts);
}
