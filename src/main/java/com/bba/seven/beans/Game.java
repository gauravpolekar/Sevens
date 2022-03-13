package com.bba.seven.beans;

import com.bba.seven.enums.Face;
import com.bba.seven.enums.GameStatus;
import com.bba.seven.enums.Suit;
import com.bba.seven.exceptions.InvalidCardException;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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
		Player nextPlayer = players().get((players().indexOf(currentTurn()) + 1)
				% players().size());
		setCurrentTurn(nextPlayer);
		//Pass to next player
		if (!hasCardToPlay(nextPlayer)) {
			nextTurn();
		}
	}

	boolean hasCardToPlay(Player nextPlayer);

	void playCurrentTurn(Player player, Card card) throws InvalidCardException;

	Map<Suit, Set<Card>> getCardOnTable();
}
