package abbonamenti;

import java.util.Scanner;

import clienti.Clienti;
import veicoli.Veicolo;

public class Abbonamenti {
	static Scanner in = new Scanner(System.in);
	
	private Clienti [] lista;
	
	public Abbonamenti() {
		int n=15;
		lista = new Clienti [n];
	}
	
	public boolean addCliente(Clienti c) {
		for(int i=0;i<lista.length;i++) {
			if(lista[i]!=null) {
				lista[i]=c;
				return true;
			}
		}
		return false;
	}
	
	public Clienti removeVeicolo(int posizione) {
		Clienti v = lista[posizione];
		lista[posizione] = null;
		return v;
	}


	public Clienti[] getLista() {
		return lista;
	}
}
