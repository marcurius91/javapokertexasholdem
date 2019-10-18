package com.cantero.games.poker.texasholdem;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.cantero.games.poker.texasholdem.Card;
import com.cantero.games.poker.texasholdem.Player;
import com.cantero.games.poker.texasholdem.RankingUtil;

import static com.cantero.games.poker.texasholdem.CardRankEnum.*;
import static com.cantero.games.poker.texasholdem.CardSuitEnum.*;
import static com.cantero.games.poker.texasholdem.RankingEnum.*;

public class RankingTest extends TestCase {

	/*
	 * 	01) ROYAL_FLUSH,
	 *	02) STRAIGHT_FLUSH,
	 *	03) FOUR_OF_A_KIND,
	 *	04) FULL_HOUSE,
	 *	05) FLUSH,
	 *	06) STRAIGHT,
	 *	07) THREE_OF_A_KIND,
	 *	08) TWO_PAIR,
	 *	09) ONE_PAIR,
	 *	10) HIGH_CARD
	 */
	@Test
	public void testCheckRoyalFlush() {
		List<Card> tableCards = new ArrayList<Card>();
		Player player = new Player();
		setRoyalFlush(player, tableCards);
		RankingUtil.checkRanking(player, tableCards);
		assertEquals(ROYAL_FLUSH, player.getRankingEnum());
		assertEquals(RankingUtil.getRoyalFlush(player, tableCards), player
				.getRankingList());
		assertEquals(new Integer(ROYAL_FLUSH.ordinal()), RankingUtil
				.getRankingToInt(player));
	}

	@Test
	public void testCheckStraightFlush() {
		List<Card> tableCards = new ArrayList<Card>();
		Player player = new Player();
		setStraightFlush(player, tableCards);
		RankingUtil.checkRanking(player, tableCards);
		assertEquals(STRAIGHT_FLUSH, player.getRankingEnum());
		assertEquals(RankingUtil.getStraightFlush(player, tableCards), player
				.getRankingList());
		assertEquals(new Integer(STRAIGHT_FLUSH.ordinal()), RankingUtil
				.getRankingToInt(player));
	}

	@Test
	public void testCheckFourOfAKind() {
		List<Card> tableCards = new ArrayList<Card>();
		Player player = new Player();
		setFourOfAKind(player, tableCards);
		RankingUtil.checkRanking(player, tableCards);
		assertEquals(FOUR_OF_A_KIND, player.getRankingEnum());
		assertEquals(RankingUtil.getFourOfAKind(player, tableCards), player
				.getRankingList());
		assertEquals(new Integer(FOUR_OF_A_KIND.ordinal()), RankingUtil
				.getRankingToInt(player));
	}

	@Test
	public void testCheckFullHouse() {
		List<Card> tableCards = new ArrayList<Card>();
		Player player = new Player();
		setFullHouse(player, tableCards);
		RankingUtil.checkRanking(player, tableCards);
		assertEquals(FULL_HOUSE, player.getRankingEnum());
		assertEquals(RankingUtil.getFullHouse(player, tableCards), player
				.getRankingList());
		assertEquals(new Integer(FULL_HOUSE.ordinal()), RankingUtil
				.getRankingToInt(player));
	}

	@Test
	public void testCheckFlush() {
		List<Card> tableCards = new ArrayList<Card>();
		Player player = new Player();
		setFlush(player, tableCards);
		RankingUtil.checkRanking(player, tableCards);
		assertEquals(FLUSH, player.getRankingEnum());
		assertEquals(RankingUtil.getFlush(player, tableCards), player
				.getRankingList());
		assertEquals(new Integer(FLUSH.ordinal()), RankingUtil
				.getRankingToInt(player));
	}

	@Test
	public void testCheckStraight() {
		List<Card> tableCards = new ArrayList<Card>();
		Player player = new Player();
		setStraight(player, tableCards);
		RankingUtil.checkRanking(player, tableCards);
		assertEquals(STRAIGHT, player.getRankingEnum());
		assertEquals(RankingUtil.getStraight(player, tableCards), player
				.getRankingList());
		assertEquals(new Integer(STRAIGHT.ordinal()), RankingUtil
				.getRankingToInt(player));
	}

	@Test
	public void testCheckThreeOfAKind() {
		List<Card> tableCards = new ArrayList<Card>();
		Player player = new Player();
		setThreeOfAKind(player, tableCards);
		RankingUtil.checkRanking(player, tableCards);
		assertEquals(THREE_OF_A_KIND, player.getRankingEnum());
		assertEquals(RankingUtil.getThreeOfAKind(player, tableCards), player
				.getRankingList());
		assertEquals(new Integer(THREE_OF_A_KIND.ordinal()), RankingUtil
				.getRankingToInt(player));
	}

