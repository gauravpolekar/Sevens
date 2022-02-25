package com.bba.seven;

import com.bba.seven.beans.Card;
import com.bba.seven.beans.Deck;
import com.bba.seven.beans.Game;
import com.bba.seven.beans.GameDeck;
import com.bba.seven.beans.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SevenOfHearts implements Game {

	private GameDeck deck;
	private List<Player> players;
	private Player currentTurn;

	public SevenOfHearts() {
		deck = new GameDeck();
		players = new ArrayList<>();
	}

	@Override
	public String name() {
		return SevenOfHearts.class.getName();
	}

	@Override
	public GameDeck getDeck() {
		return deck;
	}

	@Override
	public List<Player> players() {
		return players;
	}

	@Override
	public Player currentTurn() {
		return currentTurn;
	}

	@Override
	public void setCurrentTurn(Player player) {
		if (player != null) {
			this.currentTurn = player;
		}
	}

	@Override
	public void playCurrentTurn(Player player) {

		//Game logic
	}
}
