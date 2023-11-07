package clienti;

public class Clienti {
	private String nome;
	private String cognome;
	private int eta;
	
	public Clienti(String nome, String cognome, int eta) {
		this.nome = nome;
		this.cognome = cognome;
		this.eta = eta;
	}

	public String getNome() {
		return nome;
	}

	/*public void setNome(String nome) {
		this.nome = nome;
	}*/

	public String getCognome() {
		return cognome;
	}

	/*public void setCognome(String cognome) {
		this.cognome = cognome;
	}*/

	public int getEta() {
		return eta;
	}

	/*public void setEta(int eta) {
		this.eta = eta;
	}*/	
}