	@Test
	public void testCheckTwoPair() {
		List<Card> tableCards = new ArrayList<Card>();
		Player player = new Player();
		setTwoPair(player, tableCards);
		RankingUtil.checkRanking(player, tableCards);
		assertEquals(TWO_PAIR, player.getRankingEnum());
		assertEquals(RankingUtil.getTwoPair(player, tableCards), player
				.getRankingList());
		assertEquals(new Integer(TWO_PAIR.ordinal()), RankingUtil
				.getRankingToInt(player));
	}

	@Test
	public void testCheckOnePair() {
		List<Card> tableCards = new ArrayList<Card>();
		Player player = new Player();
		setOnePair(player, tableCards);
		RankingUtil.checkRanking(player, tableCards);
		assertEquals(ONE_PAIR, player.getRankingEnum());
		assertEquals(RankingUtil.getOnePair(player, tableCards), player
				.getRankingList());
		assertEquals(new Integer(ONE_PAIR.ordinal()), RankingUtil
				.getRankingToInt(player));
	}

	@Test
	public void testCheckHighCard() {
		List<Card> tableCards = new ArrayList<Card>();
		Player player = new Player();
		setHighCard(player, tableCards);
		RankingUtil.checkRanking(player, tableCards);
		assertEquals(HIGH_CARD, player.getRankingEnum());
		assertEquals(RankingUtil.getHighCard(player, tableCards), player
				.getRankingList().get(0));
		assertEquals(new Integer(HIGH_CARD.ordinal()), RankingUtil
				.getRankingToInt(player));
	}

	@Test
	public void testIsRoyalFlush() {
		List<Card> tableCards = new ArrayList<Card>();
		Player player = new Player();
		setRoyalFlush(player, tableCards);

		List<Card> royalFlushList = new ArrayList<Card>();
		royalFlushList.addAll(tableCards);
		royalFlushList.add(player.getCards()[0]);
		royalFlushList.add(player.getCards()[1]);

		List<Card> result = RankingUtil.getRoyalFlush(player, tableCards);
		assertTrue(isSameCardList(royalFlushList, result));
	}

	@Test
	public void testIsRoyalFlushNotSequence() {
		List<Card> tableCards = new ArrayList<Card>();
		Player player = new Player();
		player.getCards()[0] = new Card(CLUBS, _J);
		player.getCards()[1] = new Card(CLUBS, _10);

		tableCards.add(new Card(CLUBS, _Q));
		tableCards.add(new Card(CLUBS, _A));
		tableCards.add(new Card(CLUBS, _K));

		List<Card> royalFlushList = new ArrayList<Card>();
		royalFlushList.addAll(tableCards);
		royalFlushList.add(player.getCards()[0]);
		royalFlushList.add(player.getCards()[1]);

		List<Card> result = RankingUtil.getRoyalFlush(player, tableCards);
		assertTrue(isSameCardList(royalFlushList, result));
	}

	@Test
	public void testIsNotRoyalFlushNotSameSuit() {
		List<Card> tableCards = new ArrayList<Card>();
		Player player = new Player();
		player.getCards()[0] = new Card(CLUBS, _A);
		player.getCards()[1] = new Card(HEARTS, _10);

		tableCards.add(new Card(CLUBS, _2));
		tableCards.add(new Card(SPADES, _3));
		tableCards.add(new Card(SPADES, _4));

		assertNull(RankingUtil.getRoyalFlush(player, tableCards));
	}

	@Test
	public void testIsStraightFlush() {
		List<Card> tableCards = new ArrayList<Card>();
		Player player = new Player();
		setStraightFlush(player, tableCards);

		List<Card> straightFlushList = new ArrayList<Card>();
		straightFlushList.addAll(tableCards);
		straightFlushList.add(player.getCards()[0]);
		straightFlushList.add(player.getCards()[1]);

		List<Card> result = RankingUtil.getStraightFlush(player, tableCards);
		assertTrue(isSameCardList(straightFlushList, result));
	}

