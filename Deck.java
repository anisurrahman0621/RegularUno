import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {

	public List<Card> deckOfCards = new ArrayList<>();

	public void createDeck() {
		List<String> diffColors = new ArrayList<>();
		diffColors.add("red");
		diffColors.add("blue");
		diffColors.add("green");
		diffColors.add("yellow");

		List<String> cardTypes = new ArrayList<>();

		cardTypes.add("1");
		cardTypes.add("2");
		cardTypes.add("3");
		cardTypes.add("4");
		cardTypes.add("5");
		cardTypes.add("6");
		cardTypes.add("7");
		cardTypes.add("8");
		cardTypes.add("9");
		cardTypes.add("skip");
		cardTypes.add("reverse");
		cardTypes.add("draw 2");

		for (int i = 0; i < 4; i++) {

			deckOfCards.add(new Card(diffColors.get(i), "0", false));
			deckOfCards.add(new Card("", "wild", true));
			deckOfCards.add(new Card("", "draw 4", true));

			for (int j = 0; j < cardTypes.size(); j++) {
				deckOfCards.add(new Card(diffColors.get(i), cardTypes.get(j), false));
			}
		}
		Collections.shuffle(deckOfCards);

	}
	
	public Card getTopCard() {
		return deckOfCards.get(deckOfCards.size() - 1);
	}
	
	public void shuffle() {
		Collections.shuffle(deckOfCards);
	}
	
	public void removeTopCard() {
		deckOfCards.remove(deckOfCards.size() - 1);
	}
	
	public void addCard(Card playedCard) {
		deckOfCards.add(playedCard);
	}
	
	public int size() {
		return deckOfCards.size();
	}
}
