package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.MonsterCard;

public class MagePower extends SpellCard {

	public MagePower(String name, String description) {
		super(name, description);

	}

	@Override
	public void action(MonsterCard monster) {

		int size = Card.getBoard().getActivePlayer().getField().getSpellArea()
				.size();
		for (MonsterCard x : Card.getBoard().getActivePlayer().getField()
				.getMonstersArea()) {
			if (x.getName() == monster.getName()) {
				x.setAttackPoints(x.getAttackPoints() + 500 * size);
				x.setDefensePoints(x.getDefensePoints() + 500 * size);
				break;

			}

		}
	}

}
