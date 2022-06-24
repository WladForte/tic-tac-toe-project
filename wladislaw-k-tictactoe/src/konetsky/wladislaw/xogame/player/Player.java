package konetsky.wladislaw.xogame.player;

import java.util.Comparator;

import konetsky.wladislaw.xogame.board.Board;
import konetsky.wladislaw.xogame.entity.AbstractCell;
import konetsky.wladislaw.xogame.entity.Cell;
import konetsky.wladislaw.xogame.entity.Mark;
import konetsky.wladislaw.xogame.exception.InvalidPlayerMarkException;

/**
 * This is an abstract class, defining main methods a player can perform (regardless whether it is a human player, or a AI player).
 * The players are compared according to their marks.
 * @param <T> type of board that the player is interacting with.
 */
public abstract class Player<T extends Board<?>> implements Comparable<Player<T>>{
	
	private final Mark playerMark;
	
	/**
	 * The constructor of the player.
	 * @param playerMark - the mark of the player.
	 * @throws InvalidPlayerMarkException the exception is thrown when the type of mark for the player is <b>EMPTY</b> or the mark is already assigned to another player.
	 */
	protected Player(Mark playerMark) throws InvalidPlayerMarkException {
		if (playerMark != Mark.EMPTY) {
			this.playerMark = playerMark;
		} else {
			throw new InvalidPlayerMarkException("Empty mark cannot be assigned to any player");
		}
		
	}
	
	public Mark getPlayerMark() {
		return this.playerMark;
	}
	
	public int compareTo(Player<T> anotherPlayer) {
		return Comparator.comparing(Player<T>::getPlayerMark).compare(this, anotherPlayer);
	}
	
	public abstract boolean putMark(T board, AbstractCell cell);
	
	public abstract Cell selectCell(T board, String input);
	
	public abstract boolean isAIPlayer();

}
