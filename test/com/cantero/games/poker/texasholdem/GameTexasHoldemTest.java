package com.cantero.games.poker.texasholdem;

import static com.cantero.games.poker.texasholdem.CardRankEnum.*;
import static com.cantero.games.poker.texasholdem.CardSuitEnum.*;
import static com.cantero.games.poker.texasholdem.RankingEnum.*;
import static junit.framework.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.cantero.games.poker.texasholdem.Card;
import com.cantero.games.poker.texasholdem.Deck;
import com.cantero.games.poker.texasholdem.GameTexasHoldem;
import com.cantero.games.poker.texasholdem.IPlayer;
import com.cantero.games.poker.texasholdem.Player;

public class GameTexasHoldemTest {

	@Test
	public void testDrawGameFourPlayers() {
		//Basic tests
		GameTexasHoldem game = new GameTexasHoldem();
		IPlayer player1 = new Player();
		IPlayer player2 = new Player();
		IPlayer player3 = new Player();
		IPlayer player4 = new Player();
		game.newGame(new Deck(), player1, player2, player3, player4);
		game.deal();
		game.callFlop();
		game.showRiver();
		game.showTurn();
		game.getTableCards().clear();
		game.getTableCards().add(new Card(SPADES, _10));
		game.getTableCards().add(new Card(SPADES, _A));
		player1.getCards()[0] = new Card(DIAMONDS, _2);
		player1.getCards()[1] = new Card(SPADES, _3);
		player2.getCards()[0] = new Card(CLUBS, _2);
		player2.getCards()[1] = new Card(HEARTS, _3);
		player3.getCards()[0] = new Card(SPADES, _2);
		player3.getCards()[1] = new Card(DIAMONDS, _3);
		player4.getCards()[0] = new Card(HEARTS, _2);
		player4.getCards()[1] = new Card(CLUBS, _3);
		List<IPlayer> winnerList = game.getWinner();
		assertEquals(4, winnerList.size());
	}

	@Test
	public void testDrawGameTwoPlayers() {
		//Basic tests
		GameTexasHoldem game = new GameTexasHoldem();
		IPlayer player = new Player();
		IPlayer dealer = new Player();
		game.newGame(new Deck(), player, dealer);
		game.deal();
		game.callFlop();
		game.showRiver();
		game.showTurn();
		game.getTableCards().clear();
		game.getTableCards().add(new Card(SPADES, _10));
		game.getTableCards().add(new Card(SPADES, _A));
		dealer.getCards()[0] = new Card(DIAMONDS, _2);
		dealer.getCards()[1] = new Card(SPADES, _2);
		player.getCards()[0] = new Card(CLUBS, _2);
		player.getCards()[1] = new Card(HEARTS, _2);
		List<IPlayer> winnerList = game.getWinner();
		assertEquals(2, winnerList.size());
		assertEquals(ONE_PAIR, dealer.getRankingEnum());
		assertEquals(ONE_PAIR, player.getRankingEnum());
	}

	@Test
	public void testPlayerWinDrawGameBestRanking() {
		//Basic tests
		GameTexasHoldem game = new GameTexasHoldem();
		IPlayer player = new Player();
		IPlayer dealer = new Player();
		game.newGame(new Deck(), player, dealer);
		game.deal();
		game.callFlop();
		game.showRiver();
		game.showTurn();
		game.getTableCards().clear();
		game.getTableCards().add(new Card(SPADES, _10));
		game.getTableCards().add(new Card(SPADES, _A));
		dealer.getCards()[0] = new Card(CLUBS, _10);
		dealer.getCards()[1] = new Card(HEARTS, _2);
		player.getCards()[0] = new Card(CLUBS, _2);
		player.getCards()[1] = new Card(HEARTS, _A);
		//assertEquals(GameEnum.PLAYER_WINNER_BEST_RANKING, game.getWinner());
		List<IPlayer> winnerList = game.getWinner();
		assertEquals(1, winnerList.size());
		assertEquals(player, winnerList.get(0));
		assertEquals(ONE_PAIR, dealer.getRankingEnum());
		assertEquals(ONE_PAIR, player.getRankingEnum());
	}

