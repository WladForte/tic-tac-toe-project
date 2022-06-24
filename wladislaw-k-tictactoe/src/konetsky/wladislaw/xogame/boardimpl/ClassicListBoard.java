package konetsky.wladislaw.xogame.boardimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import konetsky.wladislaw.xogame.board.CollectionBoard;
import konetsky.wladislaw.xogame.entity.AbstractCell;
import konetsky.wladislaw.xogame.entity.Cell;
import konetsky.wladislaw.xogame.entity.Mark;
import konetsky.wladislaw.xogame.exception.NonexistentCellException;

/**
 * This class contains a field of cells, and also contains logic for determining which player wins. The board is a square of cells. 
 * Cells are sorted in a Sorted Set.
 */
public class ClassicListBoard implements CollectionBoard, java.io.Serializable {
	
	private static final long serialVersionUID = -4882947285303260749L;
	public static final int DEFAULT_SIZE = 3;
	
	private final int size;
	private List<Cell> cells;
	
	// this will create a board with its default size
	public ClassicListBoard() {
		this.size = DEFAULT_SIZE;
		this.cells = new ArrayList<Cell>(size);;
		
		// filling board with empty cells starting from bottom left to top right
		for (int i = 1; i <= this.size; i++) {
			for (int j = 1; j <= this.size; j++) {
				cells.add(new Cell(i, j));
			}
		}
	}
	
	public ClassicListBoard(int size) throws IllegalArgumentException {
		if (size < 3) {
			throw new IllegalArgumentException("The size of the board cannot be less than 3");
		}
		this.size = size;
		this.cells = new ArrayList<Cell>(size);;
		
		// filling board with empty cells starting from bottom left to top right
		for (int i = 1; i <= this.size; i++) {
			for (int j = 1; j <= this.size; j++) {
				cells.add(new Cell(i, j));
			}
		}
	}
	
	@Override
	public int getSize() {
		return this.size;
	}
	
	@Override
	public List<Cell> getCells() {
		return this.cells;
	}
	
	@Override
	public List<Cell> getEmptyCells() {
		return cells.stream().filter(c -> c.getMark() == Mark.EMPTY).collect(Collectors.toList());
	}
	
	@Override
	public int countCellsByMark(Mark mark) {
		return (int) cells.stream().filter(cell -> cell.getMark() == mark).count();
	}
	
	public Cell getCell(int x, int y) throws NonexistentCellException {
		if ( (x < 1 || x > size) || (y < 1 || y > size) ) {
			throw new NonexistentCellException("No such indexes are applicable to the board, provide indexes no lesser than 1 and no greater than " + size);
		}
		
		Cell target;
		target = new Cell(x, y);
		
		for (Cell c : cells ) {
			if ( (c.getX() == target.getX()) && (c.getY() == target.getY()) ) {
				target = c;
			}
		}
		
		return target;
	}
	
	@Override
	public int getMaxPlayersCount() {
		return 2;
	}
	
	@Override
	public String getDescription() {
		return "The goal of the player on this board is to put 3 consecutive marks in a row, column or a diagonal.";
	}

	
	/**
	 * In this implementation, if there 3 or more cells with the same Mark in a row, column or diagonal, the is considered to have a winning condition. 
	 * Cells are stored in the following manner (example for the board with the size 3, inside each cell are indices of the inner board list):
	 * <pre>(6)(7)(8)</pre>
	 * <pre>(3)(4)(5)</pre>
	 * <pre>(0)(1)(2)</pre>
	 */
	@Override
	public boolean hasWinningCondition() {

		final int minWinCount = 3;    // cannot be less than 2 and more than the size of the board
		
		if (hasWinningRow(minWinCount) || hasWinningCol(minWinCount) || hasWinningMainDiags(minWinCount) || hasWinningSecondaryDiags(minWinCount)) {
			return true;
		} else {
			return false;
		}
	
	}
	
