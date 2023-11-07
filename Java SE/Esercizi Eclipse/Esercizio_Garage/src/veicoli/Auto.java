package veicoli;

public class Auto extends Veicolo{
	private int nPorte;
	private String alimentazione;

	public Auto(String marca, String targa, int anno, int cilindrata, int nPorte, String alimentazione) { //creo auto
		super(marca, targa, anno, cilindrata);
		this.nPorte=nPorte;
		this.alimentazione=alimentazione;
	}
	
	/*@Override
	public String toString() {
		return "Auto [" + super.toString() + ", nPorte" + nPorte + ", alimentazione" + alimentazione + "]";
	}*/
	
	@Override
	public String toString() {
		return super.toString() + "_" + nPorte + "_" + alimentazione;
	}

	public int getnPorte() {
		return nPorte;
	}

	/*public void setnPorte(int nPorte) {
		this.nPorte = nPorte;
	}*/

	public String getAlimentazione() {
		return alimentazione;
	}

	/*public void setAlimentazione(String alimentazione) {
		this.alimentazione = alimentazione;
	}*/
}
