package Esercizi04;

import java.io.*;

public class Esercizi {
	
	static String file_name = "TESTO.txt";
	static String data_dir = "data/";	//prima cartella che troviamo nel file di progetto
	//questo è percorso relativo
	static String file_path = data_dir + file_name;
	
	static String copy_file_name = "TESTO_COPIA.txt";
	static String copy_file_path = data_dir + copy_file_name;
	
	public static void main(String[] args) {
		//BRRead();
		//showFile();
		//copyFile();
		//copyFile2();

	}

	private static void copyFile2() {
		/*
		 * Le risorse qui vengono aperte nel try e una volta uscite dallo scope vengono 
		 * deallocate e prima di farlo le va a chiudere e inoltre controlla anche se siano nulle
		 *
		 * Possono usare questo try solo le classi che implementano l'interfaccia CLOSABLE 
		 * (che a sua volta estende l'interfaccia AUTOCLOSABLE) e che implementano il METODO close(). 
		 * 
		 * QUINDI IL TRY-WITH-RESOURCE SI USA SOLO CON LE RISORSE (OSSIA LE CLASSI SOPRA DESCRITTE)!!
		 */
		
		
		try (var fin = new FileInputStream(file_path);
				var fout = new FileOutputStream(copy_file_path);) {
			int ch;
			
			do {
                // leggiamo un carattere alla volta
                ch = fin.read();
                
                if (ch == -1)
                    break;
                fout.write(ch); //se aggiungi 10 già stai cifrando
            } while (true);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println(" BYE BYE !!");
	}

	private static void copyFile() {
		//voglio copiare un file di testo in un file di copia (backup)
		//useremo due Byte Stream: uno di input e uno di output
		
		FileInputStream fin = null;		//dovremo chiudere entrambi i flussi alla fine
		FileOutputStream fout = null;
		
		try {
            fin = new FileInputStream(file_path);
            fout = new FileOutputStream(copy_file_path);
            do {
                // leggiamo un carattere alla volta
                int ch = fin.read();
                if (ch == -1)
                    break;
                fout.write(ch); //se aggiungi 10 già stai cifrando
            } while (true);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                // molto importante questa guardia
                if (fin != null) {
                    fin.close();
                if(fout != null)
                    fout.close();
                }
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }
        }
	}

	private static void showFile() {
		// apriamo il file in lettura
		
		FileInputStream fin_stream = null;
		try {
			fin_stream = new FileInputStream(file_path);	//è un Byte Stream
			
			/* a questo punto il file è stato APERTO e ora possiamo leggerlo con un CICLO
			 * fino a quando non viene restituito un EOS = EOF = -1
			 */
			
			int ch;
			
			do {
				//leggo tutto il file, un carattere alla volta
				
				ch=fin_stream.read(); 
				
				if(ch==-1)
					break;
				
				System.out.println((char) ch);
			}while(true);
			//se lasciassi solo questo close, entrando in una eccezione non si chiuderebbe il flusso
			//fin_stream.close();
			return;
		} catch (FileNotFoundException e) { //questa non causa problemi perché se il file non ci sta
			System.out.printf("%s - file inesistente (%s).", e.getMessage(), file_path);
			return;
		} catch (IOException e) { //però questa si, se si verifica una IO salta tutte le altre istruzioni
			System.out.printf("errore durante l'accesso al file (%s) - %s.", e.getMessage(), file_path);
			return; //con return uscirebbe senza eseguire le ultime istruzioni quindi serve il finally
		}
		finally{
			if(fin_stream != null) {
				try {
					fin_stream.close();
				}catch (IOException e) {
					System.out.println(e.getMessage());				
				}
			}			
		}
		
		/*if(fin_stream != null) {
			try {
				fin_stream.close();
			}catch (IOException e) {
				System.out.println(e.getMessage());				
			}
		}*/
	}

	private static void BRRead() {
        // 1) trasformiamo "System.in" (byte stream) in un character stream (stream di caratteri Unicode)

        var ch_stream = new InputStreamReader(System.in); 

        // 2) bufferizziamo lo stream di caratteri

        var br = new BufferedReader(ch_stream);

        // il byte stream "System.in" è INFINITO <===> per esso NON esiste un END OF STREAM
        // ===> l'unico modo che ho per avere una indicazione di FINE STREAM e quello di chiederlo all'utente

        System.out.println("Enter characters, 'q' to quit.");
        
        char ch = 0;
        int n = 0;
        
        char [] arr = new char [100];
        
        
	        while(ch!='q' && n<arr.length) {
	        	try {
					ch = (char) br.read();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
	        	
	        	if (ch == '\r' || ch == '\n' ) {                	
	                continue; 
	            }
	        	
	        	for(int i=0;i<arr.length;i++) {
	        		
	        		if(arr[i]!='\u0000') {
	        			continue;
	        		}
	        		
	        		arr[i]=ch;
	        		n++;
	        		break;
	        	}
	        }
	        
	        for(int i=0; i<arr.length; i++) {
	        	System.out.print(arr[i]);        	
	        }  
    }
}
