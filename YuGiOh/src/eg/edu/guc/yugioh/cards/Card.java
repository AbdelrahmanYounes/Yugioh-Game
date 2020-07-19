package eg.edu.guc.yugioh.cards;

import eg.edu.guc.yugioh.board.Board;

public abstract class Card implements Cloneable {
	private String name;
	private String description;
	private boolean isHidden;
	private Location location;
	private static Board board; // = new Board();

	public Card(String name, String description) {
		this.isHidden = true;
		this.name = name;
		this.description = description;

		location = Location.DECK;
	}

	public boolean isHidden() {
		return isHidden;
	}

	public void setHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public static Board getBoard() {
		return board;
	}

	public Card clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return (Card) super.clone();
	}

	public static void setBoard(Board b) {
		Card.board = b;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public abstract void action(MonsterCard monster);

}
