package konetsky.wladislaw.xogame.playerimpl;

import konetsky.wladislaw.xogame.board.Board;
import konetsky.wladislaw.xogame.entity.Mark;
import konetsky.wladislaw.xogame.exception.ExceededPlayersCountException;
import konetsky.wladislaw.xogame.exception.InvalidPlayerMarkException;

/**
 * This class enables to select various player (human player, different AI players) depending on the type of the board. 
 * This allows to limit the amount of created players.
 */
public class PlayerProvider {
	
	private static final PlayerProvider PROVIDER = new PlayerProvider();
	
	private int availablePlayersCount;
	
	private PlayerProvider() {}
	
	public static PlayerProvider getInstance(Board<?> board) {
		PROVIDER.availablePlayersCount = board.getMaxPlayersCount();
		return PROVIDER;
	}
	
	/**
	 * The method provides an AI player.
	 * @param playerMark - the mark of the player.
	 * @param mode - the mode of the AI player.
	 * @return new AI player.
	 * @throws ExceededPlayersCountException the exception occurs if the provider tries to create more players, than the board can have.
	 * @throws InvalidPlayerMarkException the exception is thrown when the type of mark for the player is <b>EMPTY</b> or the mark is already assigned to another player.
	 */
	public AIClassicPlayer provideAIPlayer(Mark playerMark, Mode mode) throws ExceededPlayersCountException, InvalidPlayerMarkException {
		if (availablePlayersCount < 1) {
			throw new ExceededPlayersCountException("The number of players for this board has reached its limit");
		}
		if (playerMark == Mark.EMPTY) {
			throw new InvalidPlayerMarkException("Empty mark cannot be assigned to any player");
		}
		availablePlayersCount -= 1;
		return new AIClassicPlayer(playerMark, mode);
	}
	
	/**
	 * The method provides a human player.
	 * @param playerMark - the mark of the player.
	 * @return new human player.
	 * @throws ExceededPlayersCountException the exception occurs if the provider tries to create more players, than the board can have.
	 * @throws InvalidPlayerMarkException the exception is thrown when the type of mark for the player is <b>EMPTY</b> or the mark is already assigned to another player.
	 */
	public HumanClassicPlayer provideHumanPlayer(Mark playerMark) throws ExceededPlayersCountException, InvalidPlayerMarkException {
		if (availablePlayersCount < 1) {
			throw new ExceededPlayersCountException("The number of players for this board has reached its limit");
		}
		if (playerMark == Mark.EMPTY) {
			throw new InvalidPlayerMarkException("Empty mark cannot be assigned to any player");
		}
		availablePlayersCount -= 1;
		return new HumanClassicPlayer(playerMark);
	}
	
}
