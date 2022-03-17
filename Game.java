import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Game {

	public static void main(String[] args) {

		List<Player> players = new ArrayList<>();

		players.add(new Player("Anisur"));
		players.add(new Player("Moksud"));
		players.add(new Player("Murad"));
		players.add(new Player("Morshed"));
		players.add(new Player("Runu"));
		playGame(players);

	}

	public static void playGame(List<Player> players) {

		Deck deck = new Deck();
		deck.createDeck();

		Deck playedCards = new Deck();
		System.out.println("DEALING CARDS");
		for (int j = 0; j < players.size(); j++) { // deal cards out to players
			for (int i = 0; i < 5; i++) {
				Card topOfDeck = deck.getTopCard();
				players.get(j).hand.add(topOfDeck);
				// since wild doesn't have a color, printouts will be invoked differently
				if (topOfDeck.isWild() & topOfDeck.getCardType().equals("wild")) {
					System.out.println(players.get(j).name + " is dealt a wild ");
				} else if (topOfDeck.isWild()) { // if it's wild but not only wild, then it's a wild draw 4
					System.out.println(players.get(j).name + " is dealt a wild " + topOfDeck.getCardType());
				} else {
					System.out.println(players.get(j).name + " is dealt a " + topOfDeck.getColor() + " "
							+ topOfDeck.getCardType());
				}
				deck.removeTopCard();
			}
		}
		Card playedCard = new Card();
		Card drawnCard = new Card();
		boolean gameWon = false;
		int playerIndex = 0; // players are indexed with a var because skips and reverses make for loops
								// useless
		int direction = 1; // direction will oscillate between 1 and -1 based on reverses played
		Card topCard = deck.getTopCard();
		while (topCard.isWild()) { // can't start with a wild card first
			deck.removeTopCard();
			playedCards.addCard(topCard);
			topCard = deck.getTopCard();
		}
		while (!gameWon) {
			int indexBy = 1; // index by one unless skip is played
			Player player = players.get(playerIndex);
			if (deck.size() == 0) { // checking if deck is empty
				System.out.println("Deck is empty. Played cards will be reshuffled and used as new deck.");
				deck = playedCards; // if deck is empty, played cards will be used as the new deck
				deck.shuffle();
				playedCards = new Deck(); // create an empty pile for played cards
			}
			List<Integer> playableCards = player.getPlayableCards(topCard);
			int numberOfPlayedCardsBefore = playedCards.size() - 1;
			if (Collections.max(playableCards) == 0) {
				drawCard(player, deck);
				drawnCard = player.hand.get(player.hand.size() - 1);
				if (!drawnCard.isWild()) {
					System.out.println(player.name + " has no playable cards. " + player.name + " drew a "
							+ drawnCard.getColor() + " " + drawnCard.getCardType());
				} else if (drawnCard.getCardType().equals("draw 4")) {
					System.out.println(player.name + " has no playable cards. " + player.name + " drew a wild draw 4");
				} else {
					System.out.println(player.name + " has no playable cards. " + player.name + " drew a wild");
				}
			} else {
				int handIndex = 0;
				for (int bool : playableCards) {
					if (bool == 1) {
						playedCard = player.hand.get(handIndex);

						if (!playedCard.isWild()) {
							System.out.println(player.name + " played a " + playedCard.getColor() + " "
									+ playedCard.getCardType());
						} else if (playedCard.getCardType().equals("draw 4")) {
							System.out.println(player.name + " played a wild draw 4");
						} else {
							System.out.println(player.name + " played a wild");
						}

						playedCards.addCard(playedCard);
						player.hand.remove(handIndex);
						break;
					} else {
						handIndex++;
					}
				}
			}

			if (player.hand.size() == 0) {
				gameWon = true;
				System.out.println("GAME OVER! " + player.name + " has won. Congratulations!");
				break;
			}
			topCard = playedCard;
			int numberOfPlayedCardsAfter = playedCards.size() - 1;

			/*
			 * when a player plays a wild card and the next player doesn't have the correct
			 * color, the top card stays the same. A check has to be made in order to see if
			 * the wild on top was just played or did someone draw and this is still the
			 * same wild from a previous turn. Without this check, the code would ask to
			 * pick a color again even though a new wild card wasn't played
			 */

			if (numberOfPlayedCardsBefore != numberOfPlayedCardsAfter) {
				if (playedCard.isWild()) {
					Scanner myObj = new Scanner(System.in); // user will pick next color
					System.out.println("Pick a color: ");
					String newColor = myObj.nextLine();
					// validate that the input is a valid color and no typos
					while (!(newColor.equals("red") | newColor.equals("blue") | newColor.equals("green")
							| newColor.equals("yellow"))) {
						System.out.println("Invalid color. Try again. Pick a color:");
						newColor = myObj.nextLine();
					}
					topCard.setColor(topCard, newColor);
				}
			}
			if (playedCard.getCardType().equals("reverse")) { // reverse direction if reverse is played
				direction = direction * -1;
			}
			// skips and draws all skip the next players turn
			if (playedCard.getCardType().equals("skip") | playedCard.getCardType().equals("draw 2")
					| playedCard.getCardType().equals("draw 4")) {
				indexBy = 2;
			} else {
				indexBy = 1;
			}
			int playerToDraw = playerIndex + 1 * direction; // only the next player one index away can ever be made to
															// draw
			playerIndex += indexBy * direction;
			playerIndex = correctIndex(playerIndex, players); // bring index within proper bounds

			if (topCard.getCardType().equals("draw 4")) {
				playerToDraw = correctIndex(playerToDraw, players);
				for (int i = 0; i < 4; i++) {
					if (deck.size() == 0) { // check to make sure deck isn't empty before drawing
						System.out.println("Deck is empty. Played cards will be reshuffled and used as new deck.");
						deck = playedCards;
						deck.shuffle();
						playedCards = new Deck();
					}
					drawCard(players.get(playerToDraw), deck);
					drawnCard = players.get(playerToDraw).hand.get(players.get(playerToDraw).hand.size() - 1);
					if (!drawnCard.isWild()) {
						System.out.println(players.get(playerToDraw).name + " drew a " + drawnCard.getColor() + " "
								+ drawnCard.getCardType());
					} else if (drawnCard.getCardType().equals("draw 4")) {
						System.out.println(players.get(playerToDraw).name + " drew a wild draw 4");
					} else {
						System.out.println(players.get(playerToDraw).name + " drew a wild");
					}
				}
			}

			if (playedCard.getCardType().equals("draw 2")) {
				playerToDraw = correctIndex(playerToDraw, players);
				for (int i = 0; i < 2; i++) {
					if (deck.size() == 0) {// check to make sure deck isn't empty before drawing
						System.out.println("Deck is empty. Played cards will be reshuffled and used as new deck.");
						deck = playedCards;
						deck.shuffle();
						playedCards = new Deck();
					}
					drawCard(players.get(playerToDraw), deck);
					drawnCard = players.get(playerToDraw).hand.get(players.get(playerToDraw).hand.size() - 1);
					if (!drawnCard.isWild()) {
						System.out.println(players.get(playerToDraw).name + " drew a " + drawnCard.getColor() + " "
								+ drawnCard.getCardType());
					} else if (drawnCard.getCardType().equals("draw 4")) {
						System.out.println(players.get(playerToDraw).name + " drew a wild draw 4");
					} else {
						System.out.println(players.get(playerToDraw).name + " drew a wild");
					}
				}
			}
		}
	}

	public static void drawCard(Player player, Deck deck) {
		player.hand.add(deck.getTopCard());
		deck.removeTopCard();
	}

	public static int correctIndex(int playerIndex, List<Player> players) {
		if (playerIndex < 0) {
			playerIndex = players.size() - 1;
		}
		if (playerIndex > players.size() - 1) {
			playerIndex = 0;
		}
		return playerIndex;
	}
}
