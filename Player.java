import java.util.List;
import java.util.ArrayList;

public class Player {

	final String name;
	public List<Card> hand = new ArrayList<>();

	public Player(String name, List<Card> hand) {
		this.name = name;
		this.hand = hand;
	}

	public Player(String name) {
		this.name = name;
	}

	public List<Integer> getPlayableCards(Card lastPlayedCard) {

		List<Integer> playableCards = new ArrayList<>();
		for (Card card : hand) { // go through each card in hand mark whether or not it's playable using 1 and 0
			if (card.isPlayable(lastPlayedCard)) {
				playableCards.add(1);
			} else {
				playableCards.add(0);
			}
		}
		return playableCards;
	}
	
}
