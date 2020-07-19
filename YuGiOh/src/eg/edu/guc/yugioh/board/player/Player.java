package eg.edu.guc.yugioh.board.player;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.Mode;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.exceptions.DefenseMonsterAttackException;
import eg.edu.guc.yugioh.exceptions.EmptyFieldException;
import eg.edu.guc.yugioh.exceptions.MissingFieldException;
import eg.edu.guc.yugioh.exceptions.MonsterMultipleAttackException;
import eg.edu.guc.yugioh.exceptions.MultipleMonsterAdditionException;
import eg.edu.guc.yugioh.exceptions.UnknownCardTypeException;
import eg.edu.guc.yugioh.exceptions.UnknownSpellCardException;
import eg.edu.guc.yugioh.exceptions.WrongPhaseException;

public class Player implements Duelist {
	private String name;
	private int lifePoints;
	private Field field;
	private boolean flag1 = false;

	private ArrayList<MonsterCard> monstersAttacked;
	private ArrayList<MonsterCard> monstersSwitched;
	private ArrayList<MonsterCard> monstersSummoned;

	public boolean isFlag1() {
		return flag1;
	}

	public void setFlag1(boolean flag1) {
		this.flag1 = flag1;
	}

	public int getLifePoints() {
		return lifePoints;
	}

	public void setLifePoints(int lifePoints) {
		this.lifePoints = lifePoints;
	}

	public String getName() {
		return name;
	}

	public Field getField() {
		return field;
	}

	public Player(String name) throws CloneNotSupportedException,
			UnknownCardTypeException, EmptyFieldException,
			MissingFieldException, UnknownSpellCardException,
			FileNotFoundException {

		this.name = name;
		field = new Field();
		lifePoints = 8000;
		monstersAttacked = new ArrayList<>();
		monstersSwitched = new ArrayList<>();
		monstersSummoned = new ArrayList<>();

	}

	@Override
	public boolean summonMonster(MonsterCard monster) {
		if (Card.getBoard().getActivePlayer().getField().getPhase() == Phase.BATTLE) {
			throw new WrongPhaseException();
		}
		if (flag1 == true) {
			throw new MultipleMonsterAdditionException();
		}
		if (monstersSummoned.contains(monster)) {
			throw new MultipleMonsterAdditionException();
		}
		if (Card.getBoard().isGameIsNotOver()
				&& Card.getBoard().getActivePlayer().getName() == this.name
				&& flag1 == false) {
			for (Card monsterCard : Card.getBoard().getActivePlayer()
					.getField().getHand()) {
				if (monsterCard instanceof MonsterCard) {
					if (monsterCard.getName() == monster.getName()) {
						int size = Card.getBoard().getActivePlayer().getField()
								.getMonstersArea().size();
						Card.getBoard().getActivePlayer().getField()
								.addMonsterToField(monster, Mode.ATTACK, false);
						monstersSummoned.add(monster);
						flag1 = true;
						monster.setSummoned(true);
						if (size < Card.getBoard().getActivePlayer().getField()
								.getMonstersArea().size()) {

							return true;
						}
					}
				}
			}

		}

		return false;
	}

	@Override
	public boolean summonMonster(MonsterCard monster,
			ArrayList<MonsterCard> sacrifices) {
		boolean found = false;
		if (Card.getBoard().getActivePlayer().getField().getPhase() == Phase.BATTLE) {
			throw new WrongPhaseException();
		}
		if (flag1 == true) {
			throw new MultipleMonsterAdditionException();
		}
		if (monstersSummoned.contains(monster)) {
			throw new MultipleMonsterAdditionException();
		}

		if (Card.getBoard().isGameIsNotOver()

		&& Card.getBoard().getActivePlayer().getName() == this.name
				&& flag1 == false) {
			for (Card monsterCard : Card.getBoard().getActivePlayer()
					.getField().getHand()) {
				if (monsterCard instanceof MonsterCard) {
					if (monsterCard.getName() == monster.getName()) {
						Card.getBoard()
								.getActivePlayer()
								.getField()
								.addMonsterToField(monster, Mode.ATTACK,
										sacrifices);
						monstersSummoned.add(monster);
						for (int i = 0; i < Card.getBoard().getActivePlayer()
								.getField().getMonstersArea().size(); i++) {
							MonsterCard x = Card.getBoard().getActivePlayer()
									.getField().getMonstersArea().get(i);
							flag1 = true;
							monster.setSummoned(true);
							if (x.getName() == monster.getName()) {
								found = true;

								break;
							}
						}
					}
				}
			}

		}
		return found;
	}

