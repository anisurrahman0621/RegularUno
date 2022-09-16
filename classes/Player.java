import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Player {

	final String name;
	private boolean isUserControlled;
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
		this.isUserControlled = isUserControlled;
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

	public String pickBestColor(List<Card> hand) {

		int red = 0;
		int blue = 0;
		int green = 0;
		int yellow = 0;
		int holder = 0;
		
		List<Integer> colorCount = new ArrayList<>();
		List<String> colors = new ArrayList<>();
		
		colors.add("red");
		colors.add("blue");
		colors.add("green");
		colors.add("yellow");

		for (Card card : hand) {
			if (card.getColor().equals("red")) {
				red++;
			} else if (card.getColor().equals("blue")) {
				blue++;
			} else if (card.getColor().equals("green")) {
				green++;
			} else if (card.getColor().equals("yellow")) {
				yellow++;
			} else {
				;
			}
		}
			
			colorCount.add(red);
			colorCount.add(blue);
			colorCount.add(green);
			colorCount.add(yellow);
			
			int maxColor = Collections.max(colorCount);
			for (int i = 0; i < colorCount.size(); i++) {
				if (colorCount.get(i) == maxColor) {
					holder = i;
					break;
				}
			}
			return colors.get(holder);
		}

	public boolean isUserControlled() {
		return isUserControlled;
	}

}