	@Test
	public void testDealerWinDrawGameHighCard() {
		//Basic tests
		GameTexasHoldem game = new GameTexasHoldem();
		IPlayer player = new Player();
		IPlayer dealer = new Player();
		game.newGame(new Deck(), player, dealer);
		game.deal();
		game.callFlop();
		game.showRiver();
		game.showTurn();
		game.getTableCards().clear();
		game.getTableCards().add(new Card(SPADES, _10));
		dealer.getCards()[0] = new Card(CLUBS, _10);
		dealer.getCards()[1] = new Card(HEARTS, _A);
		player.getCards()[0] = new Card(CLUBS, _2);
		player.getCards()[1] = new Card(HEARTS, _10);
		//assertEquals(GameEnum.DEALER_WINNER_HIGH_CARD, game.getWinner());
		List<IPlayer> winnerList = game.getWinner();
		assertEquals(1, winnerList.size());
		assertEquals(dealer, winnerList.get(0));
		assertEquals(ONE_PAIR, dealer.getRankingEnum());
		assertEquals(ONE_PAIR, player.getRankingEnum());
	}

	@Test
	public void testDealerWinStraighFlush() {
		//Basic tests
		GameTexasHoldem game = new GameTexasHoldem();
		IPlayer player1 = new Player();
		IPlayer player2 = new Player();
		IPlayer dealer = new Player();
		game.newGame(new Deck(), player1, player2, dealer);
		game.deal();
		game.callFlop();
		game.showRiver();
		game.showTurn();
		game.getTableCards().clear();
		game.getTableCards().add(new Card(SPADES, _3));
		game.getTableCards().add(new Card(SPADES, _4));
		game.getTableCards().add(new Card(SPADES, _5));
		game.getTableCards().add(new Card(CLUBS, _Q));
		dealer.getCards()[0] = new Card(SPADES, _6);
		dealer.getCards()[1] = new Card(SPADES, _7);
		player1.getCards()[0] = new Card(SPADES, _10);
		player1.getCards()[1] = new Card(DIAMONDS, _10);
		player2.getCards()[0] = new Card(SPADES, _2);
		player2.getCards()[1] = new Card(DIAMONDS, _2);
		//assertEquals(GameEnum.DEALER_WINNER, game.getWinner());
		List<IPlayer> winnerList = game.getWinner();
		assertEquals(1, winnerList.size());
		assertEquals(dealer, winnerList.get(0));
		assertEquals(STRAIGHT_FLUSH, dealer.getRankingEnum());
		assertEquals(ONE_PAIR, player1.getRankingEnum());
	}

	@Test
	public void testPlayerWinFourOfAKind() {
		//Basic tests
		GameTexasHoldem game = new GameTexasHoldem();
		IPlayer player = new Player();
		IPlayer dealer = new Player();
		game.newGame(new Deck(), player, dealer);
		game.deal();
		game.callFlop();
		game.showRiver();
		game.showTurn();
		game.getTableCards().clear();
		game.getTableCards().add(new Card(SPADES, _3));
		game.getTableCards().add(new Card(SPADES, _4));
		game.getTableCards().add(new Card(HEARTS, _10));
		game.getTableCards().add(new Card(CLUBS, _10));
		dealer.getCards()[0] = new Card(SPADES, _A);
		dealer.getCards()[1] = new Card(SPADES, _2);
		player.getCards()[0] = new Card(SPADES, _10);
		player.getCards()[1] = new Card(DIAMONDS, _10);
		//assertEquals(GameEnum.PLAYER_WINNER, game.getWinner());
		List<IPlayer> winnerList = game.getWinner();
		assertEquals(1, winnerList.size());
		assertEquals(player, winnerList.get(0));
		assertEquals(ONE_PAIR, dealer.getRankingEnum());
		assertEquals(FOUR_OF_A_KIND, player.getRankingEnum());
	}

	@Test
	public void testDealerWinFullHouse() {
		//Basic tests
		GameTexasHoldem game = new GameTexasHoldem();
		IPlayer player = new Player();
		IPlayer dealer = new Player();
		game.newGame(new Deck(), player, dealer);
		game.deal();
		game.callFlop();
		game.showRiver();
		game.showTurn();
		game.getTableCards().clear();
		game.getTableCards().add(new Card(SPADES, _3));
		game.getTableCards().add(new Card(SPADES, _4));
		game.getTableCards().add(new Card(HEARTS, _10));
		game.getTableCards().add(new Card(CLUBS, _10));
		dealer.getCards()[0] = new Card(HEARTS, _3);
		dealer.getCards()[1] = new Card(CLUBS, _3);
		player.getCards()[0] = new Card(SPADES, _10);
		player.getCards()[1] = new Card(SPADES, _2);
		//assertEquals(GameEnum.DEALER_WINNER, game.getWinner());
		List<IPlayer> winnerList = game.getWinner();
		assertEquals(1, winnerList.size());
		assertEquals(dealer, winnerList.get(0));
		assertEquals(FULL_HOUSE, dealer.getRankingEnum());
		assertEquals(THREE_OF_A_KIND, player.getRankingEnum());
	}

