package eg.edu.guc.yugioh.board.player;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.hamcrest.core.IsInstanceOf;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.Mode;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.exceptions.EmptyFieldException;
import eg.edu.guc.yugioh.exceptions.MissingFieldException;
import eg.edu.guc.yugioh.exceptions.NoMonsterSpaceException;
import eg.edu.guc.yugioh.exceptions.NoSpellSpaceException;
import eg.edu.guc.yugioh.exceptions.UnknownCardTypeException;
import eg.edu.guc.yugioh.exceptions.UnknownSpellCardException;

public class Field {
	private Phase phase;
	private ArrayList<MonsterCard> monstersArea;
	private ArrayList<SpellCard> spellArea;
	private Deck deck;
	private ArrayList<Card> hand;
	private ArrayList<Card> graveyard;

	public Field() throws CloneNotSupportedException, UnknownCardTypeException, EmptyFieldException, MissingFieldException, UnknownSpellCardException, FileNotFoundException {
		Deck.setMonstersPath("Database-Monsters.csv");
		Deck.setSpellsPath("Database-Spells.csv");
		

		this.monstersArea = new ArrayList<MonsterCard>();
		this.spellArea = new ArrayList<SpellCard>();
		this.deck = new Deck();
		this.hand = new ArrayList<Card>();
		this.graveyard = new ArrayList<Card>();
		phase = Phase.MAIN1;
	}

	public Phase getPhase() {
		return phase;
	}

	public void setPhase(Phase phase) {
		this.phase = phase;
	}

	public ArrayList<MonsterCard> getMonstersArea() {
		return monstersArea;
	}

	public ArrayList<SpellCard> getSpellArea() {
		return spellArea;
	}

	public Deck getDeck() {
		return deck;
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public ArrayList<Card> getGraveyard() {
		return graveyard;
	}

	public void addMonsterToField(MonsterCard monster, Mode m, boolean isHidden) {
		
			if (monstersArea.size() >= 5){
				
			throw new NoMonsterSpaceException();
		} else {
			monster.setMode(m);
			monster.setHidden(isHidden);
			monster.setLocation(Location.FIELD);
			monstersArea.add(monster);
		}
			
		}

	

	public void addMonsterToField(MonsterCard monster, Mode mode,
			ArrayList<MonsterCard> sacrifices) {
		if (monstersArea.size() >= 5) {
			throw new NoMonsterSpaceException();
		} else {
			if (monster.getLevel() == 1 | monster.getLevel() == 2
					| monster.getLevel() == 3 | monster.getLevel() == 4
					&& sacrifices == null) {
				monster.setMode(mode);
				monster.setLocation(Location.FIELD);
				monstersArea.add(monster);
			} else {
				if (monster.getLevel() == 5 | monster.getLevel() == 6
						&& sacrifices != null && sacrifices.size() == 1) {
					MonsterCard x = sacrifices.get(0);
					x.setLocation(Location.GRAVEYARD);
					removeMonsterToGraveyard(x);

					monster.setMode(mode);
					monster.setLocation(Location.FIELD);
					monstersArea.add(monster);
				} else {
					if (monster.getLevel() == 7 | monster.getLevel() == 8
							&& sacrifices != null && sacrifices.size() == 2) {
						MonsterCard x = sacrifices.get(0);
						x.setLocation(Location.GRAVEYARD);
						MonsterCard y = sacrifices.get(1);
						y.setLocation(Location.GRAVEYARD);
						ArrayList<MonsterCard> z = new ArrayList<MonsterCard>();
						z.add(x);
						z.add(y);
						removeMonsterToGraveyard(z);
						monster.setMode(mode);
						monster.setLocation(Location.FIELD);
						monstersArea.add(monster);
					} else {
						System.out.println("You can not perform this action");
					}

				}
			}
		}

	}

	public void removeMonsterToGraveyard(MonsterCard monster) {

		if (monstersArea.contains(monster)) {
			monstersArea.remove(monster);
			monster.setLocation(Location.GRAVEYARD);
			graveyard.add(monster);
		}

	}

	public void removeMonsterToGraveyard(ArrayList<MonsterCard> monsters) {

		for (int i = 0; i < monsters.size(); i++) {
			MonsterCard x = monsters.get(i);
			for (MonsterCard monsterCard : monstersArea) {

				if (monsterCard.getName() == x.getName()) {
					monstersArea.remove(monsterCard);
					monsterCard.setLocation(Location.GRAVEYARD);
					graveyard.add(monsterCard);
					i--;
					break;
				}

			}
		}

	}

	public void addSpellToField(SpellCard action, MonsterCard monster,
			boolean hidden) {
		if (spellArea.size() >= 5) {
			throw new NoSpellSpaceException();
		} else {
			if (hidden) {

				action.setHidden(hidden);
				action.setLocation(Location.FIELD);
				hand.remove(action);
				spellArea.add(action);
			} else {
				action.setHidden(hidden);
				action.setLocation(Location.FIELD);
				hand.remove(action);
				spellArea.add(action);
				activateSetSpell(action, monster);
			}
		}

	}

	public void addSpellToField(SpellCard action) {
		if (spellArea.size() >= 5) {
			throw new NoSpellSpaceException();
		} else {
			action.setHidden(true);

			hand.remove(action);
			spellArea.add(action);
			action.setLocation(Location.FIELD);
		}
	}

	public void activateSetSpell(SpellCard action, MonsterCard monster) {
		if (action.getLocation() == Location.FIELD) {

			action.action(monster);
			action.setLocation(Location.GRAVEYARD);
			removeSpellToGraveyard(action);
		}

	}

	public void removeSpellToGraveyard(SpellCard spell) {
		for (SpellCard spellCard : spellArea) {
			if (spellCard.getName() == spell.getName()) {
				spellArea.remove(spellCard);
				spellCard.setLocation(Location.GRAVEYARD);

				graveyard.add(spellCard);
				break;
			}
		}

	}

	public void removeSpellToGraveyard(ArrayList<SpellCard> spells) {
		for (int i = 0; i < spells.size(); i++) {
			SpellCard x = spells.get(i);
			for (SpellCard spellCard : spellArea) {

				if (spellCard.getName() == x.getName()) {
					spellArea.remove(spellCard);
					spellCard.setLocation(Location.GRAVEYARD);
					graveyard.add(spellCard);
					i--;
					break;
				}

			}
		}

	}

	public void addCardToHand() {

		Card card = deck.drawOneCard();
		if (card != null) {
			card.setLocation(Location.HAND);
			hand.add(card);
		}
	}

	public void addNCardsToHand(int n) {
		ArrayList<Card> drawn = deck.drawNCards(n);

		for (Card card : drawn) {
			card.setLocation(Location.HAND);
			hand.add(card);
		}

	}

}
