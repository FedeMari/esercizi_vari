package main;

import veicoli.*;

import java.io.*;
import java.util.Scanner;

import FileVeicoli.FileVeicoli;
import Garage.*;
import abbonamenti.Abbonamenti;
import clienti.Clienti;


public class Application {
	
	static String file_name;
	static String data_dir = "data/";
	static String file_path;
	
	static Garage garage;
	
	static Scanner in = new Scanner(System.in);
	
	
	public static void main(String[] args) {
		garage = new Garage(); //oggetto di tipo Garage
		
		avvioMenuPrincipale();		
	}

	private static void avvioMenuPrincipale() {
		int operazione = 0;		
		
		while (operazione != -1) {
			System.out.println("Cosa vuoi fare?\n");
			System.out.println("1)Inserisci un veicolo.");
			System.out.println("2)Rimuovi un veicolo dal garage.");
			System.out.println("3)Stampa la situazione del garage.");
			System.out.println("4)Ingresso nel menu utente.");
			System.out.println("-1)Esci dal garage.");

			operazione = in.nextInt();
			
			switch (operazione) {
			case 1:
				Veicolo v = creaVeicolo();
				//creaFileVeicolo(v);
				int pos;
				do{
					System.out.println("In che posizione vuoi inserire il veicolo? (0-14), (-1 per prima posizione disponibile)");
					pos = in.nextInt();
				}while(!(pos>=-1 && pos<=14));
				
				if(!(garage.addVeicolo(v,pos))) //se false attiva l'altro metodo
				{
					pos=garage.addVeicolo(v);	//questo si attiva solo se  il primo è false
				}
				if(pos!=-1) { 
					System.out.println("Veicolo inserito in posizione " + pos);
					//deve creare il file solo se effettivamente viene inserito un veicolo quindi mettiamo qui la chiamata al metodo
					FileVeicoli.creaFileVeicolo(v,pos);
				}
				else
				{
					System.out.println("Il Garage e' pieno");
				}
				
				break;
				
			case 2:
				System.out.println("Da quale posizione vuoi rimuovere il veicolo? (0-14), (-1 per posizione predefinita.)");
				pos = in.nextInt();
				v = garage.removeVeicolo(pos);
				if(v!=null) {
					System.out.println(v);
					//deve eliminare il file solo se effettivamente ne esisteva uno quindi mettiamo qui la chiamata al metodo
					FileVeicoli.eliminaFileVeicolo(v,pos);
				}
				else
				{
					System.out.println("La posizione nel garage e' libera.");
				}				
				break;
				
			case 3:
				stampaSituazione();
				break;
				
			case -1:
				System.out.println("Arrivederci.");
				break;
				
			case 4:
				avvioMenuSecondario();
				break;				
			default:
				System.out.println("Operazione non valida.");
			}
		}		
	}

	private static void avvioMenuSecondario() {
		int operazione2=0;
		while (operazione2 != 4) {
			System.out.println("Cosa vuoi fare?\n");
			System.out.println("1)Inserisci un nuovo utente.");
			System.out.println("2)Rimuovi un utente.");
			System.out.println("3)Stampa la situazione dell'utente.");
			System.out.println("4)Esci dal menu utente.");
			
			operazione2 = in.nextInt();
			
			switch(operazione2) {
			case 1:
				Clienti c = creaCliente();
				String scelta;						
				do {
					System.out.println("Stai per sottoscrivere un abbonamento.\nSe desideri proseguire digita Y, altrimenti per uscire dal menu utente digita N");
					scelta = in.next().toUpperCase();
				}while(!(scelta.equals("Y") || scelta.equals("N")));
				//devi usare gli equals per le stringhe che controllano il contenuto!
				if(scelta.equals("Y")) {
					int scelta2;
					do{
						System.out.println("1)Abbonamento Bronze: 1 Posto Riservato" + "\n2)Abbonamento Silver: 2 Posti Riservati" + "\n3)Abbonamento Gold: 4 Posti Riservati" + "\nQuale abbonamento desideri sottoscrivere? (digita il numero di riferimento)");
						scelta2= in.nextInt();
					}while(scelta2<=0 && scelta2>3);
					
				}
				else {
					System.out.println("Ritorno al menu precedente.\n");			
				}
				break;
				
			case 2:
				break;
				
			case 3:
				break;
				
			case 4:
				System.out.println("Arrivederci.");
				break;
			default:
				System.out.println("Operazione non valida.");
			}
		}
	}