	@Test
	public void testPlayerWinFlush() {
		//Basic tests
		GameTexasHoldem game = new GameTexasHoldem();
		IPlayer player = new Player();
		IPlayer dealer = new Player();
		game.newGame(new Deck(), player, dealer);
		game.deal();
		game.callFlop();
		game.showRiver();
		game.showTurn();
		game.getTableCards().clear();
		game.getTableCards().add(new Card(SPADES, _3));
		game.getTableCards().add(new Card(SPADES, _4));
		game.getTableCards().add(new Card(SPADES, _7));
		dealer.getCards()[0] = new Card(HEARTS, _5);
		dealer.getCards()[1] = new Card(CLUBS, _6);
		player.getCards()[0] = new Card(SPADES, _10);
		player.getCards()[1] = new Card(SPADES, _2);
		//assertEquals(GameEnum.PLAYER_WINNER, game.getWinner());
		List<IPlayer> winnerList = game.getWinner();
		assertEquals(1, winnerList.size());
		assertEquals(player, winnerList.get(0));
		assertEquals(STRAIGHT, dealer.getRankingEnum());
		assertEquals(FLUSH, player.getRankingEnum());
	}

	@Test
	public void testDealerWinStraight() {
		//Basic tests
		GameTexasHoldem game = new GameTexasHoldem();
		IPlayer player = new Player();
		IPlayer dealer = new Player();
		game.newGame(new Deck(), player, dealer);
		game.deal();
		game.callFlop();
		game.showRiver();
		game.showTurn();
		game.getTableCards().clear();
		game.getTableCards().add(new Card(SPADES, _3));
		game.getTableCards().add(new Card(SPADES, _4));
		game.getTableCards().add(new Card(SPADES, _7));
		dealer.getCards()[0] = new Card(HEARTS, _5);
		dealer.getCards()[1] = new Card(CLUBS, _6);
		player.getCards()[0] = new Card(CLUBS, _10);
		player.getCards()[1] = new Card(HEARTS, _10);
		//assertEquals(GameEnum.DEALER_WINNER, game.getWinner());
		List<IPlayer> winnerList = game.getWinner();
		assertEquals(1, winnerList.size());
		assertEquals(dealer, winnerList.get(0));
		assertEquals(STRAIGHT, dealer.getRankingEnum());
		assertEquals(ONE_PAIR, player.getRankingEnum());
	}

	@Test
	public void testDealerWinThreeOfAKind() {
		//Basic tests
		GameTexasHoldem game = new GameTexasHoldem();
		IPlayer player = new Player();
		IPlayer dealer = new Player();
		game.newGame(new Deck(), player, dealer);
		game.deal();
		game.callFlop();
		game.showRiver();
		game.showTurn();
		game.getTableCards().clear();
		game.getTableCards().add(new Card(SPADES, _10));
		game.getTableCards().add(new Card(SPADES, _A));
		dealer.getCards()[0] = new Card(HEARTS, _A);
		dealer.getCards()[1] = new Card(CLUBS, _A);
		player.getCards()[0] = new Card(CLUBS, _6);
		player.getCards()[1] = new Card(HEARTS, _Q);
		//assertEquals(GameEnum.DEALER_WINNER, game.getWinner());
		List<IPlayer> winnerList = game.getWinner();
		assertEquals(1, winnerList.size());
		assertEquals(dealer, winnerList.get(0));
		assertEquals(THREE_OF_A_KIND, dealer.getRankingEnum());
		assertEquals(HIGH_CARD, player.getRankingEnum());
	}

