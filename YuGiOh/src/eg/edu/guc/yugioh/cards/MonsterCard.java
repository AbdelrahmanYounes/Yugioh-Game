package eg.edu.guc.yugioh.cards;

import eg.edu.guc.yugioh.board.player.Phase;

public class MonsterCard extends Card {
	private int level;
	private int defensePoints;
	private int attackPoints;
	private Mode mode;
	private boolean attackIsSuccessful;
	private boolean switched;
	private boolean summoned;
	private boolean attackedBefore;

	public boolean isSummoned() {
		return summoned;
	}

	public void setSummoned(boolean summoned) {
		this.summoned = summoned;
	}

	public void setAttackIsSuccessful(boolean attackIsSuccessful) {
		this.attackIsSuccessful = attackIsSuccessful;
	}

	public boolean isAttackIsSuccessful() {
		return attackIsSuccessful;
	}

	public MonsterCard(String name, String description, int level,
			int attackPoints, int defensePoints) {
		super(name, description);
		this.level = level;
		this.defensePoints = defensePoints;
		this.attackPoints = attackPoints;
		this.mode = Mode.DEFENSE;
		this.summoned = false;
		this.switched = false;
		attackIsSuccessful = false;
		this.attackedBefore = false;
	}

	public int getDefensePoints() {
		return defensePoints;
	}

	public void setDefensePoints(int defensePoints) {
		this.defensePoints = defensePoints;
	}

	public int getAttackPoints() {
		return attackPoints;
	}

	public void setAttackPoints(int attackPoints) {
		this.attackPoints = attackPoints;
	}

	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

	public int getLevel() {
		return level;
	}

	@Override
	public void action(MonsterCard monster) {

		if (monster.getMode() == Mode.ATTACK) {
			if (this.getAttackPoints() > monster.getAttackPoints()) {
				int deduction = this.getAttackPoints()
						- monster.getAttackPoints();
				Card.getBoard()
						.getOpponentPlayer()
						.setLifePoints(
								Card.getBoard().getOpponentPlayer()
										.getLifePoints()
										- deduction);
				Card.getBoard().getOpponentPlayer().getField()
						.removeMonsterToGraveyard(monster);

				attackIsSuccessful = true;
				

			} else {
				if (this.getAttackPoints() == monster.getAttackPoints()) {
					Card.getBoard().getOpponentPlayer().getField()
							.removeMonsterToGraveyard(monster);
					Card.getBoard().getActivePlayer().getField()
							.removeMonsterToGraveyard(this);
					attackIsSuccessful = true;

				} else {
					int deduction = monster.getAttackPoints()
							- this.getAttackPoints();
					Card.getBoard()
							.getActivePlayer()
							.setLifePoints(
									Card.getBoard().getActivePlayer()
											.getLifePoints()
											- deduction);
					Card.getBoard().getActivePlayer().getField()
							.removeMonsterToGraveyard(this);

					attackIsSuccessful = true;

				}
			}
		} else {

			if (this.getAttackPoints() > monster.getDefensePoints()) {
				Card.getBoard().getOpponentPlayer().getField()
						.removeMonsterToGraveyard(monster);
				attackIsSuccessful = true;

			} else {
				if (this.getAttackPoints() < monster.getDefensePoints()) {
					int deduction = monster.getDefensePoints()
							- this.getAttackPoints();
					Card.getBoard()
							.getActivePlayer()
							.setLifePoints(
									Card.getBoard().getActivePlayer()
											.getLifePoints()
											- deduction);

					attackIsSuccessful = true;

				}
			}

		}

	}

	public boolean isAttackedBefore() {
		return attackedBefore;
	}

	public void setAttackedBefore(boolean attackedBefore) {
		this.attackedBefore = attackedBefore;
	}

	public void action() {
		if (Card.getBoard().getOpponentPlayer().getField().getMonstersArea()
				.size() == 0) {
			int oppLifePoints = Card.getBoard().getOpponentPlayer()
					.getLifePoints();
			Card.getBoard().getOpponentPlayer()
					.setLifePoints(oppLifePoints - this.getAttackPoints());

			attackIsSuccessful = true;

		}
	}

	public boolean isSwitched() {
		return switched;
	}

	public void setSwitched(boolean switched) {
		this.switched = switched;
	}
}
