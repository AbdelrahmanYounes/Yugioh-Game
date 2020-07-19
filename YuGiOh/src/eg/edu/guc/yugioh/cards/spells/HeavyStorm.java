package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.MonsterCard;

public class HeavyStorm extends HarpieFeatherDuster {

	public HeavyStorm(String name, String description) {
		super(name, description);

	}

	@Override
	public void action(MonsterCard monster) {

		super.action(monster);
		Card.getBoard()
				.getActivePlayer()
				.getField()
				.removeSpellToGraveyard(
						getBoard().getActivePlayer().getField().getSpellArea());

	}

}
