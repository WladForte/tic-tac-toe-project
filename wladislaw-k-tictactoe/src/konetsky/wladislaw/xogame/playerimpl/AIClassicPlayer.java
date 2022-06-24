package konetsky.wladislaw.xogame.playerimpl;

import konetsky.wladislaw.xogame.board.CollectionBoard;
import konetsky.wladislaw.xogame.entity.Cell;
import konetsky.wladislaw.xogame.entity.Mark;
import konetsky.wladislaw.xogame.exception.InvalidPlayerMarkException;
import konetsky.wladislaw.xogame.exception.NonexistentCellException;
import konetsky.wladislaw.xogame.player.ClassicPlayer;

/**
 * This class defines the behavior of the AI player. It behavior differers, depending on the mode.
 */
public class AIClassicPlayer extends ClassicPlayer {
	
	private final Mode mode; // this field is initialized only during the creation of the object
	
	/**
	 * The constructor of the AI player.
	 * @param playerMark - the mark of the player.
	 * @param mode - the type of mode of the AI Player.
	 * @throws InvalidPlayerMarkException the exception is thrown when the type of mark for the player is <b>EMPTY</b> or the mark is already assigned to another player
	 */
	protected AIClassicPlayer(Mark playerMark, Mode mode) throws InvalidPlayerMarkException {
		super(playerMark);
		this.mode = mode;
	}
	
	public Mode getPlayerMode() {
		return this.mode;
	}

	@Override
	public Cell selectCell(CollectionBoard board, String input) {
		Cell target = null;
		
		if (this.mode == Mode.EASY) {
			target = selectCellEasyMode(board);
		}
		if (this.mode == Mode.MEDIUM) {
			target = selectCellMediumMode(board);
		}
		
		return target;
	}
	
	private Cell selectCellEasyMode(CollectionBoard board) {
		
		boolean isSelectable = false;
		int minBound = 1;
		int maxBound = board.getSize() + 1;
		Cell target = null;
		
		while (!isSelectable) {
			int randomX = generateRandomNumber(minBound, maxBound);
			int randomY = generateRandomNumber(minBound, maxBound);
			try {
				target = board.getCell(randomX, randomY);
				if (target.getMark() == Mark.EMPTY) {
					isSelectable = true;
				}
			} catch (NonexistentCellException e) {
			}
		}
		
		return target;
	}
	
	private Cell selectCellMediumMode(CollectionBoard board) {
		// TODO Auto-generated method stub
		// TO BE IMPLEMENTED;
		return selectCellEasyMode(board);
	}
	
	// min - inclusive, max - exclusive
	private int generateRandomNumber(int min, int max) {
		int result;
		result = 0;
		
		result = min + (int) (Math.random() * (max - min));
		
		return result;
	}

	@Override
	public boolean isAIPlayer() {
		return true;
	}
	
	@Override
	public String toString() {
		return "AI player, mark - " + getPlayerMark() + ", mode - " + getPlayerMode();
	}

}