	@Test
	public void testIsStraightFlushNotSequence() {
		List<Card> tableCards = new ArrayList<Card>();
		Player player = new Player();
		player.getCards()[0] = new Card(CLUBS, _2);
		player.getCards()[1] = new Card(CLUBS, _3);

		tableCards.add(new Card(CLUBS, _4));
		tableCards.add(new Card(CLUBS, _8));
		tableCards.add(new Card(CLUBS, _6));

		assertNull(RankingUtil.getStraightFlush(player, tableCards));
	}

	@Test
	public void testIsNotStraightFlushNoSameSuit() {
		List<Card> tableCards = new ArrayList<Card>();
		Player player = new Player();
		player.getCards()[0] = new Card(CLUBS, _2);
		player.getCards()[1] = new Card(CLUBS, _3);

		tableCards.add(new Card(CLUBS, _4));
		tableCards.add(new Card(CLUBS, _5));
		tableCards.add(new Card(DIAMONDS, _6));

		assertNull(RankingUtil.getStraightFlush(player, tableCards));
	}

	@Test
	public void testIsFourOfAKind() {
		List<Card> tableCards = new ArrayList<Card>();
		Player player = new Player();
		setFourOfAKind(player, tableCards);

		List<Card> fourOfAKindList = new ArrayList<Card>();
		fourOfAKindList.add(tableCards.get(0));
		fourOfAKindList.add(tableCards.get(2));
		fourOfAKindList.add(player.getCards()[0]);
		fourOfAKindList.add(player.getCards()[1]);

		List<Card> result = RankingUtil.getFourOfAKind(player, tableCards);
		assertTrue(isSameCardList(fourOfAKindList, result));
	}

	@Test
	public void testIsNotFourOfAKind() {
		List<Card> tableCards = new ArrayList<Card>();
		Player player = new Player();
		player.getCards()[0] = new Card(DIAMONDS, _10);
		player.getCards()[1] = new Card(CLUBS, _10);

		tableCards.add(new Card(CLUBS, _10));
		tableCards.add(new Card(HEARTS, _K));
		tableCards.add(new Card(CLUBS, _A));

		assertNull(RankingUtil.getFourOfAKind(player, tableCards));
	}

	@Test
	public void testIsFullHouse() {
		List<Card> tableCards = new ArrayList<Card>();
		Player player = new Player();
		setFullHouse(player, tableCards);

		List<Card> fullHouseList = new ArrayList<Card>();
		fullHouseList.add(player.getCards()[0]);
		fullHouseList.add(tableCards.get(1));
		fullHouseList.add(tableCards.get(2));
		fullHouseList.add(player.getCards()[1]);
		fullHouseList.add(tableCards.get(0));

		List<Card> result = RankingUtil.getFullHouse(player, tableCards);
		assertTrue(isSameCardList(fullHouseList, result));
	}

	@Test
	public void testIsNotFullHouse() {
		Card cardThree1 = new Card(CLUBS, _10);
		Card cardThree2 = new Card(HEARTS, _A);
		Card cardThree3 = new Card(CLUBS, _10);

		Card cardTwo1 = new Card(CLUBS, _J);
		Card cardTwo2 = new Card(HEARTS, _J);

		List<Card> tableCards = new ArrayList<Card>();
		Player player = new Player();
		player.getCards()[0] = cardThree3;
		player.getCards()[1] = cardTwo2;

		tableCards.add(cardTwo1);
		tableCards.add(cardThree2);
		tableCards.add(cardThree1);

		List<Card> result = RankingUtil.getFullHouse(player, tableCards);
		assertNull(result);
	}

	@Test
	public void testIsFlush() {
		List<Card> tableCards = new ArrayList<Card>();
		Player player = new Player();
		setFlush(player, tableCards);

		List<Card> flushList = new ArrayList<Card>();
		flushList.addAll(tableCards);
		flushList.add(player.getCards()[0]);
		flushList.add(player.getCards()[1]);

		List<Card> result = RankingUtil.getFlush(player, tableCards);
		assertTrue(isSameCardList(flushList, result));
	}

	@Test
	public void testIsNotFlush() {
		List<Card> tableCards = new ArrayList<Card>();
		Player player = new Player();
		player.getCards()[0] = new Card(CLUBS, _10);
		player.getCards()[1] = new Card(CLUBS, _10);

		tableCards.add(new Card(HEARTS, _2));
		tableCards.add(new Card(CLUBS, _K));
		tableCards.add(new Card(CLUBS, _A));

		assertNull(RankingUtil.getFlush(player, tableCards));
	}

