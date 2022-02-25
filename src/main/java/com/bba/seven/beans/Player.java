package com.bba.seven.beans;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Player {
	private String name;
	private List<Card> cards;
}