	private static void stampaSituazione() {

		System.out.println("Situazione Garage:");
		//dobbiamo creare un get in garage che ci vada a prendere l'array di garage
		for (int j = 0; j < garage.getPosti().length; j++) {
			System.out.print("Posto: " + j + "\t");
			if (garage.getPosti()[j] != null) {
				System.out.println("Posto occupato.");
			}
			else {
				System.out.println("Posto Libero.");
			}
		}
		
		int pos;
		String choice;
		
		do {
			System.out.println("Desideri controllare una posizione specifica? (Y = yes, N = no)");
			choice = in.next().toUpperCase();
		}while(!(choice.equals("Y") || choice.equals("N")));
		//devi usare gli equals per le stringhe che controllano il contenuto!
		if(choice.equals("Y")) {
			do{
				System.out.println("Quale posizione vuoi consultare? (0-14)");
				pos = in.nextInt();
				Veicolo v = garage.getPosti()[pos];
				
				String proprietaBase = "Tipo veicolo: " + v.getClass().getSimpleName() + "\nTarga: " + v.getTarga();
				System.out.println(proprietaBase);
				
				String secondChoice;
				
				do {
					System.out.println("Desideri maggiori dettagli del veicolo selezionato? (Y = yes, N = no)");
					secondChoice = in.next().toUpperCase();
				}while(!(secondChoice.equals("Y") || secondChoice.equals("N")));
				
				if(secondChoice.equals("Y")) {
					//ricorda split ritorna un array con le parole splittate!
					String [] arr = v.toString().split("_");
					
					String proprietaComuni = proprietaBase + "\nMarca: " + arr[0] + "\nAnno di Immatricolazione: " + arr[2] 
							+ "\nCilindrata: " + arr[3];
					
					switch(v.getClass().getSimpleName()) {
					case "Auto":
						System.out.println(proprietaComuni + "\nNumero di Porte: " + arr[4] 
								+ "\nTipo di Alimentazione " + arr[5] + "\n");						
						break;
					case "Furgone":
						System.out.println(proprietaComuni + "\nCilindrata: " + arr[4] + "\n");
						break;
					case "Moto":
						System.out.println(proprietaComuni + "\nNumero di Tempi: " + arr[4] + "\n");
						break;
					default:
						System.out.println("Specifiche veicolo non trovate");
						break;					
					}										
				}
				else {
					System.out.println("Sistema in uscita.\n");			
				}				
			}while(!(pos>=0 && pos<=14));
		}
		else {
			System.out.println("Ritorno al menu precedente.\n");			
		}
	}			

	private static Clienti creaCliente() {
		String nome;
		System.out.println("Inserisci il tuo nome:\n");
		nome = in.next();
		String cognome;
		System.out.println("Inserisci il tuo cognome:\\n");
		cognome = in.next();
		int eta;
		do {
			System.out.println("Inserisci la tua eta:\\n");
			eta = in.nextInt();
		}while(eta<15 && eta>120);
		
		Clienti c = new Clienti(nome, cognome, eta);
		return c;
	}

	private static Veicolo creaVeicolo() {		
		int scelta=0;
		do{
			System.out.println("Che tipo di veicolo vuoi creare? Digita il numero di riferimento.\n1)Auto\n2)Moto\n3)Furgone");
			scelta = in.nextInt();
		}while(scelta>=4 && scelta<=0);
		
		Veicolo v = null; //devo inizializzare a null perchè non so ancora quale veicolo creare
		System.out.println("Inserisci la marca del veicolo");
		String marca = in.next();
		
		System.out.println("Inserisci la targa del veicolo");
		String targa = in.next();
		
		int anno=0;
		do{
			System.out.println("Inserisci l'anno di immatricolazione del veicolo");
			anno = in.nextInt();
		}while(anno<1884 || anno>=2024); //se metti && il sistema verificherà siano entrambe true per uscire
		
		System.out.println("Inserisci la cilindrata del veicolo");
		int cilindrata = in.nextInt();

		switch (scelta) {
		case 1:
			int nPorte=0;
			do {
				System.out.println("Inserisci il numero di porte dell'auto");
				nPorte = in.nextInt();
			}while(nPorte<=2 || nPorte>=6); //se metti && il sistema verificherà siano entrambe true per uscire
			
			System.out.println("Inserisci il tipo di alimentazione del veicolo");
			String alimentazione = in.next();
			
			v= new Auto(marca, targa, anno, cilindrata, nPorte, alimentazione);
			break;
		case 2:
			int nTempi=0;
			do{
				System.out.println("Inserisci il numero di tempi della moto (2 o 4)");
				nTempi = in.nextInt();
			}while(!(nTempi==2) && !(nTempi==4)); //deve verificare entrambe e il true deve diventare false!!
			
			v= new Moto(marca, targa, anno, cilindrata, nTempi);
			break;
		case 3:
			int capacita=0;
			do{
				System.out.println("Inserisci la capacita in termini di quantita' di persone trasportabili del furgone");
				capacita = in.nextInt();
			}while(capacita<=0 || capacita>=15); //se metti && il sistema verificherà siano entrambe true per uscire
			
			v= new Furgone(marca, targa, anno, cilindrata, capacita);
			break;
		default:
			System.out.println("Scelta non valida.");
			break;			
		}
		return v;
	}

}
