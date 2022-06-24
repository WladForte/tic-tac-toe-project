package konetsky.wladislaw.xogame.player;

import konetsky.wladislaw.xogame.board.CollectionBoard;
import konetsky.wladislaw.xogame.entity.AbstractCell;
import konetsky.wladislaw.xogame.entity.Cell;
import konetsky.wladislaw.xogame.entity.Mark;
import konetsky.wladislaw.xogame.exception.InvalidPlayerMarkException;

/**
 * This abstract class defines the method for interacting with boards, implementing CollectionBoard interface.
 */
public abstract class ClassicPlayer extends Player<CollectionBoard> {
	
	/**
	 * The constructor of the player.
	 * @param playerMark - the mark of the player.
	 * @throws InvalidPlayerMarkException the exception is thrown when the type of mark for the player is <b>EMPTY</b> or the mark is already assigned to another player.
	 */
	protected ClassicPlayer(Mark playerMark) throws InvalidPlayerMarkException {
		super(playerMark);
	}

	@Override
	public boolean putMark(CollectionBoard board, AbstractCell cell) {
		
		if (cell.getMark() == Mark.EMPTY) {
			cell.setMark(this.getPlayerMark());
			return true;
		} else {
			return false;
		}

	}

	@Override
	public abstract Cell selectCell(CollectionBoard board, String input);
	
	@Override
	public abstract boolean isAIPlayer();

}
