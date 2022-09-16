
public class Card {

	private String color;
	private final boolean isWild;
	private final String cardType;

	public Card() {
		color = null;
		isWild = false;
		cardType = null;
	}

	public Card(String color, String cardType, boolean isWild) {
		this.color = color;
		this.cardType = cardType;
		this.isWild = isWild;
	}

	public String getColor() {
		return color;
	}

	public String getCardType() {
		return cardType;
	}

	public boolean isWild() {
		return isWild;
	}

	public boolean isPlayable(Card lastPlayedCard) {
		if (isWild | color.equals(lastPlayedCard.getColor()) | cardType.equals(lastPlayedCard.getCardType())) { // either
																												// wild,
																												// same
																												// color,
																												// or
																												// same
																												// type
			return true;
		} else {
			return false;
		}
	}

	public void setColor(Card card, String newColor) {
		if (card.isWild()) {
			color = newColor;
		} else {
			System.out.println("Not cannot change color");
		}

	}
}