	@Test
	public void testIsStraight() {
		List<Card> tableCards = new ArrayList<Card>();
		Player player = new Player();
		setStraight(player, tableCards);

		List<Card> straightList = new ArrayList<Card>();
		straightList.addAll(tableCards);
		straightList.add(player.getCards()[0]);
		straightList.add(player.getCards()[1]);

		List<Card> result = RankingUtil.getStraight(player, tableCards);
		assertTrue(isSameCardList(straightList, result));
	}

	@Test
	public void testIsNotStraight() {
		List<Card> tableCards = new ArrayList<Card>();
		Player player = new Player();
		player.getCards()[0] = new Card(DIAMONDS, _2);
		player.getCards()[1] = new Card(CLUBS, _3);

		tableCards.add(new Card(CLUBS, _8));
		tableCards.add(new Card(HEARTS, _2));
		tableCards.add(new Card(SPADES, _6));

		assertNull(RankingUtil.getStraight(player, tableCards));
	}

	@Test
	public void testIsThreeOfAKind() {
		List<Card> tableCards = new ArrayList<Card>();
		Player player = new Player();
		setThreeOfAKind(player, tableCards);

		List<Card> listThreeOfAKind = new ArrayList<Card>();

		listThreeOfAKind.add(tableCards.get(1));
		listThreeOfAKind.add(player.getCards()[0]);
		listThreeOfAKind.add(player.getCards()[1]);

		List<Card> result = RankingUtil.getThreeOfAKind(player, tableCards);
		assertTrue(isSameCardList(listThreeOfAKind, result));
	}

	@Test
	public void testIsNotThreeOfAKind() {
		List<Card> tableCards = new ArrayList<Card>();
		Player player = new Player();
		player.getCards()[0] = new Card(DIAMONDS, _10);
		player.getCards()[1] = new Card(CLUBS, _10);

		tableCards.add(new Card(CLUBS, _2));
		tableCards.add(new Card(CLUBS, _K));
		tableCards.add(new Card(CLUBS, _A));

		assertNull(RankingUtil.getThreeOfAKind(player, tableCards));
	}

	@Test
	public void testIsTwoPair() {
		List<Card> tableCards = new ArrayList<Card>();
		Player player = new Player();
		setTwoPair(player, tableCards);

		List<Card> fullHouseList = new ArrayList<Card>();
		fullHouseList.add(player.getCards()[0]);
		fullHouseList.add(tableCards.get(0));
		fullHouseList.add(player.getCards()[1]);
		fullHouseList.add(tableCards.get(1));

		List<Card> result = RankingUtil.getTwoPair(player, tableCards);
		assertTrue(isSameCardList(fullHouseList, result));
	}

	@Test
	public void testIsNotTwoPair() {
		Card cardThree1 = new Card(CLUBS, _10);
		Card cardThree2 = new Card(HEARTS, _10);
		Card cardThree3 = new Card(SPADES, _10);

		Card cardTwo1 = new Card(CLUBS, _J);
		Card cardTwo2 = new Card(HEARTS, _J);

		List<Card> tableCards = new ArrayList<Card>();
		Player player = new Player();
		player.getCards()[0] = cardThree3;
		player.getCards()[1] = cardTwo2;

		tableCards.add(cardTwo1);
		tableCards.add(cardThree2);
		tableCards.add(cardThree1);

		List<Card> result = RankingUtil.getTwoPair(player, tableCards);
		assertNull(result);
	}

	@Test
	public void testIsOnePair() {
		List<Card> tableCards = new ArrayList<Card>();
		Player player = new Player();
		setOnePair(player, tableCards);

		List<Card> listOnePair = new ArrayList<Card>();
		listOnePair.add(player.getCards()[0]);
		listOnePair.add(player.getCards()[1]);

		List<Card> result = RankingUtil.getOnePair(player, tableCards);
		assertTrue(isSameCardList(listOnePair, result));
	}

	@Test
	public void testIsNotOnePair() {
		List<Card> tableCards = new ArrayList<Card>();
		Player player = new Player();
		player.getCards()[0] = new Card(DIAMONDS, _2);
		player.getCards()[1] = new Card(CLUBS, _10);

		tableCards.add(new Card(CLUBS, _3));
		tableCards.add(new Card(CLUBS, _K));
		tableCards.add(new Card(CLUBS, _A));

		assertNull(RankingUtil.getOnePair(player, tableCards));
	}

