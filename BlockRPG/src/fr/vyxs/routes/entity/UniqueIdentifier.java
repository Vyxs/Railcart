package fr.vyxs.routes.entity;

public class UniqueIdentifier {

	protected final Long id;
	
	public UniqueIdentifier(Number number) {
		this.id = number.longValue();
	}
	
	public final Long getID() {
		return id;
	}

}
