package konetsky.wladislaw.xogame.board;

import java.util.Collection;

import konetsky.wladislaw.xogame.entity.Cell;
import konetsky.wladislaw.xogame.exception.NonexistentCellException;

/**
 * This interface is the extension of a Board Interface, it's implementations are based upon on a Cell class (point with 2 coordinates), 
 * which are stored in a collection (List, Set, List of Lists, etc).
 */
public interface CollectionBoard extends Board<Collection<Cell>> {
	
	Cell getCell(int x, int y) throws NonexistentCellException;

}
