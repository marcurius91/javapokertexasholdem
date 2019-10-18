package com.cantero.games.poker.texasholdem;

import java.io.IOException;

public class UltimatePkGame {

	public static void main(String[] args) throws IOException {
		GameTexasHoldem game = new GameTexasHoldem();
		IPlayer player = new Player();
		IPlayer dealer = new Dealer();
		game.newGame(new Deck(), dealer, player);
		game.deal();
		System.out.println(dealer.toString());
		System.out.println(player.toString());
		game.callFlop();
		System.out.println(player.toString());
		game.showTurn();
		System.out.println(player.toString());
		game.showRiver();
		System.out.println(player.toString());
		System.out.println("Winner:"+ game.getWinner());
	}
}
