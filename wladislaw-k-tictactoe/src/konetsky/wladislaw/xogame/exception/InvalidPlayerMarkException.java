package konetsky.wladislaw.xogame.exception;

/**
 * Exception is thrown when the player mark is defined as empty or the player or cell cannot have have certain mark.
 */
public class InvalidPlayerMarkException extends RuntimeException {

	private static final long serialVersionUID = -8545882014308569704L;
	
	public InvalidPlayerMarkException() {
		super();
	}
	
	public InvalidPlayerMarkException(String message) {
		super(message);
	}
	
	public InvalidPlayerMarkException(Exception e) {
		super(e);
	}
	
	public InvalidPlayerMarkException(String message, Exception e) {
		super(message, e);
	}
	
}