	@Override
	public boolean setMonster(MonsterCard monster) {
		if (Card.getBoard().getActivePlayer().getField().getPhase() == Phase.BATTLE) {
			throw new WrongPhaseException();
		}
		if (flag1 == true) {
			throw new MultipleMonsterAdditionException();
		}
		if (monstersSummoned.contains(monster)) {
			throw new MultipleMonsterAdditionException();
		}
		if (Card.getBoard().isGameIsNotOver()

		&& Card.getBoard().getActivePlayer().getName() == this.name) {
			for (Card monsterCard : Card.getBoard().getActivePlayer()
					.getField().getHand()) {
				if (monsterCard instanceof MonsterCard) {
					if (monsterCard.getName() == monster.getName()) {
						int size = Card.getBoard().getActivePlayer().getField()
								.getMonstersArea().size();
						Card.getBoard().getActivePlayer().getField()
								.addMonsterToField(monster, Mode.DEFENSE, true);
						monstersSummoned.add(monster);
						monster.setSummoned(true);
						flag1 = true;
						if (size < Card.getBoard().getActivePlayer().getField()
								.getMonstersArea().size()) {

							return true;
						}
					}
				}
			}

		}
		return false;
	}

	@Override
	public boolean setMonster(MonsterCard monster,
			ArrayList<MonsterCard> sacrifices) {
		if (Card.getBoard().getActivePlayer().getField().getPhase() == Phase.BATTLE) {
			throw new WrongPhaseException();
		}
		if (flag1 == true) {
			throw new MultipleMonsterAdditionException();
		}
		boolean found = false;
		if (monstersSummoned.contains(monster)) {
			throw new MultipleMonsterAdditionException();
		}
		if (Card.getBoard().isGameIsNotOver()

		&& Card.getBoard().getActivePlayer().getName() == this.name) {
			for (Card monsterCard : Card.getBoard().getActivePlayer()
					.getField().getHand()) {
				if (monsterCard instanceof MonsterCard) {
					if (monsterCard.getName() == monster.getName()) {
						Card.getBoard()
								.getActivePlayer()
								.getField()
								.addMonsterToField(monster, Mode.DEFENSE,
										sacrifices);
						monstersSummoned.add(monster);
						for (int i = 0; i < Card.getBoard().getActivePlayer()
								.getField().getMonstersArea().size(); i++) {
							MonsterCard x = Card.getBoard().getActivePlayer()
									.getField().getMonstersArea().get(i);
							monster.setSummoned(true);
							flag1 = true;
							if (x.getName() == monster.getName()) {
								found = true;

								break;
							}
						}
					}
				}
			}

		}
		return found;
	}

	public ArrayList<MonsterCard> getMonstersSummoned() {
		return monstersSummoned;
	}

	public void setMonstersSummoned(ArrayList<MonsterCard> monstersSummoned) {
		this.monstersSummoned = monstersSummoned;
	}

	@Override
	public boolean setSpell(SpellCard spell) {
		if (Card.getBoard().getActivePlayer().getField().getPhase() == Phase.BATTLE) {
			throw new WrongPhaseException();
		}

		if (Card.getBoard().isGameIsNotOver()

		&& Card.getBoard().getActivePlayer().getName() == this.name) {
			for (Card spellCard : Card.getBoard().getActivePlayer().getField()
					.getHand()) {
				if (spellCard instanceof SpellCard) {
					if (spellCard.getName() == spell.getName()) {
						int size = Card.getBoard().getActivePlayer().getField()
								.getSpellArea().size();
						Card.getBoard().getActivePlayer().getField()
								.addSpellToField(spell);
						if (size < Card.getBoard().getActivePlayer().getField()
								.getSpellArea().size()) {
							return true;
						}
					}
				}
			}

		}
		return false;
	}

