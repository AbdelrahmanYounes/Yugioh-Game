package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.MonsterCard;

public class HarpieFeatherDuster extends SpellCard {

	public HarpieFeatherDuster(String name, String description) {
		super(name, description);

	}

	@Override
	public void action(MonsterCard monster) {

		Card.getBoard()
				.getOpponentPlayer()
				.getField()
				.removeSpellToGraveyard(
						getBoard().getOpponentPlayer().getField()
								.getSpellArea());
	}
}
