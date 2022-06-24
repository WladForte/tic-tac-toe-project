package konetsky.wladislaw.xogame.entity;

/**
 * An abstract class for a cell of the board. All cells have a common feature - their mark. 
 * The concrete implementation of the cell defines its other additional parameters for the board.
 */
public abstract class AbstractCell implements java.io.Serializable {
	
	private static final long serialVersionUID = 4475084693574039972L;
	
	private Mark mark;
	
	protected AbstractCell() {
		this.mark = Mark.EMPTY;
	}
	
	protected AbstractCell(Mark mark) {
		this.mark = mark;
	}
	
	public Mark getMark() {
		return this.mark;
	}
	
	public void setMark(Mark mark) {
		this.mark = mark;
	}
	
	public boolean equalsMark(AbstractCell other) {
		if (this.getMark() == other.getMark()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean equalsMarkMult(AbstractCell ... cells) {
		boolean result;
		result = false;
		
		if (cells.length < 2) {
			return true; // the cell is compared to itself
		}
		
		for (int i = 0; i < cells.length - 1; i++) {
			if(!cells[i].equalsMark(cells[i + 1])) {
				result = false;
			} else {
				result = true;
			}
		}
		return result;
	}

}
