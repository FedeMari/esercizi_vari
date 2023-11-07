package FileVeicoli;

import java.io.*;
import Garage.*;
import veicoli.*;

public class FileVeicoli {
	static String file_name;
	static String data_dir = "data/";
	static String file_path;
	
	static Garage garage;
	
	public FileVeicoli() {
	}
	public static void creaFileVeicolo(Veicolo v, int pos) {
		
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
	
	public static String creaPercorso(Veicolo v, int pos) {
		
		String tipoVeicolo = v.getClass().getSimpleName();
		
		String file_name = tipoVeicolo + "_" + pos;
		
		String file_path = data_dir + file_name;
		
		return file_path;
	}

	public static void eliminaFileVeicolo(Veicolo v, int pos) {
		
		File file = new File(creaPercorso(v,pos));
		 
        boolean result = file.delete();
        
        if (result) {
            System.out.println("File is successfully deleted.");
        }
        else {
            System.out.println("File deletion failed.");
        }		
	}
	
	public static Veicolo avvioGarage(int posizione) {
		//entriamo nella cartella
		File file = new File(data_dir);
		//vede ogni file e lo inserisce in un array come stringa e rimuove il tipo di file
		String [] backup = file.list();
		
		String [] buffer;
		
		for(int w=0; w<backup.length;w++) {
			buffer = backup[w].split("_");
			int bf = Integer.parseInt(buffer[1]);
			if(bf==posizione) {
				try(FileInputStream fin = new FileInputStream(data_dir + backup[w])){ //serve il path del file non solo backup[i]
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
		            } while(true);
					
					String [] letturaDati = testo.split("_");
					
					String marca = letturaDati[0];
					String targa = letturaDati[1];
					int anno = Integer.parseInt(letturaDati[2]);
					int cilindrata = Integer.parseInt(letturaDati[3]);
					
					//int pos = Integer.parseInt(buffer[1]);
					
					switch(buffer[0]) {				
					case "Auto":
						int nPorte = Integer.parseInt(letturaDati[4]);
						String alimentazione = letturaDati[5];
						Auto auto = new Auto(marca, targa, anno, cilindrata, nPorte, alimentazione);
						
						return auto;
						//garage.addVeicolo(auto,posizione);
						
						//break;
					case "Furgone":
						int capacita = Integer.parseInt(letturaDati[4]);
						Furgone furgone = new Furgone(marca, targa, anno, cilindrata, capacita);
						
						return furgone;
						//garage.addVeicolo(furgone,posizione);
						
						//break;
					case "Moto":
						int nTempi = Integer.parseInt(letturaDati[4]);
						Moto moto = new Moto(marca, targa, anno, cilindrata, nTempi);
						
						garage.addVeicolo(moto,posizione);
						
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
			}
		}
		return null;
	}
}
