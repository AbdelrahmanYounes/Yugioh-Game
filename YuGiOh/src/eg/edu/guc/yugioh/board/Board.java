package eg.edu.guc.yugioh.board;

import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.Raigeki;

public class Board {
	private Player activePlayer;
	private Player opponentPlayer;
	private Player winner;
	private boolean gameIsNotOver = true;

	public Board() {
		winner = null;
		Card.setBoard(this);

	}

	public boolean isGameIsNotOver() {
		return gameIsNotOver;
	}

	public void setGameIsNotOver(boolean gameIsOver) {
		this.gameIsNotOver = gameIsOver;
	}

	public Player getActivePlayer() {
		return activePlayer;
	}

	public void setActivePlayer(Player activePlayer) {
		this.activePlayer = activePlayer;
	}

	public Player getOpponentPlayer() {
		return opponentPlayer;
	}

	public void setOpponentPlayer(Player opponentPlayer) {
		this.opponentPlayer = opponentPlayer;
	}

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}

	public void whoStarts(Player p1, Player p2) {
		int random = (int) (Math.random() * 2);
		if (random == 1) {
			activePlayer = p1;
			opponentPlayer = p2;
		} else {
			activePlayer = p2;
			opponentPlayer = p1;
		}

	}

	public void startGame(Player p1, Player p2) {
		p1.getField().addNCardsToHand(5);
		p2.getField().addNCardsToHand(5);
		whoStarts(p1, p2);
		activePlayer.getField().addCardToHand();
		Card.setBoard(this);
	}

	public void nextPlayer() {

		Player temp;
		temp = activePlayer;
		activePlayer = opponentPlayer;
		opponentPlayer = temp;
		activePlayer.getField().addCardToHand();
//		activePlayer.getMonstersAttacked().clear();
//		activePlayer.getMonstersSwitched().clear();
//		activePlayer.setFlag1(false);
//		activePlayer.getMonstersSummoned().clear();

	}

}
