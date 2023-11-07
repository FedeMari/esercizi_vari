package abbonamenti;

import clienti.Clienti;
import veicoli.Veicolo;

public class Abbonamenti {
	private Clienti [] listaAbb;
	
	public Abbonamenti() {
		listaAbb = new Clienti [15];
	}
	
	public boolean addCliente(Clienti c) {
		for(int i=0;i<listaAbb.length;i++) {
			if(listaAbb[i]!=null) {
				listaAbb[i]=c;
				return true;
			}
		}
		return false;
	}
	
	public void stampaCliente(int posizione) {
		System.out.println(listaAbb[posizione]);
	}	
}
