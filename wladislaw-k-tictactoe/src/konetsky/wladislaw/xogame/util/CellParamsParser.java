package konetsky.wladislaw.xogame.util;

import konetsky.wladislaw.xogame.entity.Cell;
import konetsky.wladislaw.xogame.exception.UnparseableInputException;

/**
 * This class parses the string into the cell parameters (x and y coordinates).
 */
public class CellParamsParser implements BaseParser<String, Cell> {

	private static final CellParamsParser INSTANCE = new CellParamsParser();
	
	private CellParamsParser() {}
	
	public static CellParamsParser getInstance() {
		return INSTANCE;
	}
	
	@Override
	public Cell parse(String input) throws UnparseableInputException {
		int xCoord;
		int yCoord;	
		
		try {
			String[] params = input.split("\\s*,\\s*");
			xCoord = Integer.parseInt(params[0]);
			yCoord = Integer.parseInt(params[1]);
		} catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
			throw new UnparseableInputException("The input cannot be parsed, please, provide the input in the following manner:\n"
					+ "xCooirdinate(integer number), yCoordinate(integer number) - e.g. \"1,2\"");
		}
		
		return new Cell(xCoord, yCoord);
	}

}
