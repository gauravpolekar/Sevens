package com.bba.seven.beans;

import com.bba.seven.enums.GameStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameUIBean {
	String gameId;
	GameStatus status;
	List<Player> players;
}
