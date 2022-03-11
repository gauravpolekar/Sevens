package com.bba.seven.beans;

import com.bba.seven.enums.Face;
import com.bba.seven.enums.GameStatus;
import com.bba.seven.enums.Suit;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Game {
	String name();
	GameDeck getDeck();
	List<Player> players();

	void addPlayerToGame(Player player);

	Player currentTurn();

	void setCurrentTurn(Player player);

	GameStatus getStatus();

	void startGame();

	void distributeCards();

	default void nextTurn() {
		if (players() == null) {
			return;
		}
		setCurrentTurn(players().get((players().indexOf(currentTurn()) + 1)
				% players().size()));
	}

	void playCurrentTurn(Player player, Card card);

	Map<Suit, Set<Card>> getCardOnTable();
}
