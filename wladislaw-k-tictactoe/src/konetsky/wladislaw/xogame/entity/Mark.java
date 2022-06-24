package konetsky.wladislaw.xogame.entity;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class contains marks used the game for a single cell, or as a player identifier.
 */
public enum Mark {
	
	EMPTY {
		@Override
		public String toString() {
			return " ";
		}
	},
	X {
		@Override
		public String toString() {
			return "X";
		}
	},
	O {
		@Override
		public String toString() {
			return "O";
		}
	};
	
	/**
	 * The method returns a set of all marks, except <b>EMPTY</b> mark (it is considered as "inactive").
	 * @return an array of "active" marks.
	 */
	public static Set<Mark> geytAllActiveMarks() {
		return Stream.of(Mark.values()).filter((mark) -> mark != Mark.EMPTY).collect(Collectors.toSet());
	}	

}
