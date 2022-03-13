package com.bba.seven.controller;

import com.bba.seven.beans.Card;
import com.bba.seven.beans.GameDeck;
import com.bba.seven.beans.GameUIBean;
import com.bba.seven.beans.Player;
import com.bba.seven.enums.Suit;
import com.bba.seven.exceptions.InvalidCardException;
import com.bba.seven.service.SevenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController()
@RequestMapping("/seven")
@CrossOrigin(origins = "*")
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

	@GetMapping("/table/{gameId}")
	public Map<Suit, Set<Card>> cardsOnTable(@PathVariable("gameId") String gameId) {
		return sevenService.getCardsOnTable(gameId);
	}

	@PostMapping("/playTurn/{gameId}")
	public void playCurrentPlayerTurn(@PathVariable("gameId") String gameId, @RequestBody PlayTurn playTurn) throws InvalidCardException {
		sevenService.playCurrentTurn(gameId, playTurn.getPlayer(), playTurn.getCard());
	}


	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ InvalidCardException.class })
	public String handleException(Exception e) {
		return e.getMessage();
	}
}
