package eg.edu.guc.yugioh.cards.spells;

import java.util.ArrayList;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.MonsterCard;

public class ChangeOfHeart extends SpellCard {

	public ChangeOfHeart(String name, String description) {
		super(name, description);

	}

	@Override
	public void action(MonsterCard monster) {

		ArrayList<MonsterCard> x = Card.getBoard().getOpponentPlayer()
				.getField().getMonstersArea();
		boolean isFound = false;
		for (MonsterCard monsterCard : x) {
			if (monster.getName() == monsterCard.getName()) {
				Card.getBoard().getOpponentPlayer().getField()
						.getMonstersArea().remove(monsterCard);
				isFound = true;
				break;

			}

		}
		if (isFound) {
			Card.getBoard()
					.getActivePlayer()
					.getField()
					.addMonsterToField(monster, monster.getMode(),
							monster.isHidden());
		}
	}

}
