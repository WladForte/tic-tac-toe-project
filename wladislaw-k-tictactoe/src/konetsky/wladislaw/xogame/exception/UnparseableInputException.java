package konetsky.wladislaw.xogame.exception;

public class UnparseableInputException extends RuntimeException {

	private static final long serialVersionUID = 8220063568209203639L;
	
	public UnparseableInputException() {
		super();
	}
	
	public UnparseableInputException(String message) {
		super(message);
	}
	
	public UnparseableInputException(Exception e) {
		super(e);
	}
	
	public UnparseableInputException(String message, Exception e) {
		super(message, e);
	}

}
