package konetsky.wladislaw.xogame.test;

import konetsky.wladislaw.xogame.entity.Cell;
import konetsky.wladislaw.xogame.entity.Mark;

public class CellTest {

	public static void main(String[] args) {
		
		Cell cell = new Cell(1, 2, Mark.X);
		System.out.println(cell.toString());
		
		Cell cell2 = new Cell(5, 2, Mark.X);
		System.out.println(cell.equalsMark(cell2 ));
		
	}

}
