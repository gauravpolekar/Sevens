package com.bba.seven;

import com.bba.seven.beans.Card;
import com.bba.seven.beans.Deck;
import com.bba.seven.beans.Game;
import com.bba.seven.beans.GameDeck;
import com.bba.seven.beans.Player;
import com.bba.seven.enums.Face;
import com.bba.seven.enums.GameStatus;
import com.bba.seven.enums.Suit;
import com.bba.seven.exceptions.InvalidCardException;
import com.bba.seven.rules.DefaultRuleEngine;
import com.bba.seven.rules.Facts;
import com.bba.seven.rules.Rule;
import com.bba.seven.rules.RuleClass;
import com.bba.seven.rules.RuleEngine;
import com.bba.seven.rules.sevensrule.RuleConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reflections.Reflections;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
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

	private List<Rule> rules;
	private Facts facts;
	private RuleEngine ruleEngine;
	private static final Logger LOGGER = LogManager.getLogger(SevenOfHearts.class);

	public SevenOfHearts() {
		deck = new GameDeck();
		players = new ArrayList<>();
		cardsOnTable = new HashMap<>();
		status = GameStatus.READY_TO_START;
		gameName = SevenOfHearts.class.getSimpleName() + UUID.randomUUID();
		ruleEngine = new DefaultRuleEngine();
		rules = getAllRules();
		facts = new Facts();
		facts.put(RuleConstants.FACT_CARDS_ON_TABLE, cardsOnTable);
	}

	private List<Rule> getAllRules() {
		List<Rule> rules = new ArrayList<>();
		try {
			Reflections ref = new Reflections("com.bba.seven.rules.sevensrule");
			for (Class<?> cl : ref.getTypesAnnotatedWith(RuleClass.class)) {
				rules.add((Rule) cl.getDeclaredConstructor().newInstance());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rules;
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
		players.stream().forEach(p-> Collections.sort(p.getCards()));
	}

	@Override
	public boolean hasCardToPlay(Player nextPlayer) {
		for(Card c: nextPlayer.getCards()) {
			if (isValidCardToPlay(c)) {
				return true;
			}
		}
		return false;
	}

	private void initializeCurrentPlayer() {
		Player p = players.stream().filter(player -> player.getCards().contains(Deck.SEVEN_OF_HEART))
				.findAny().get();
        setCurrentTurn(p);
	}


	@Override
	public void playCurrentTurn(Player player, Card card) throws InvalidCardException {
		//Game logic
		LOGGER.info("Player " + player.getName() + " Card " + card.getSuit() + card.getFace());
		if (currentTurn().equals(player)) {
			if (currentTurn().getCards().contains(card)) {
				if (isValidCardToPlay(card)) {
					addCardToTable(card);
					if (checkWinner(currentTurn())) {
						return;
					}
					nextTurn();
				} else {
					throw new InvalidCardException("Not a valid card to play");
				}
			}
		}
	}

	private boolean checkWinner(Player player) {
		if (player.getCards().isEmpty()) {
			this.status = GameStatus.COMPLETED;
			return true;
		}
		return false;
	}

	private void addCardToTable(Card card) {
		Set<Card> faces = cardsOnTable.getOrDefault(card.getSuit(), new TreeSet<>());
		faces.add(card);
		cardsOnTable.put(card.getSuit(), faces);
		currentTurn().getCards().remove(card);
	}

	private boolean isValidCardToPlay(Card card) {
		//Evaluate Game rules
		LOGGER.info("Total rules "+ rules.size());
		facts.put(RuleConstants.FACT_CARD, card);
		return ruleEngine.evaluate(rules, facts);
	}

	@Override
	public Map<Suit, Set<Card>> getCardOnTable() {
		return cardsOnTable;
	}
}
