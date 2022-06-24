package konetsky.wladislaw.xogame.exception;

public class ExceededPlayersCountException extends RuntimeException {

	private static final long serialVersionUID = 7097210508514030423L;
	
	public ExceededPlayersCountException() {
		super();
	}
	
	public ExceededPlayersCountException(String message) {
		super(message);
	}
	
	public ExceededPlayersCountException(Exception e) {
		super(e);
	}
	
	public ExceededPlayersCountException(String message, Exception e) {
		super(message, e);
	}

}
