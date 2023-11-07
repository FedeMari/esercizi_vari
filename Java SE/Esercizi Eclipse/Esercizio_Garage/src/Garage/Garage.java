package Garage;

import java.util.Scanner;

import FileVeicoli.*;
import veicoli.*;

public class Garage {
	static Scanner in = new Scanner(System.in);
	
	private Veicolo[] posti; //dichiaro un array di tipo Veicolo perchè voglio ci siano solo oggetti

	public Garage() {
		int n = 15;
		posti = new Veicolo[n]; //inizializzo a 15 posti
		
		for(int i = 0; i < n; i++) {        
			posti[i] = FileVeicoli.avvioGarage(i);
		}
	}

	public boolean addVeicolo(Veicolo v, int posizione) {
		if (posizione < 0 || posizione >= posti.length) {
			return false;
		}
		if (posti[posizione] != null) {
			return false;
		}
		posti[posizione] = v;
		return true;
	}

	// overload altra possibilità per aggiungere un veicolo
	public int addVeicolo(Veicolo v) {
		for (int i = 0; i < posti.length; i++) {
			if (posti[i] == null) {
				posti[i] = v;
				return i;
			}
		}
		return -1;
	}

	public Veicolo removeVeicolo(int posizione) {
		Veicolo v = posti[posizione];
		posti[posizione] = null;
		return v;
	}


	public Veicolo[] getPosti() {
		return posti;
	}


	/*public void setPosti(Veicolo[] posti) {
		this.posti = posti;
	}*/

}
