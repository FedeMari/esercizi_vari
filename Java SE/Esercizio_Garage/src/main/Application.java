package main;

import veicoli.*;

import java.io.*;
import java.util.Scanner;

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
		
		avvioGarage();
		
		//Abbonamenti abbonamenti = new Abbonamenti(); //oggetto di tipo cliente
		
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
					creaFileVeicolo(v,pos);
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
					eliminaFileVeicolo(v,pos);
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
		System.out.println("Stai per creare un utente. Inserisci un nome:\n");
		nome = in.next();
		String cognome;
		System.out.println("Inserisci un cognome:\n");
		cognome = in.next();
		int eta;
		System.out.println("Inserisci un'eta':\n");
		eta = in.nextInt();
		
		Clienti c = new Clienti(nome,cognome,eta);	//creato un cliente
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
	
	private static void creaFileVeicolo(Veicolo v, int pos) {
		
		try(var fout = new FileOutputStream (creaPercorso(v,pos))){
			byte [] scrittura = v.toString().getBytes();
			fout.write(scrittura); 
			
			/* char [] scrittura = v.toString().toCharArray();
			 * for(int i=0; i<scrittura.length;i++) {
				fout.write(scrittura[i]);
			}*/	
		}
		catch(Exception e) {
			
		}
	}
	
	private static String creaPercorso(Veicolo v, int pos) {
		
		String tipoVeicolo = v.getClass().getSimpleName();
		
		String file_name = tipoVeicolo + "_" + pos;
		
		String file_path = data_dir + file_name;
		
		return file_path;
	}

	private static void eliminaFileVeicolo(Veicolo v, int pos) {
		
		File file = new File(creaPercorso(v,pos));
		 
        boolean result = file.delete();
        
        if (result) {
            System.out.println("File is successfully deleted.");
        }
        else {
            System.out.println("File deletion failed.");
        }		
	}
	
	private static void avvioGarage() {
		//entriamo nella cartella
		File file = new File(data_dir);
		//vede ogni file e lo inserisce in un array come stringa e rimuove il tipo di file
		String [] backup = file.list();
		
		String [] buffer;
		
		for(int i=0; i<backup.length;i++) {
			buffer = backup[i].split("_");
			try(FileInputStream fin = new FileInputStream(data_dir + backup[i])){ //serve il path del file non solo backup[i]
				int ch;
				int n=0;
				String testo = "";				
				do {
	                // leggiamo un carattere alla volta, restituisce counque un intero
	                ch = fin.read();
	                //quando finisce il flusso ci fermiamo ed usciamo
	                if (ch == -1)
	                    break;
	                testo += (char)ch; //c'è bisogno di una conversione
	                if(ch == '_')
	                	n++;	                
	            } while (true);
				
				String [] letturaDati = testo.split("_");
				
				String marca = letturaDati[0];
				String targa = letturaDati[1];
				int anno = Integer.parseInt(letturaDati[2]);
				int cilindrata = Integer.parseInt(letturaDati[3]);
				
				int pos = Integer.parseInt(buffer[1]);
				
				switch(buffer[0]) {				
				case "Auto":
					int nPorte = Integer.parseInt(letturaDati[4]);
					String alimentazione = letturaDati[5];
					Auto auto = new Auto(marca, targa, anno, cilindrata, nPorte, alimentazione);
					
					garage.addVeicolo(auto,pos);
					
					break;
				case "Furgone":
					int capacita = Integer.parseInt(letturaDati[4]);
					Furgone furgone = new Furgone(marca, targa, anno, cilindrata, capacita);
					
					garage.addVeicolo(furgone,pos);
					
					break;
				case "Moto":
					int nTempi = Integer.parseInt(letturaDati[4]);
					Moto moto = new Moto(marca, targa, anno, cilindrata, nTempi);
					
					garage.addVeicolo(moto,pos);
					
					break;
				default:
					System.out.println("File non valido");
					break;			
				}
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}			
			buffer[0] = null;
			buffer[1] = null;
		}
		
	}
}
