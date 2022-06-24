package konetsky.wladislaw.xogame.board;

import konetsky.wladislaw.xogame.entity.Mark;

/**
 * This is a basic interface for the board. Any board, implementing this interface, is a square.
 * @param <T> - determines how cells may be stored in the board (through array, multidimensional array, collection, etc).
 */
public interface Board<T> {
		
	int getSize();
	
	T getCells();
	
	T getEmptyCells();
	
	int countCellsByMark(Mark mark);
	
	int getMaxPlayersCount();
	
	/**
	 * The implementors of this method provide the description of for the rules of the board.
	 * @return string, containing rules of the board.
	 */
	String getDescription();
	
	/**
	 * evaluates the state of the board, whether it has a winning condition or not.
	 * @return true if it has a combination that defines a winning for a player.
	 */
	boolean hasWinningCondition();
		
}
