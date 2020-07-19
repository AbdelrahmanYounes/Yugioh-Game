package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.MonsterCard;

public abstract class SpellCard extends Card {

	public SpellCard(String name, String description) {
		super(name, description);

	}

	@Override
	public abstract void action(MonsterCard monster);

}