	@Test
	public void testGetHighCardRepeatedCards() {
		List<Card> tableCards = new ArrayList<Card>();
		Player player = new Player();
		Card fourCard = new Card(CLUBS, _4);
		player.getCards()[0] = fourCard;
		player.getCards()[1] = fourCard;

		tableCards.add(fourCard);
		tableCards.add(fourCard);
		tableCards.add(new Card(CLUBS, _2));

		assertEquals(fourCard, RankingUtil.getHighCard(player, tableCards));
	}

	@Test
	public void testGetHighCardAce() {
		List<Card> tableCards = new ArrayList<Card>();
		Player player = new Player();
		player.getCards()[0] = new Card(HEARTS, _9);
		player.getCards()[1] = new Card(SPADES, _7);

		Card aceCard = new Card(CLUBS, _A);
		tableCards.add(aceCard);
		assertEquals(aceCard, RankingUtil.getHighCard(player, tableCards));
	}

	public Boolean isSameCardList(List<Card> list1, List<Card> list2) {
		return list1.containsAll(list2) && list1.size() == list2.size();
	}

	private void setRoyalFlush(Player player, List<Card> tableCards) {
		player.getCards()[0] = new Card(CLUBS, _10);
		player.getCards()[1] = new Card(CLUBS, _J);

		tableCards.add(new Card(CLUBS, _Q));
		tableCards.add(new Card(CLUBS, _K));
		tableCards.add(new Card(CLUBS, _A));
	}

	private void setStraightFlush(Player player, List<Card> tableCards) {
		player.getCards()[0] = new Card(CLUBS, _2);
		player.getCards()[1] = new Card(CLUBS, _3);

		tableCards.add(new Card(CLUBS, _4));
		tableCards.add(new Card(CLUBS, _5));
		tableCards.add(new Card(CLUBS, _6));
	}

	private void setFourOfAKind(Player player, List<Card> tableCards) {
		player.getCards()[0] = new Card(DIAMONDS, _10);
		player.getCards()[1] = new Card(CLUBS, _10);

		tableCards.add(new Card(CLUBS, _10));
		tableCards.add(new Card(CLUBS, _K));
		tableCards.add(new Card(HEARTS, _10));
	}

	private void setFullHouse(Player player, List<Card> tableCards) {
		player.getCards()[0] = new Card(CLUBS, _10);
		player.getCards()[1] = new Card(HEARTS, _J);

		tableCards.add(new Card(CLUBS, _J));
		tableCards.add(new Card(HEARTS, _10));
		tableCards.add(new Card(CLUBS, _10));
	}

	private void setFlush(Player player, List<Card> tableCards) {
		player.getCards()[0] = new Card(CLUBS, _10);
		player.getCards()[1] = new Card(CLUBS, _3);

		tableCards.add(new Card(CLUBS, _2));
		tableCards.add(new Card(CLUBS, _K));
		tableCards.add(new Card(CLUBS, _A));
	}

	private void setStraight(Player player, List<Card> tableCards) {
		player.getCards()[0] = new Card(DIAMONDS, _4);
		player.getCards()[1] = new Card(CLUBS, _5);

		tableCards.add(new Card(CLUBS, _2));
		tableCards.add(new Card(HEARTS, _3));
		tableCards.add(new Card(SPADES, _6));
	}

	private void setThreeOfAKind(Player player, List<Card> tableCards) {
		player.getCards()[0] = new Card(CLUBS, _10);
		player.getCards()[1] = new Card(SPADES, _10);
		tableCards.add(new Card(SPADES, _A));
		tableCards.add(new Card(HEARTS, _10));
		tableCards.add(new Card(HEARTS, _2));
	}

	private void setTwoPair(Player player, List<Card> tableCards) {
		player.getCards()[0] = new Card(CLUBS, _10);
		player.getCards()[1] = new Card(CLUBS, _J);

		tableCards.add(new Card(SPADES, _10));
		tableCards.add(new Card(HEARTS, _J));
	}

	private void setOnePair(Player player, List<Card> tableCards) {
		player.getCards()[0] = new Card(DIAMONDS, _10);
		player.getCards()[1] = new Card(CLUBS, _10);

		tableCards.add(new Card(CLUBS, _2));
		tableCards.add(new Card(CLUBS, _K));
		tableCards.add(new Card(CLUBS, _A));
	}

	private void setHighCard(Player player, List<Card> tableCards) {
		player.getCards()[0] = new Card(DIAMONDS, _10);
		player.getCards()[1] = new Card(CLUBS, _9);

		tableCards.add(new Card(CLUBS, _2));
		tableCards.add(new Card(CLUBS, _K));
		tableCards.add(new Card(CLUBS, _A));
	}
}
