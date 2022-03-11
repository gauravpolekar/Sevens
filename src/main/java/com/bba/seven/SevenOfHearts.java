package com.bba.seven;

import com.bba.seven.beans.Card;
import com.bba.seven.beans.Deck;
import com.bba.seven.beans.Game;
import com.bba.seven.beans.GameDeck;
import com.bba.seven.beans.Player;
import com.bba.seven.enums.Face;
import com.bba.seven.enums.GameStatus;
import com.bba.seven.enums.Suit;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

	private Map<Suit, Set<Card>> cardsOnTable;

	public SevenOfHearts() {
		deck = new GameDeck();
		players = new ArrayList<>();
		cardsOnTable = new HashMap<>();
		status = GameStatus.READY_TO_START;
		gameName = SevenOfHearts.class.getSimpleName() + UUID.randomUUID();
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
			if (!players.contains(player)) {
				players.add(player);
			}
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
			players.stream().forEach(p-> p.setCurrentPlayer(false));
			this.currentTurn = player;
			player.setCurrentPlayer(true);
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
			distributeCards();
			initializeCurrentPlayer();
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
		Player p = players.stream().filter(player -> player.getCards().contains(Deck.SEVEN_OF_HEART))
				.findAny().get();
        setCurrentTurn(p);
	}


	@Override
	public void playCurrentTurn(Player player, Card card) {
		//Game logic
		if (currentTurn().equals(player)) {
			if (currentTurn().getCards().contains(card)) {
				if (isValidCardToPlay(card)) {
					addCardToTable(card);
					nextTurn();
				}
			}
		}
	}

	private void addCardToTable(Card card) {
		Set<Card> faces = cardsOnTable.getOrDefault(card.getSuit(), new HashSet<>());
		faces.add(card);
		cardsOnTable.put(card.getSuit(), faces);
		currentTurn().getCards().remove(card);
	}

	private boolean isValidCardToPlay(Card card) {
		//Evaluate Game rules
		return true;
	}

	@Override
	public Map<Suit, Set<Card>> getCardOnTable() {
		return cardsOnTable;
	}
}
