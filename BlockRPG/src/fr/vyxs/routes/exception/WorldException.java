package fr.vyxs.routes.exception;

public class WorldException extends Exception {
	
	private static final long serialVersionUID = 2762218305867623965L;
	
	public WorldException(String msg) {
		super(msg);
	}
	
	public WorldException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

}
