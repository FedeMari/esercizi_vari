package veicoli;

public class Furgone extends Veicolo{
	private int capacita;
	
	public Furgone (String marca, String targa, int anno, int cilindrata, int capacita) { //creo furgone
		super(marca, targa, anno, cilindrata);
		this.capacita=capacita;
	}
	
	/*@Override
	public String toString() {
		return "Furgone [" + super.toString() + ", capacita" + capacita + "]";
	}*/
	
	@Override
	public String toString() {
		return super.toString() + "_" + capacita;
	}

	public int getCapacita() {
		return capacita;
	}

	/*public void setCapacita(int capacita) {
		this.capacita = capacita;
	}*/
}
