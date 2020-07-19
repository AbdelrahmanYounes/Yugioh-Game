package eg.edu.guc.yugioh.board.player;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.CardDestruction;
import eg.edu.guc.yugioh.cards.spells.ChangeOfHeart;
import eg.edu.guc.yugioh.cards.spells.DarkHole;
import eg.edu.guc.yugioh.cards.spells.GracefulDice;
import eg.edu.guc.yugioh.cards.spells.HarpieFeatherDuster;
import eg.edu.guc.yugioh.cards.spells.HeavyStorm;
import eg.edu.guc.yugioh.cards.spells.MagePower;
import eg.edu.guc.yugioh.cards.spells.MonsterReborn;
import eg.edu.guc.yugioh.cards.spells.PotOfGreed;
import eg.edu.guc.yugioh.cards.spells.Raigeki;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.exceptions.EmptyFieldException;
import eg.edu.guc.yugioh.exceptions.MissingFieldException;
import eg.edu.guc.yugioh.exceptions.UnexpectedFormatException;
import eg.edu.guc.yugioh.exceptions.UnknownCardTypeException;
import eg.edu.guc.yugioh.exceptions.UnknownSpellCardException;
import eg.edu.guc.yugioh.board.Board;
import eg.edu.guc.yugioh.board.player.ReadingCSVFile;

public class Deck {
	private static ArrayList<Card> monsters;
	private static ArrayList<Card> spells;
	private ArrayList<Card> deck;
	private static String monstersPath = "Database-Monsters.csv";
	private static String spellsPath = "Database-Spells.csv";
	

	public static String getMonstersPath() {
		return monstersPath;
	}

	public static void setMonstersPath(String monstersPath) {
		Deck.monstersPath = monstersPath;
	}

	public static String getSpellsPath() {
		return spellsPath;
	}

	public static void setSpellsPath(String spellsPath) {
		Deck.spellsPath = spellsPath;
	}

	public Deck() throws CloneNotSupportedException,
	UnknownCardTypeException, EmptyFieldException,
	MissingFieldException, UnknownSpellCardException, FileNotFoundException {

		Scanner sc = new Scanner(System.in);

		for (int i = 0; i <= 3; i++) {
			try {
				monsters = loadCardsFromFile(monstersPath);
				break;
			}catch (FileNotFoundException t) {
				if (i == 3){
				//	i=0;
					t.printStackTrace();
					throw t;
				}
				if (sc.hasNextLine())
					setMonstersPath(sc.nextLine());

			}
 
			catch (UnexpectedFormatException t) {
				if (i == 3){
				//	i=0;
					throw t;
				}
				if (sc.hasNextLine())
					setMonstersPath(sc.nextLine());

			}
			catch (IOException e) {
				
			}

		}
		for (int i = 0; i <= 3; i++) {
			try {
				spells = loadCardsFromFile(spellsPath);
				break;
				
			}
			catch (UnexpectedFormatException t) {
				if (i == 3){
			//		i=0;
					throw t;
				}
				//if (sc.hasNextLine())
					setSpellsPath(sc.nextLine());

			} 
			catch (FileNotFoundException t) {
				if (i == 3){
			//		i=0;
					t.printStackTrace();
					throw t;
				}
			//	if (sc.hasNextLine())
					setSpellsPath(sc.nextLine());

			} 
			
			catch (IOException e) {

			}

		}

			buildDeck(monsters, spells);
			shuffleDeck();
		

	}

	public static ArrayList<Card> getMonsters() {
		return monsters;
	}

	public void setMonsters(ArrayList<Card> monsters) {
		Deck.monsters = monsters;
	}

	public static ArrayList<Card> getSpells() {
		return spells;
	}

	public void setSpells(ArrayList<Card> spells) {
		Deck.spells = spells;
	}

	public ArrayList<Card> getDeck() {
		return deck;
	}