	@Override
	public boolean activateSpell(SpellCard spell, MonsterCard monster) {
		if (Card.getBoard().getActivePlayer().getField().getPhase() == Phase.BATTLE) {
			throw new WrongPhaseException();
		}
		if (Card.getBoard().isGameIsNotOver()

		&& Card.getBoard().getActivePlayer().getName() == this.name) {
			if (spell.getLocation() == Location.HAND) {
				for (Card spellCard : Card.getBoard().getActivePlayer()
						.getField().getHand()) {
					if (spellCard instanceof SpellCard) {
						if (spellCard == spell) {

							Card.getBoard().getActivePlayer().getField()
									.addSpellToField(spell, monster, false);
							return true;

						}
					}
				}
			} else {
				if (spell.getLocation() == Location.FIELD)
					spell.action(monster);
				return true;
			}

		}
		return false;
	}

	@Override
	public boolean declareAttack(MonsterCard activeMonster,
			MonsterCard opponentMonster) {
		if (Card.getBoard().getActivePlayer().getField().getPhase() != Phase.BATTLE) {
			throw new WrongPhaseException();
		}
//		if (Card.getBoard().getActivePlayer() != this) {
//			throw new MonsterMultipleAttackException();
//		}
		if (this.getMonstersAttacked().contains(activeMonster)) {
			throw new MonsterMultipleAttackException();
		}
		if (activeMonster.getMode() == Mode.DEFENSE) {
			throw new DefenseMonsterAttackException();
		}

		if (activeMonster.isAttackedBefore()) {
			throw new MonsterMultipleAttackException();
		}

		if (Card.getBoard().isGameIsNotOver()) {

			activeMonster.action(opponentMonster);
		this.getMonstersAttacked().add(activeMonster);
			activeMonster.setAttackedBefore(true);
			if (Card.getBoard().getActivePlayer().getLifePoints() <= 0) {
				Card.getBoard().getActivePlayer().setLifePoints(0);
				Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());
				Card.getBoard().setGameIsNotOver(false);
			}
			if (Card.getBoard().getOpponentPlayer().getLifePoints() <= 0) {
				Card.getBoard().getOpponentPlayer().setLifePoints(0);
				Card.getBoard().setWinner(Card.getBoard().getActivePlayer());
				Card.getBoard().setGameIsNotOver(false);
			}
			return true;

		}
//		if (activeMonster.isAttackIsSuccessful() == true) {
//
//			for (MonsterCard monster : Card.getBoard().getActivePlayer()
//					.getField().getMonstersArea()) {
//
//				monster.setSwitched(false);
//			}
//		}

		
		return false;
	}

	@Override
	public boolean declareAttack(MonsterCard activeMonster) {
		if (Card.getBoard().getActivePlayer().getField().getPhase() != Phase.BATTLE) {
			throw new WrongPhaseException();
		}
		if (Card.getBoard().getActivePlayer() != this) {
			throw new MonsterMultipleAttackException();
		}
		if (this.getMonstersAttacked().contains(activeMonster)) {
			throw new MonsterMultipleAttackException();
		}
		if (activeMonster.getMode() == Mode.DEFENSE) {
			throw new DefenseMonsterAttackException();
		}

		if (activeMonster.isAttackedBefore()) {
			throw new MonsterMultipleAttackException();
		}
		if (Card.getBoard().isGameIsNotOver()) {

			activeMonster.action();
			this.getMonstersAttacked().add(activeMonster);
			activeMonster.setAttackedBefore(true);
			if (Card.getBoard().getOpponentPlayer().getLifePoints() <= 0) {
				Card.getBoard().getOpponentPlayer().setLifePoints(0);
				Card.getBoard().setWinner(Card.getBoard().getActivePlayer());
				Card.getBoard().setGameIsNotOver(false);
			}
			return true;

		}
//		if (activeMonster.isAttackIsSuccessful() == true) {
//
//			for (MonsterCard monster : Card.getBoard().getActivePlayer()
//					.getField().getMonstersArea()) {
//
//				monster.setSwitched(false);
//			}
//
//		}

		
		return false;
	}

	@Override
	public void addCardToHand() {
		if (Card.getBoard().isGameIsNotOver()
				&& Card.getBoard().getActivePlayer().getName() == this.name) {
			Card.getBoard().getActivePlayer().getField().addCardToHand();
		}
	}

	@Override
	public void addNCardsToHand(int n) {
		if (Card.getBoard().isGameIsNotOver()
				&& Card.getBoard().getActivePlayer().getName() == this.name) {
			Card.getBoard().getActivePlayer().getField().addNCardsToHand(n);

		}
	}

	@Override
	public void endPhase() {
		if (Card.getBoard().getActivePlayer().getName() == this.name
				&& Card.getBoard().isGameIsNotOver()) {
			if (Card.getBoard().getActivePlayer().getField().getPhase() == Phase.MAIN1) {
				Card.getBoard().getActivePlayer().getField()
						.setPhase(Phase.BATTLE);
			} else {
				if (Card.getBoard().getActivePlayer().getField().getPhase() == Phase.BATTLE) {
					Card.getBoard().getActivePlayer().getField()
							.setPhase(Phase.MAIN2);
					for (MonsterCard monster : Card.getBoard()
							.getActivePlayer().getField().getMonstersArea()) {

						monster.setSwitched(false);
					}

				} else {

					endTurn();

				}
			}

		}

	}

	@Override
	public boolean endTurn() {
		for(int n=0;n<Card.getBoard().getActivePlayer().getField().getMonstersArea().size();n++){
			Card.getBoard().getActivePlayer().getField().getMonstersArea().get(n).setAttackedBefore(false);
		}
		if (Card.getBoard().isGameIsNotOver()) {
			this.getMonstersAttacked().clear();
			if (Card.getBoard().getActivePlayer() == this) {
				
				this.getMonstersSwitched().clear();
				
				flag1 = false;

				Card.getBoard().getActivePlayer().getField()
						.setPhase(Phase.MAIN1);

				for (MonsterCard monster : Card.getBoard().getActivePlayer()
						.getField().getMonstersArea()) {
					monster.setSummoned(false);
					monster.setSwitched(false);
					monster.setAttackedBefore(false);
				}
				Card.getBoard().nextPlayer();

				return true;
			}
		}
		return false;

	}

	public ArrayList<MonsterCard> getMonstersAttacked() {
		return monstersAttacked;
	}

	public void setMonstersAttacked(ArrayList<MonsterCard> monstersAttacked) {
		this.monstersAttacked = monstersAttacked;
	}

	public ArrayList<MonsterCard> getMonstersSwitched() {
		return monstersSwitched;
	}

	public void setMonstersSwitched(ArrayList<MonsterCard> monstersSwitched) {
		this.monstersSwitched = monstersSwitched;
	}

	@Override
	public boolean switchMonsterMode(MonsterCard monster) {
		if (Card.getBoard().getActivePlayer().getField().getPhase() == Phase.BATTLE) {
			throw new WrongPhaseException();
		}
		boolean found = false;
		if (Card.getBoard().getActivePlayer().getField().getPhase() == Phase.MAIN1
				&& !monstersSwitched.contains(monster)) {
			if (Card.getBoard().isGameIsNotOver()

			&& Card.getBoard().getActivePlayer() == this) {
				for (MonsterCard monsterCard : Card.getBoard()
						.getActivePlayer().getField().getMonstersArea()) {
					if (monster.getName() == monsterCard.getName()
							&& !monsterCard.isSwitched()) {
						if (monsterCard.getMode() == Mode.ATTACK) {
							monsterCard.setMode(Mode.DEFENSE);
						} else {
							monsterCard.setMode(Mode.ATTACK);
						}
						found = true;
						monstersSwitched.add(monster);
						monsterCard.setSwitched(true);
						break;
					}
				}
			}
		}
		if (Card.getBoard().getActivePlayer().getField().getPhase() == Phase.MAIN2) {
			if (Card.getBoard().isGameIsNotOver()

			&& Card.getBoard().getActivePlayer() == this
					&& monster.getLocation() == Location.FIELD
					&& !monster.isSwitched()
					&& !monstersSwitched.contains(monster)) {
				if (monster.getMode() == Mode.ATTACK) {
					monster.setMode(Mode.DEFENSE);
					found = true;
					monstersSwitched.add(monster);
				}

			}
		}
		return found;
	}

}
