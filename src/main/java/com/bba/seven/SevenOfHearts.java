package com.bba.seven;

import com.bba.seven.beans.Card;
import com.bba.seven.beans.Game;
import com.bba.seven.beans.GameDeck;
import com.bba.seven.beans.Player;
import com.bba.seven.enums.Face;
import com.bba.seven.enums.GameStatus;
import com.bba.seven.enums.Suit;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Seven of heart card game, Badam saat in hindi and marathi
 */
public class SevenOfHearts implements Game {

	private String gameName;
	private GameDeck deck;
	private List<Player> players;
	private Player currentTurn;
	private GameStatus status;

	private Map<Suit, List<Face>> cardsOnTable;

	public SevenOfHearts() {
		deck = new GameDeck();
		players = new ArrayList<>();
		cardsOnTable = new HashMap<>();
		status = GameStatus.READY_TO_START;
		gameName = SevenOfHearts.class.getName() + UUID.randomUUID();
	}

	@Override
	public String name() {
		return gameName;
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
	public void addPlayerToGame(Player player) {
		if (GameStatus.READY_TO_START.equals(status)) {
			players.add(player);
		} else {
			//TODO game already started
		}
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
	public GameStatus getStatus() {
		return status;
	}

	@Override
	public void startGame() {
		if (GameStatus.READY_TO_START.equals(status) && !CollectionUtils.isEmpty(players)) {
			status = GameStatus.STARTED;
			initializeCurrentPlayer();
			distributeCards();
		} else {
			//TODO can not start game
		}
	}

	@Override
	public void distributeCards() {
		int currentIndex = 0;

		while (currentIndex < deck.getCards().size()) {
			players.get(currentIndex % players.size()).addCard(deck.getCards().get(currentIndex));
			currentIndex ++;
		}
	}

	private void initializeCurrentPlayer() {
        setCurrentTurn(players.stream().filter(player -> player.getCards().stream().anyMatch(
				card -> Face.SEVEN.equals(card.getFace())
						&& Suit.HEART.equals(card.getSuit()))).findAny().get());
	}


	@Override
	public void playCurrentTurn(Player player, Card card) {
		//Game logic

		if (player.getCards().contains(card)) {
			if (isValidCardToPlay(card)) {
				addCardToTable(card);
				nextTurn();
			}
		}
	}

	private void addCardToTable(Card card) {
		List<Face> faces = cardsOnTable.getOrDefault(card.getSuit(), new ArrayList<>());
		faces.add(card.getFace());
		cardsOnTable.put(card.getSuit(), faces);
	}

	private boolean isValidCardToPlay(Card card) {
		//Evaluate Game rules
		return false;
	}
}
