package com.bba.seven.beans;

import com.bba.seven.enums.Suit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Card {
	private String face;
	private String unicode;
	private Suit suit;
}
