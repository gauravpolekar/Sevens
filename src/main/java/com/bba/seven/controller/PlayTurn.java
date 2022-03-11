package com.bba.seven.controller;

import com.bba.seven.beans.Card;
import com.bba.seven.beans.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayTurn {
	Player player;
	Card card;
}
