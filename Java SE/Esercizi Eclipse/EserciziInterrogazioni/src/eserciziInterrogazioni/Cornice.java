package eserciziInterrogazioni;

public class Cornice {
	String name;
	String x= "";
	String s= "  "; //do due spazi iniziali per i due "+" della cornice
	
	public Cornice (String name) {
		this.name=name;
		for(int i=0; i<name.length();i++) {
			x=x+"-";
			s=s+" "; //conteggio spazi vuoti per dopo cornice
		}
	}
	
	
//	@Override
//	public String toString() {
//		return "+" + x + "+\n|" + name + "|\n+" + x + "+"; 
//	}
	
	//se vuoi aggiungere il cuore
	@Override
	public String toString() {
		return "+" + x + "+   _  _\n|" + name + "|  / \\/ \\ \n+" + x + "+ LOVE YOU \n" + s + "  \\    /\n" + s + "   \\  /\n" + s + "    \\/"; 
	}
}
