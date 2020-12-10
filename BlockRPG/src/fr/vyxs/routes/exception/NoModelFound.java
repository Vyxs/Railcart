package fr.vyxs.routes.exception;

public class NoModelFound extends Exception {

	private static final long serialVersionUID = 1304305839702766486L;

	public NoModelFound() {
		super("No model found.");
	}
	
	public NoModelFound(Throwable throwable) {
		super("No model found.", throwable);
	}
}
