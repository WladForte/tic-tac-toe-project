package konetsky.wladislaw.xogame.boardimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import konetsky.wladislaw.xogame.board.Board;
import konetsky.wladislaw.xogame.exception.UnparseableInputException;

/**
 * The class to provide different types of boards;
 */
public class BoardProvider {
	
	public List<Class<?>> boardsList = new ArrayList<>();
	
	private static final BoardProvider PROVIDER = new BoardProvider();
	
	// filling the collection of boards names
	{
		boardsList.add(ClassicListBoard.class);
	}
	
	private BoardProvider() {};
	
	public static BoardProvider getInstance() {
		return PROVIDER;
	}
	
	/**
	 * The method through string input provides a specific board with certain size.
	 * @param input - the string, provided in the following manner: boardName, its size (integer number, that equals or is greater than 3).
	 * @throws UnparseableInputException if the provided string does not match the manner, specified above
	 */
	public Board<?> getCustomizedBoard(String input) throws UnparseableInputException {
		String[] params = input.split("\\s*,\\s*");
		if (params.length != 2) {
			throw new UnparseableInputException("The provided input does not correspond to the string pattern (boardName, its size (integer number, that equals or is greater than 3))");
		}
		
		int boardSize;
		String boardName;
		Board<?> targetBoard;
		boardSize = 0;
		boardName = "";
		targetBoard = null;
		
		try {
			boardSize = Integer.parseInt(params[1]);
			if (boardSize < 3) {
				throw new UnparseableInputException("The provided input does not correspond to the string pattern (boardName, its size (integer number, that equals or is greater than 3)");
			}
		} catch (NumberFormatException nfe) {
			throw new UnparseableInputException("The provided input does not correspond to the string pattern (boardName, its size (integer number, that equals or is greater than 3)");
		}
		
		boardName = params[0];
		
		if (boardName.equalsIgnoreCase(boardsList.get(0).getSimpleName())) {
			targetBoard = new ClassicListBoard(boardSize);
		} else {
			throw new UnparseableInputException("A board of such type does not exist, please, provide the correct input");
		}
	
		return targetBoard;		
	}
	
	public String presentAllAvailableBoards() {
		return boardsList.stream().map(Class<?>::getSimpleName).collect(Collectors.toList()).toString();
	}

}
