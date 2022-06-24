package konetsky.wladislaw.xogame.playerimpl;

import konetsky.wladislaw.xogame.board.CollectionBoard;
import konetsky.wladislaw.xogame.entity.Cell;
import konetsky.wladislaw.xogame.entity.Mark;
import konetsky.wladislaw.xogame.exception.InvalidPlayerMarkException;
import konetsky.wladislaw.xogame.exception.NonexistentCellException;
import konetsky.wladislaw.xogame.exception.UnparseableInputException;
import konetsky.wladislaw.xogame.player.ClassicPlayer;
import konetsky.wladislaw.xogame.util.CellParamsParser;

/**
 * This class defines the behavior of a human player. Human player provides input as a string, which is then parsed to board cell coordinates.
 */
public class HumanClassicPlayer extends ClassicPlayer {
	
	/**
	 * The constructor of the player.
	 * @param playerMark - the mark of the player.
	 * @throws InvalidPlayerMarkException the exception is thrown when the type of mark for the player is <b>EMPTY</b> or the mark is already assigned to another player.
	 */
	protected HumanClassicPlayer(Mark playerMark) throws InvalidPlayerMarkException {
		super(playerMark);
	}

	/**
	 * 
	 */
	@Override
	public Cell selectCell(CollectionBoard board, String input) throws UnparseableInputException, NonexistentCellException {
		
		CellParamsParser parser = CellParamsParser.getInstance();
		Cell target = parser.parse(input);

		return board.getCell(target.getX(), target.getY());
	}

	@Override
	public boolean isAIPlayer() {
		return false;
	}
	
	@Override
	public String toString() {
		return "Human player, mark - " + getPlayerMark();
	}

}
