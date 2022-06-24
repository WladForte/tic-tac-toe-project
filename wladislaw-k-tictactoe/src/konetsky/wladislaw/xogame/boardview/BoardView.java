package konetsky.wladislaw.xogame.boardview;

import konetsky.wladislaw.xogame.board.Board;
import konetsky.wladislaw.xogame.boardimpl.ClassicListBoard;

/**
 * This class represent a board on the console, the logic behind the board is encapsulated in a board class.
 */
public class BoardView {
	
	private static final BoardView INSTANCE = new BoardView();
	
	private BoardView() {}
	
	public static BoardView getInstance() {
		return INSTANCE;
	}
	
	public String boardToString(Board<?> board) {
		String result;
		result = "";
		
		if (board instanceof ClassicListBoard) {
			ClassicListBoard boardImpl = (ClassicListBoard) board;
			result = classicListBoardToString(boardImpl);
		}
		
		return result;
	}
	
	public String boardToStringWithPositions(Board<?> board) {
		String result;
		result = "";
		
		if (board instanceof ClassicListBoard) {
			ClassicListBoard boardImpl = (ClassicListBoard) board;
			result = classicListBoardToStringWithPositions(boardImpl);
		}
		
		return result;
	}
	
	private String classicListBoardToString(ClassicListBoard board) {
		StringBuilder b = new StringBuilder();
		
		int boardSize = board.getSize();
		
		for(int i = boardSize; i >= 1; i--) {
			for(int j = 1; j <= boardSize; j++) {
				b.append("(").append(board.getCell(i, j).getMark()).append(")");
			}
			b.append("\n");
		}
		
		return b.substring(0, b.length() - 1);
	}
	
	private String classicListBoardToStringWithPositions(ClassicListBoard board) {
		StringBuilder b = new StringBuilder();
		
		int boardSize = board.getSize();
		
		for(int i = boardSize; i >= 1; i--) {
			for(int j = 1; j <= boardSize; j++) {
				b.append("(");
				b.append(board.getCell(i, j).getX()).append(",");
				b.append(board.getCell(i, j).getY()).append(",");
				b.append(board.getCell(i, j).getMark());
				b.append(")");
			}
			b.append("\n");
		}
		
		return b.substring(0, b.length() - 1);
	}
	
}
