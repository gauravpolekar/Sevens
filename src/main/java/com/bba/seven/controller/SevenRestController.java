package com.bba.seven.controller;

import com.bba.seven.beans.Card;
import com.bba.seven.beans.Game;
import com.bba.seven.beans.GameDeck;
import com.bba.seven.beans.GameUIBean;
import com.bba.seven.beans.Player;
import com.bba.seven.service.SevenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/seven")
@CrossOrigin(origins = "http://localhost:3000")
public class SevenRestController {

	@Autowired
	private SevenService sevenService;

	@GetMapping("/deck")
	public GameDeck showDeck() {
		return sevenService.showDeck();
	}

	@GetMapping("/createGame")
	public String createGame() {
		return sevenService.createGame().name();
	}

	@GetMapping("/{gameId}")
	public GameUIBean getGame(@PathVariable("gameId") String gameId) {
		return  sevenService.getGame(gameId);
	}

	@GetMapping("/{gameId}/{playerName}/cards")
	public List<Card> getMyCards(@PathVariable("gameId") String gameId,
	                             @PathVariable("playerName") String playerName) {
		return sevenService.getMyCards(gameId, playerName);
	}

	@PostMapping("player/add/{gameId}")
	public void addPlayer(@PathVariable("gameId") String gameId, @RequestBody Player player) {
		sevenService.addPlayerToGame(gameId, player);
	}

	@GetMapping("/start/{gameId}")
	public void startGame(@PathVariable("gameId") String gameId) {
		sevenService.startGame(gameId);
	}
}
