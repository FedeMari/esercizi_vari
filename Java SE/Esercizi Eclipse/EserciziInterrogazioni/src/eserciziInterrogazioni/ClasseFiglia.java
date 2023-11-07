package eserciziInterrogazioni;

public class ClasseFiglia extends ClasseMadre{
	boolean primogenita;
	public ClasseFiglia(String name, int eta, boolean primogenita) {
		super(name,eta);
		this.primogenita=primogenita;
	}
	@Override
	public String toString() {
		return "Il nome della figlia e' " + name + ", l'eta' e' " + eta + ", " + primogenita;
	}
	
	
	

}