	/**
	 * a private method to check a winning condition through iterating rows of the board
	 * @param minWinCount - the minimal value of cells, that should have have the same mark, cannot be less than 2 and more than the size of the board
	 * @return true if the number cells in a row equals or is greater than then number of the argument <b>minWinCount</b>
	 */
	private boolean hasWinningRow(final int minWinCount) {
		boolean result;
		result = false;
		
		Cell[] tmpCells;    // the array of cells which contains objects to be compared
		tmpCells = new Cell[minWinCount];
		
		int newRowIndDelta;    // the delta value to shift from one virtual row to another, is incremented by the size of the board
		newRowIndDelta = 0;
		
		for (int i = 0; i < size; i++) {    // iterating through rows 
			for (int j = 0; j < (size - minWinCount + 1); j++) {    // iterating through a row and creating temporary arrays to be compared
				
				boolean needEstimation;    // if any of the cells is empty, the comparison of the temporary arrays stops, and the method switches to another temporary array
				needEstimation = true;
				
				for (int k = 0; k < minWinCount; k++) {    // filling the array of cells to be compared
					tmpCells[k] = cells.get(j + k + newRowIndDelta);
					if (tmpCells[k].getMark() == Mark.EMPTY) {
						needEstimation = false;
						break;
					}
				}
				
				if (needEstimation) {
					if (AbstractCell.equalsMarkMult(tmpCells)) {
						result = true;
						return result;
					}
				}
				
			}
			
			newRowIndDelta += size;
		}
		
		return result;
	}
	
