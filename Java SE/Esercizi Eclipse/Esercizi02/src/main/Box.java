package main;

public class Box implements Comparable<Box>{
	// questa volta, i tre campi sono PRIVATI
	// ===> saranno ACCESSIBILI solamente attraverso l'INTERFACCIA della classe (METODI PUBBLICI)
	protected double h, w, d;
	
	private static int boxCounter = 0;
	
	public static int getBoxCounter() {return boxCounter;}

	// il compilatore ci regala il costruttore di default, fimo a che non aggiungiamo
	// esplicitamente un costruttore. In tal caso, se vogliamo continuare ad utilizzare
	// il costruttore di default, lo dobbiamo dichiarare esplicitamente
	public Box() {
		boxCounter++;
	}
	
	public Box(double w, double h, double d) {
		this.h = h;
		this.w = w;
		this.d = d;
		boxCounter++;
	}

	// costruttore che crea un CUBO (lati tutti uguali)
	
	public Box(double l) {
//		this.h = l;
//		this.w = l;
//		this.d = l;
		this(l, l, l);
		boxCounter++;
	}
	
	// costruttore di COPIA o CLONAZIONE
	public Box (Box orig) {
		this(orig.w, orig.h, orig.d);
//		h = orig.h;
//		w = orig.w;
//		d = orig.d;
		boxCounter++;
	}
	
	//imposta le dimensioni del parallelepipedo
	public boolean setDim(double w, double h, double d) {
		// GUARDIA: evita che nei tre campi vengano scritti dei valori NON autorizzati
		
		// se il metodo restituisce FALSE, allora significa che gli argomenti
		// passati dal codice chiamante erano sbagliati
		
		if (w < 0 || h < 0 || d < 0)
			return false;

		this.w = w;
		this.h = h;
		this.d = d;
		
		return true;
	}
	
	public double Volume() {
		return w * h * d;
	}

	public double getW() {return w;}
	public double getH() {return h;}
	public double getD() {return d;}

	public void setW(double w) {this.w = w;}
	public void setH(double h) {this.h = h;}
	public void setD(double d) {this.d = d;}

	//restituisce true se o è uguale all'oggetto che ha invocato il metodo
	public boolean equalTo(Box o) {
		if(o.w == this.w && o.h == this.h && o.d == this.d)
			return true;
		return false;
	}

	// il metodo "CompareTo()" deve CONFRONTARE due Box e decidere chi è il più grande, il più piccolo
	// oppure se sono uguali.
	// per fare ciò ha bisogno di un CRITERIO di CONFRONTO basato su un particolare CRITERIO di ORDINAMENTO
	
	// in questo caso, per confrontare due Box, scelgo come criterio di ordinamento uno basato sul VOLUME
	// Box1 > Box2, se V di Box1 > V di Box2
	
//	public int CompareTo(Box o) {
//		// se i due Box sono UGUALI, il metodo deve restuire 0
//		if (this.Volume() == o.Volume())
//			return 0;
//
//		// se il Box corrente ha un V > di quello del Box passato come parametro, il metodo deve restuire
//		// un numero positivo
//		
//		if (this.Volume() > o.Volume())
//			return 1;
//		
//		return -1;
//	}
	
	@Override
	public String toString() {
		return "[Box: H = " + h + ", W = " + w + ", D = " + d + "]";
	}

	@Override
	public int compareTo(Box o) {
		// se i due Box sono UGUALI, il metodo deve restuire 0
		if (this.Volume() == o.Volume())
			return 0;
	
		// se il Box corrente ha un V > di quello del Box passato come parametro, il metodo deve restuire
		// un numero positivo
		
		if (this.Volume() > o.Volume())
			return 1;
		
		return -1;
	}
}
