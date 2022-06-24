package konetsky.wladislaw.xogame.entity;

/**
 * The class, containing the main properties of a single cell of the gameboard. This class is not a classic java bean 
 * (x and y coordinates can not be changed after initialization of the object).
 */
public class Cell extends AbstractCell {
	
	private static final long serialVersionUID = -5492667046169588801L;
	
	private final int x;    // x coordinate
	private final int y;    // y coordinate
	
	// creates a cell with empty mark
	public Cell(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public Cell(int x, int y, Mark mark) {
		this (x, y);
		this.setMark(mark);
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		result = prime * result + ((this.getMark() == null) ? 0 : this.getMark().hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		
		Cell other = (Cell) obj;
		
		if (this.x != other.x) {
			return false;
		}
		
		if (this.y != other.y) {
			return false;
		}
		if (this.getMark() != other.getMark()) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append(this.getClass().getSimpleName());
		b.append(" {x=").append(x);
		b.append(", y=").append(y);
		b.append(", mark=").append(getMark().toString()).append("}");
		return  b.toString();
	}
	
	

}
