package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.MonsterCard;

public class CardDestruction extends SpellCard {

	public CardDestruction(String name, String description) {
		super(name, description);

	}

	@Override
	public void action(MonsterCard monster) {

		int activeSize = 0;
		int opponentSize = 0;

		while (Card.getBoard().getActivePlayer().getField().getHand().size() > 0) {
			Card y = Card.getBoard().getActivePlayer().getField().getHand()
					.remove(0);
			Card.getBoard().getActivePlayer().getField().getGraveyard().add(y);
			y.setLocation(Location.GRAVEYARD);
			activeSize++;

		}

		Card.getBoard().getActivePlayer().getField()
				.addNCardsToHand(activeSize);
		while (Card.getBoard().getOpponentPlayer().getField().getHand().size() > 0) {
			Card y = Card.getBoard().getOpponentPlayer().getField().getHand()
					.remove(0);
			Card.getBoard().getOpponentPlayer().getField().getGraveyard()
					.add(y);
			y.setLocation(Location.GRAVEYARD);
			opponentSize++;

		}
		Card.getBoard().getOpponentPlayer().getField()
				.addNCardsToHand(opponentSize);

	}

}
