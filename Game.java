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
		for (int j = 0; j < players.size(); j++) {
			for (int i = 0; i < 5; i++) {
				Card topOfDeck = deck.getTopCard();
				players.get(j).hand.add(topOfDeck);
				if (topOfDeck.isWild() & topOfDeck.getCardType().equals("wild")) {
					System.out.println(players.get(j).name + " is dealt a wild ");
				} else if (topOfDeck.isWild()) {
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
		int playerIndex = 0;
		int indexBy = 1;
		int direction = 1;
		Card topCard = deck.getTopCard();
		while (topCard.isWild()) {
			deck.removeTopCard();
			playedCards.addCard(topCard);
			topCard = deck.getTopCard();
		}
		while (!gameWon) {
			Player player = players.get(playerIndex);
			if (deck.size() == 0) {
				System.out.println("Deck is empty. Played cards will be reshuffled and used as new deck.");
				deck = playedCards;
				deck.shuffle();
				playedCards = new Deck();
			}
			List<Integer> playableCards = player.getPlayableCards(topCard);
			int numberOfPlayedCardsBefore = playedCards.size() - 1;
			if (Collections.max(playableCards) == 0) {
				drawCard(player, deck);
				drawnCard = player.hand.get(player.hand.size() - 1);
				System.out.println(player.name + " has no playable cards. " + player.name + " drew a "
						+ drawnCard.getColor() + " " + drawnCard.getCardType());
			} else {
				int handIndex = 0;
				for (int bool : playableCards) {
					if (bool == 1) {
						playedCard = player.hand.get(handIndex);
						System.out.println(player.name + " played " + "played a " + playedCard.getColor() + " "
								+ playedCard.getCardType());
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
			if (numberOfPlayedCardsBefore != numberOfPlayedCardsAfter) {
				if (playedCard.isWild()) {
					Scanner myObj = new Scanner(System.in); // Create a Scanner object
					System.out.println("Pick a color: ");
					String newColor = myObj.nextLine(); // Read user input
					while (!(newColor.equals("red") | newColor.equals("blue") | newColor.equals("green")
							| newColor.equals("yellow"))) {
						System.out.println("Invalid color. Try again. Pick a color:");
						newColor = myObj.nextLine();
					}
					myObj.close();
					topCard.setColor(topCard, newColor);
				}
			}
			if (playedCard.getCardType().equals("reverse")) {
				direction = direction * -1;
			}
			if (playedCard.getCardType().equals("skip") | playedCard.getCardType().equals("draw 2")
					| playedCard.getCardType().equals("draw 4")) {
				indexBy = 2;
			} else {
				indexBy = 1;
			}
			int playerToDraw = playerIndex + 1 * direction;
			playerIndex += indexBy * direction;
			playerIndex = correctIndex(playerIndex, players);

			if (topCard.getCardType().equals("draw 4")) {
				playerToDraw = correctIndex(playerToDraw, players);
				for (int i = 0; i < 4; i++) {
					if (deck.size() == 0) {
						System.out.println("Deck is empty. Played cards will be reshuffled and used as new deck.");
						deck = playedCards;
						deck.shuffle();
						playedCards = new Deck();
					}
					drawCard(players.get(playerToDraw), deck);
					drawnCard = players.get(playerToDraw).hand.get(players.get(playerToDraw).hand.size() - 1);
					System.out.println(players.get(playerToDraw).name + " drew a " + drawnCard.getColor() + " "
							+ drawnCard.getCardType());
				}
			}

			if (playedCard.getCardType().equals("draw 2")) {
				playerToDraw = correctIndex(playerToDraw, players);
				for (int i = 0; i < 2; i++) {
					if (deck.size() == 0) {
						System.out.println("Deck is empty. Played cards will be reshuffled and used as new deck.");
						deck = playedCards;
						deck.shuffle();
						playedCards = new Deck();
					}
					drawCard(players.get(playerToDraw), deck);
					drawnCard = players.get(playerToDraw).hand.get(players.get(playerToDraw).hand.size() - 1);
					System.out.println(players.get(playerToDraw).name + " drew a " + drawnCard.getColor() + " "
							+ drawnCard.getCardType());
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