	public ArrayList<Card> loadCardsFromFile(String path)
			throws UnknownCardTypeException, EmptyFieldException,
			MissingFieldException, UnknownSpellCardException, NumberFormatException, IOException {
		String line = "";
		FileReader fileReader = new FileReader(path);
		BufferedReader br = new BufferedReader(fileReader);
		ArrayList<Card> cards = new ArrayList<Card>();

		int i = 0;
		
		while ((line = br.readLine()) != null) {		
			i++;
			String[] split = line.split(",");
			
			if (!split[0].equalsIgnoreCase("monster")
					&& !split[0].equalsIgnoreCase("spell")) {
				throw new UnknownCardTypeException(path, i, split[0]);

			}
			for (int j = 0; j < split.length; j++) {
				if (split[j].equals("") | split[j].equals(" ")) {
					throw new EmptyFieldException(path, i, j+1);
				}
			}
			if (split[0].equalsIgnoreCase("monster")) {
				if (split.length != 6) {
					throw new MissingFieldException(path, i);
				}
				MonsterCard monster = new MonsterCard(split[1], split[2],
						Integer.parseInt(split[5]), Integer.parseInt(split[3]),
						Integer.parseInt(split[4]));
				cards.add(monster);
			} else {
				if (split.length != 3) {
					throw new MissingFieldException(path, i);
				}
				String name = split[1];
				String description = split[2];
				switch (name) {
				case "Card Destruction":
					CardDestruction CardDestruction = new CardDestruction(name,
							description);
					cards.add(CardDestruction);
					break;
				case "Change Of Heart":
					ChangeOfHeart ChangeOfHeart = new ChangeOfHeart(name,
							description);
					cards.add(ChangeOfHeart);
					break;
				case "Dark Hole":
					DarkHole DarkHole = new DarkHole(name, description);
					cards.add(DarkHole);
					break;
				case "Graceful Dice":
					GracefulDice GracefulDice = new GracefulDice(name,
							description);
					cards.add(GracefulDice);
					break;
				case "Harpie's Feather Duster":
					HarpieFeatherDuster HarpieFeatherDuster = new HarpieFeatherDuster(
							name, description);
					cards.add(HarpieFeatherDuster);
					break;
				case "Heavy Storm":
					HeavyStorm HeavyStorm = new HeavyStorm(name, description);
					cards.add(HeavyStorm);
					break;
				case "Mage Power":
					MagePower MagePower = new MagePower(name, description);
					cards.add(MagePower);
					break;
				case "Monster Reborn":
					MonsterReborn MonsterReborn = new MonsterReborn(name,
							description);
					cards.add(MonsterReborn);
					break;
				case "Pot of Greed":
					PotOfGreed PotOfGreed = new PotOfGreed(name, description);
					cards.add(PotOfGreed);
					break;
				case "Raigeki":
					Raigeki Raigeki = new Raigeki(name, description);
					cards.add(Raigeki);
					break;
				default:
					throw new UnknownSpellCardException(path, i, split[1]);

				}
			}
		}
		return cards;
	}

	private void buildDeck(ArrayList<Card> monsters, ArrayList<Card> spells)
			throws CloneNotSupportedException {
		deck = new ArrayList<Card>();
		for (int i = 0; i < 15 && monsters.size() > 0; i++) {

			int random = (int) (Math.random() * monsters.size());

			Card x = monsters.get(random).clone();
			deck.add(x);

		}
		for (int i = 0; i < 5 && spells.size() > 0; i++) {
			int random = (int) (Math.random() * spells.size());
			Card x = spells.get(random).clone();
			deck.add(x);
		}

	}

	private void shuffleDeck() {
		long seed = System.nanoTime();
		Collections.shuffle(deck, new Random(seed));

	}

	public ArrayList<Card> drawNCards(int n) {

		ArrayList<Card> drawnCards = new ArrayList<Card>(n);
		for (int i = n; i > 0; i--) {

			Card x = drawOneCard();
			if (x != null)
				drawnCards.add(x);
			else
				break;

		}

		if (drawnCards.size() < n) {
			Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());
			Card.getBoard().setGameIsNotOver(false);
		}

		return drawnCards;

	}

	public Card drawOneCard() {
		if (deck.size() == 0) {
			Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());
			Card.getBoard().setGameIsNotOver(false);

			return null;
		}

		return deck.remove(0);
	}

}