	@Test
	public void testPlayerWinTwoPair() {
		//Basic tests
		GameTexasHoldem game = new GameTexasHoldem();
		IPlayer player = new Player();
		IPlayer dealer = new Player();
		game.newGame(new Deck(), player, dealer);
		game.deal();
		game.callFlop();
		game.showRiver();
		game.showTurn();
		game.getTableCards().clear();
		game.getTableCards().add(new Card(SPADES, _10));
		game.getTableCards().add(new Card(SPADES, _A));
		dealer.getCards()[0] = new Card(HEARTS, _10);
		dealer.getCards()[1] = new Card(HEARTS, _Q);
		player.getCards()[0] = new Card(CLUBS, _10);
		player.getCards()[1] = new Card(HEARTS, _A);
		//assertEquals(GameEnum.PLAYER_WINNER, game.getWinner());
		List<IPlayer> winnerList = game.getWinner();
		assertEquals(1, winnerList.size());
		assertEquals(player, winnerList.get(0));
		assertEquals(ONE_PAIR, dealer.getRankingEnum());
		assertEquals(TWO_PAIR, player.getRankingEnum());
	}

	@Test
	public void testDealerWinOnePair() {
		//Basic tests
		GameTexasHoldem game = new GameTexasHoldem();
		IPlayer player = new Player();
		IPlayer dealer = new Player();
		game.newGame(new Deck(), player, dealer);
		game.deal();
		game.callFlop();
		game.showRiver();
		game.showTurn();
		game.getTableCards().clear();
		game.getTableCards().add(new Card(SPADES, _10));
		dealer.getCards()[0] = new Card(CLUBS, _10);
		dealer.getCards()[1] = new Card(HEARTS, _7);
		player.getCards()[0] = new Card(CLUBS, _A);
		player.getCards()[1] = new Card(HEARTS, _Q);
		//assertEquals(GameEnum.DEALER_WINNER, game.getWinner());
		List<IPlayer> winnerList = game.getWinner();
		assertEquals(1, winnerList.size());
		assertEquals(dealer, winnerList.get(0));
		assertEquals(ONE_PAIR, dealer.getRankingEnum());
		assertEquals(HIGH_CARD, player.getRankingEnum());
	}

	@Test
	public void testDrawGameOnePairDealerWinSecondHighCard() {
		//Basic tests
		GameTexasHoldem game = new GameTexasHoldem();
		IPlayer player = new Player();
		IPlayer dealer = new Player();
		game.newGame(new Deck(), player, dealer);
		game.deal();
		game.callFlop();
		game.showRiver();
		game.showTurn();
		game.getTableCards().clear();
		game.getTableCards().add(new Card(DIAMONDS, _6));
		game.getTableCards().add(new Card(CLUBS, _Q));
		game.getTableCards().add(new Card(DIAMONDS, _9));
		game.getTableCards().add(new Card(DIAMONDS, _A));
		game.getTableCards().add(new Card(CLUBS, _6));
		dealer.getCards()[0] = new Card(DIAMONDS, _10);
		Card highCardDealer = new Card(SPADES, _2);
		dealer.getCards()[1] = highCardDealer;
		player.getCards()[0] = new Card(HEARTS, _10);
		Card highCardPlayer = new Card(SPADES, _3);
		player.getCards()[1] = highCardPlayer;
		//assertEquals(GameEnum.PLAYER_WINNER_HIGH_CARD, game.getWinner());
		List<IPlayer> winnerList = game.getWinner();
		assertEquals(1, winnerList.size());
		assertEquals(player, winnerList.get(0));
		assertEquals(ONE_PAIR, dealer.getRankingEnum());
		assertEquals(ONE_PAIR, player.getRankingEnum());
		assertEquals(highCardPlayer, player.getHighCard());
		assertEquals(highCardDealer, dealer.getHighCard());
	}

	@Test
	public void testPlayerWinHighCard() {
		//Basic tests
		GameTexasHoldem game = new GameTexasHoldem();
		IPlayer player = new Player();
		IPlayer dealer = new Player();
		game.newGame(new Deck(), player, dealer);
		game.deal();
		game.callFlop();
		game.showRiver();
		game.showTurn();
		game.getTableCards().clear();
		game.getTableCards().add(new Card(SPADES, _4));
		dealer.getCards()[0] = new Card(CLUBS, _10);
		dealer.getCards()[1] = new Card(HEARTS, _7);
		player.getCards()[0] = new Card(CLUBS, _A);
		player.getCards()[1] = new Card(HEARTS, _Q);
		//assertEquals(GameEnum.PLAYER_WINNER_BEST_RANKING, game.getWinner());
		List<IPlayer> winnerList = game.getWinner();
		assertEquals(1, winnerList.size());
		assertEquals(player, winnerList.get(0));
		assertEquals(HIGH_CARD, dealer.getRankingEnum());
		assertEquals(HIGH_CARD, player.getRankingEnum());
	}
}