	/**
	 * a private method to check a winning condition through iterating columns of the board
	 * @param minWinCount - the minimal value of cells, that should have have the same mark, cannot be less than 2 and more than the size of the board
	 * @return true if the number cells in a column equals or is greater than then number of the argument <b>minWinCount</b>
	 */
	private boolean hasWinningCol(final int minWinCount) {
		boolean result;
		result = false;
		
		Cell[] tmpCells;    // the array of cells which contains objects to be compared
		tmpCells = new Cell[minWinCount];
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < (size - minWinCount + 1); j++) {    // iterating through a column and creating temporary arrays to be compared
				
				boolean needEstimation;    // if any of the cells is empty, the comparison of the temporary arrays stops, and the method switches to another temporary array
				needEstimation = true;
				
				for (int k = 0; k < minWinCount; k++) {    // filling the array of cells to be compared
					tmpCells[k] = cells.get(i + j * size + k * size);
					if (tmpCells[k].getMark() == Mark.EMPTY) {
						needEstimation = false;
						break;
					}
				}
				
				if (needEstimation) {
					if (AbstractCell.equalsMarkMult(tmpCells)) {
						result = true;
						return result;
					}
				}
				
			}
		}
		
		return result;
	}
	
	private boolean hasWinningMainDiags(final int minWinCount) {
		boolean result;
		result = false;
		
		Cell[] tmpCells;    // the array of cells which contains objects to be compared
		tmpCells = new Cell[minWinCount];
		
		final int diagsToBeCompared;
		diagsToBeCompared = countDiagsToBeCompared(minWinCount);
		
		int firstStartingInd;
		firstStartingInd = minWinCount - 1;
		
		int subDiagsToCompare;    // the number of subdiagonals to loop through in a diagonal (on rows and columns it is a constant value)
		subDiagsToCompare = 1;
		
		// the first half (including corner element)
		for (int i = firstStartingInd; i < size; i++) {
			for (int j = 0; j < subDiagsToCompare; j++) {
				
				boolean needEstimation;    // if any of the cells is empty, the comparison of the temporary arrays stops, and the method switches to another temporary array
				needEstimation = true;
				
				for (int k = 0; k < minWinCount; k++) {    // filling the array of cells to be compared
					tmpCells[k] = cells.get(i + j * (size - 1) + k * (size - 1));
					if (tmpCells[k].getMark() == Mark.EMPTY) {
						needEstimation = false;
						break;
					}
				}
				
				if (needEstimation) {
					if (AbstractCell.equalsMarkMult(tmpCells)) {
						result = true;
						return result;
					}
				}
				
			}
			
			subDiagsToCompare++;
		}
		
		// the second half (excluding corner element)
		if (diagsToBeCompared > 1) {
			int secondStartingPoint;
			secondStartingPoint = size * 2 - 1;
			
			subDiagsToCompare -= 2;
			
			for (int i = secondStartingPoint; i <= (size * size - 1) - size * (size - minWinCount); i += size) {
				for (int j = subDiagsToCompare - 1; j > -1; j--) {
					
					boolean needEstimation;    // if any of the cells is empty, the comparison of the temporary arrays stops, and the method switches to another temporary array
					needEstimation = true;
					
					for (int k = 0; k < minWinCount; k++) {    // filling the array of cells to be compared
						tmpCells[k] = cells.get(i + j * (size - 1) + k * (size - 1));
						if (tmpCells[k].getMark() == Mark.EMPTY) {
							needEstimation = false;
							break;
						}
					}
					
					if (needEstimation) {
						if (AbstractCell.equalsMarkMult(tmpCells)) {
							result = true;
							return result;
						}
					}
					
				}
				
				subDiagsToCompare--;
			}
		}
		
		return result;
	}
	
	private boolean hasWinningSecondaryDiags(final int minWinCount) {
		boolean result;
		result = false;
		
		Cell[] tmpCells;    // the array of cells which contains objects to be compared
		tmpCells = new Cell[minWinCount];
		
		final int diagsToBeCompared;
		diagsToBeCompared = countDiagsToBeCompared(minWinCount);
		
		int firstStartingInd;
		firstStartingInd = size - minWinCount;
		
		int subDiagsToCompare;    // the number of subdiagonals to loop through in a diagonal (on rows and columns it is a constant value)
		subDiagsToCompare = 1;
		
		// the first half (including corner element)
		for (int i = firstStartingInd; i > -1; i--) {
			for (int j = 0; j < subDiagsToCompare; j++) {
				
				boolean needEstimation;    // if any of the cells is empty, the comparison of the temporary arrays stops, and the method switches to another temporary array
				needEstimation = true;
				
				for (int k = 0; k < minWinCount; k++) {    // filling the array of cells to be compared
					tmpCells[k] = cells.get(i + j * (size + 1) + k * (size + 1));
					if (tmpCells[k].getMark() == Mark.EMPTY) {
						needEstimation = false;
						break;
					}
				}
				
				if (needEstimation) {
					if (AbstractCell.equalsMarkMult(tmpCells)) {
						result = true;
						return result;
					}
				}
				
			}
			
			subDiagsToCompare++;
		}
		
		// the second half (excluding corner element)
		if (diagsToBeCompared > 1) {
			int secondStartingPoint;
			secondStartingPoint = size;
			
			subDiagsToCompare -= 2;
			
			for (int i = secondStartingPoint; i <= subDiagsToCompare * size; i += size) {
				for (int j = subDiagsToCompare - 1; j > -1; j--) {
					
					boolean needEstimation;    // if any of the cells is empty, the comparison of the temporary arrays stops, and the method switches to another temporary array
					needEstimation = true;
					
					for (int k = 0; k < minWinCount; k++) {    // filling the array of cells to be compared
						tmpCells[k] = cells.get(i + j * (size + 1) + k * (size + 1));
						if (tmpCells[k].getMark() == Mark.EMPTY) {
							needEstimation = false;
							break;
						}
					}
					
					if (needEstimation) {
						if (AbstractCell.equalsMarkMult(tmpCells)) {
							result = true;
							return result;
						}
					}
					
				}
				
				subDiagsToCompare--;
			}
		}
		
		return result;
	}
	
	/**
	 * a private method to count the number of diagonals of the board to be compared
	 * @param minWinCount - the minimal value of cells, that should have have the same mark
	 * @return the number of diagonal of the board to be compared
	 */
	private int countDiagsToBeCompared(final int minWinCount) {
		int result;
		result = (size - minWinCount) * 2 + 1;
		return result;
	}
	

}
