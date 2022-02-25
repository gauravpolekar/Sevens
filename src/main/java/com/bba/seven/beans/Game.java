package com.bba.seven.beans;

import java.util.List;

public interface Game {
	String name();
	GameDeck getDeck();
	List<Player> players();

	Player currentTurn();

	void setCurrentTurn(Player player);

	void playCurrentTurn(Player player);

	default void nextTurn() {
		if (players() == null) {
			return;
		}
		setCurrentTurn(players().get((players().indexOf(currentTurn()) + 1)
				% players().size()));
	}
}
