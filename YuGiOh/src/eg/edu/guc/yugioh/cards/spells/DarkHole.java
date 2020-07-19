package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.MonsterCard;

public class DarkHole extends Raigeki {

	public DarkHole(String name, String description) {
		super(name, description);

	}

	@Override
	public void action(MonsterCard monster) {

		super.action(monster);

		Card.getBoard()
				.getActivePlayer()
				.getField()
				.removeMonsterToGraveyard(
						getBoard().getActivePlayer().getField()
								.getMonstersArea());

	}
}
