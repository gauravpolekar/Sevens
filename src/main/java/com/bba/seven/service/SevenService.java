package com.bba.seven.service;

import com.bba.seven.SevenOfHearts;
import com.bba.seven.beans.Game;
import com.bba.seven.beans.GameDeck;
import com.bba.seven.beans.Player;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SevenService {

	private Map<String, Game> games = new HashMap<>();

	public Game createGame() {
		Game game = new SevenOfHearts();
		games.put(game.name(), game);
		return game;
	}

	public void addPlayerToGame(String gameName, Player player) {
		games.get(gameName).addPlayerToGame(player);
	}

	public void startGame(String gameName) {
		games.get(gameName).startGame();
	}

	public GameDeck showDeck() {
		return new SevenOfHearts().getDeck();
	}
}
