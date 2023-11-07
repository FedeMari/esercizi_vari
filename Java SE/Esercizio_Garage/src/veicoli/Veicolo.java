package veicoli;

public class Veicolo {
	protected String marca; 	//le voglio protected altrimenti non si vedono nelle sottoclassi
	protected String targa;
	protected int anno;
	protected int cilindrata;
	
	public Veicolo (String marca, String targa, int anno, int cilindrata) {	//creo oggetto veicolo
		this.marca=marca;
		this.targa=targa;
		this.anno=anno;
		this.cilindrata=cilindrata;
	}

	/*@Override
	public String toString() {
		return "[marca = " + marca + ", anno = " + anno + ", cilindrata = " + cilindrata + "]";
	}*/
	
	@Override
	public String toString() {
		return marca + "_" + targa + "_" + anno + "_" + cilindrata;
	}

	public String getMarca() {
		return marca;
	}

	/*public void setMarca(String marca) {
		this.marca = marca;
	}*/

	public String getTarga() {
		return targa;
	}

	/*public void setTarga(String targa) {
		this.targa = targa;
	}*/

	public int getAnno() {
		return anno;
	}

	/*public void setAnno(int anno) {
		this.anno = anno;
	}*/

	public int getCilindrata() {
		return cilindrata;
	}

	/*public void setCilindrata(int cilindrata) {
		this.cilindrata = cilindrata;
	}*/
	
	
	
}
