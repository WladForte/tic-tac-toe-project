package konetsky.wladislaw.xogame.exception;

public class NonexistentCellException extends RuntimeException {

	private static final long serialVersionUID = 414002128059844848L;
	
	public NonexistentCellException() {
		super();
	}
	
	public NonexistentCellException(String message) {
		super(message);
	}
	
	public NonexistentCellException(Exception e) {
		super(e);
	}
	
	public NonexistentCellException(String message, Exception e) {
		super(message, e);
	}

}
