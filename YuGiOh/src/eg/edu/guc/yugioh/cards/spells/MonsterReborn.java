package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.MonsterCard;

public class MonsterReborn extends SpellCard {

	public MonsterReborn(String name, String description) {
		super(name, description);

	}

	@Override
	public void action(MonsterCard monster) {

		MonsterCard max1 = (MonsterCard) Card.getBoard().getActivePlayer()
				.getField().getGraveyard().get(0);
		MonsterCard max2 = (MonsterCard) Card.getBoard().getOpponentPlayer()
				.getField().getGraveyard().get(0);

		for (int i = 1; i < Card.getBoard().getActivePlayer().getField()
				.getGraveyard().size(); i++) {
			if (Card.getBoard().getActivePlayer().getField().getGraveyard()
					.get(i) instanceof MonsterCard) {
				MonsterCard x = (MonsterCard) Card.getBoard().getActivePlayer()
						.getField().getGraveyard().get(i);
				if (x.getAttackPoints() > max1.getAttackPoints()) {
					max1 = x;

				}
			}

		}

		for (int i = 1; i < Card.getBoard().getOpponentPlayer().getField()
				.getGraveyard().size(); i++) {
			if (Card.getBoard().getOpponentPlayer().getField().getGraveyard()
					.get(i) instanceof MonsterCard) {
				MonsterCard x = (MonsterCard) (Card.getBoard()
						.getOpponentPlayer().getField().getGraveyard().get(i));
				if (x.getAttackPoints() > max2.getAttackPoints()) {
					max2 = x;

				}

			}

		}
		if (max1.getAttackPoints() > max2.getAttackPoints()) {
			Card.getBoard().getActivePlayer().getField().getGraveyard()
					.remove(max1);
			max1.setLocation(Location.FIELD);
			Card.getBoard().getActivePlayer().getField()
					.addMonsterToField(max1, max1.getMode(), max1.isHidden());
		}

		else {

			Card.getBoard().getOpponentPlayer().getField().getGraveyard()
					.remove(max2);
			max2.setLocation(Location.FIELD);
			Card.getBoard().getActivePlayer().getField()
					.addMonsterToField(max2, max2.getMode(), max2.isHidden());
		}
	}

}
