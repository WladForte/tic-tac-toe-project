package konetsky.wladislaw.xogame.test;

import konetsky.wladislaw.xogame.board.CollectionBoard;
import konetsky.wladislaw.xogame.boardimpl.ClassicListBoard;
import konetsky.wladislaw.xogame.boardview.BoardView;
import konetsky.wladislaw.xogame.entity.AbstractCell;
import konetsky.wladislaw.xogame.entity.Cell;
import konetsky.wladislaw.xogame.entity.Mark;

public class WinningConditionTest {

	public static void main(String[] args) {
		
		BoardView view = BoardView.getInstance();
		CollectionBoard board = new ClassicListBoard(4);
		System.out.println(view.boardToString(board));
		System.out.println("--------------");

		board.getCell(3, 2).setMark(Mark.X);
		board.getCell(2, 3).setMark(Mark.X);
		board.getCell(1, 4).setMark(Mark.X);
		board.getCell(1, 1).setMark(Mark.O);;
		System.out.println(view.boardToString(board));
		
		boolean estimate = board.hasWinningCondition();
		System.out.println("Has winning condition? - " + estimate);
		System.out.println("--------------");
		
		System.out.println(view.boardToStringWithPositions(board));

		Cell c1 = new Cell(1, 2, Mark.X);
		Cell c2 = new Cell(3, 2, Mark.X);
		Cell c3 = new Cell(2, 2, Mark.O);
		
		System.out.println(AbstractCell.equalsMarkMult(c1, c2, c3));

	}

}
