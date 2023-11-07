package veicoli;

public class Moto extends Veicolo{
	private int nTempi;

	public Moto(String marca, String targa, int anno, int cilindrata, int nTempi) {	//creo oggetto moto
		super(marca, targa, anno, cilindrata);	//uso costruttore veicolo
		this.nTempi=nTempi;
	}
	
	/*@Override
	public String toString() {
		return "Moto [" + super.toString() + ", nTempi=" + nTempi + "]";
	}*/
	
	@Override
	public String toString() {
		return super.toString() + "_" + nTempi;
	}

	public int getnTempi() {
		return nTempi;
	}

	/*public void setnTempi(int nTempi) {
		this.nTempi = nTempi;
	}*/

}
