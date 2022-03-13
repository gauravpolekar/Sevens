package com.bba.seven.rules;

import java.util.HashMap;
import java.util.Map;

public class Facts {

	private Map<String, Object> facts = new HashMap<>();

	public void put(String key, Object value) {
		facts.put(key, value);
	}

	public Object get(String key) {
		return facts.get(key);
	}
}
