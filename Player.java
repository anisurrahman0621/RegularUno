import java.util.List;
import java.util.ArrayList;

public class Player {

	final String name;
	final boolean isUserControlled;
	public List<Card> hand = new ArrayList<>();

	public Player(String name, List<Card> hand) {
		this.name = name;
		this.hand = hand;
	}

	public Player(String name) {
		this.name = name;
	}
	public Player(String name, boolean isUserControlled) {
		this.name = name;
		this.isUserControlled = isUserControlled
	}
	public Player(String name, boolean isUserControlled, List<Card> hand) {
		this.name = name;
		this.isUserControlled = isUserControlled;
		this.hand = hand;
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
