package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.MonsterCard;

public class GracefulDice extends SpellCard {

	public GracefulDice(String name, String description) {
		super(name, description);

	}

	@Override
	public void action(MonsterCard monster) {

		int random = 1 + (int) (Math.random() * 10);
		for (MonsterCard x : Card.getBoard().getActivePlayer().getField()
				.getMonstersArea()) {
			x.setAttackPoints(x.getAttackPoints() + random * 100);
			x.setDefensePoints(x.getDefensePoints() + random * 100);

		}
	}
}
