package com.bba.seven.enums;

public enum Suit {

	SPADE(Color.BLACK), HEART(Color.RED), DIAMOND(Color.RED), CLUB(Color.BLACK);

	private final Color color;

	Suit(Color color) {
		this.color = color;
	}
}
