package fr.vyxs.routes.exception;

public class PositionException extends Exception {
	
	private static final long serialVersionUID = -928081954614641154L;

	public PositionException() {
		super();
	}

	public PositionException(String msg) {
		super(msg);
	}
	
	public PositionException(String msg, Throwable throwable) {
		super(msg, throwable);
	}
}
