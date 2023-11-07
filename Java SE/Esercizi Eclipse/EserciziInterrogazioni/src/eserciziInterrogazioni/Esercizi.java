package eserciziInterrogazioni;

import java.util.Iterator;
import java.util.Random;

public class Esercizi {

	public static void main(String[] args) {
		//arrayInverso();
		//overrideToString();
		//moltiplicazioniFor();
		corniceNome("GABGO");
		//dueGiocatori(10,8);
		//trasformaBinario("1001");
		//System.out.println(potenze(2,5));
		//stampaArrayPiuVolte();
		//sommaArray(5);
		//fattoriale(3);
		//sommaEradice(0);
	}

	private static void sommaEradice(int n) {
		int max=n;
		int tot=0;
		int k=0;
		
		String mainThreadName = Thread.currentThread().getName();
		
		System.out.println("Entering father thread: " + mainThreadName);
		
		NuovoThread nt = new NuovoThread(n);

		nt.start();
		
		try {
			for(int i=0; i<=max+2;i++) { //se non mettessimo i<=max+2 i casi 1,2,3 non entretrebbero nell'if e non sovrascriverebbero il k
				if(tot>max) {
					k=i-2; /* vogliamo innanzitutto legare k al numero di iterazioni k=i
							* successivamente sappiamo che vogliamo fermarci al numero 
							* precedente rispetto al numero con il quale usciamo dal ciclo
							* quindi k=i-1;
							* Pero sappiamo anche che i parte da 0 quindi k=i-2;
							*/
					break;
				}
				tot=tot+i;
				Thread.sleep(500);
			}			
		} catch (InterruptedException e) {
			System.out.println("Main thread interrupted.");
		}
		System.out.println("Main thread exiting.");
		System.out.println("Il massimo numero intero per non sforare con la somma e' " + k);
		
		
	}

	private static void fattoriale(int start) {
		/*
		 * stampa il fattoriale di un numero 
		 */
		int n = start;
		int tot=1;
		
		if(n==0) {
			tot=1;
		}
		else if(n<0) {
			System.out.println("Impossibile procedere!");
			return;
		}
		else {			
			do {
				for(int i=0;i<n;i++) {
					tot=tot*(n-i);
				}
			}while((n-1)==1);			
		}
		System.out.println(tot);
		
	}

	private static void sommaArray(int n) {
		/*dati tre array di interi di stesso indice somma gli elementi nella
		 * stessa posizione ed assegna il risultato alla stessa posizione
		 * di un quarto array
		 */
		
		int [] ar1 = new int [n];
		int [] ar2 = new int [n];
		int [] ar3 = new int [n];
		int [] ar4 = new int [n];
		
		for(int i=0, j=0, k=0; i<=ar1.length-1 & j<=ar1.length-1 & k<=ar1.length-1;i++, j++, k++) {
			ar1[i]=i+1;
			ar2[j]=j+1;
			ar3[k]=k+1;
		}
		
		for(int z=0; z<=ar4.length-1;z++) {
			ar4[z]=ar1[z]+ar2[z]+ar3[z];
			System.out.print(" " + ar4[z]);
		}
		
		
	}

	private static void stampaArrayPiuVolte() {
		/*
		 * stampa un array tre volte usando solo un ciclo for e niente altro
		 */
		int [] arr = {1,2,3,4,5,6,7,8,9,10};
		
		for(int i=0; i<arr.length*3;i++) {
			System.out.print(" " + arr[i%arr.length]);
			/*
			 * usiamo il modulo per andare avanti dopo l'indice 9 ossia il primo 10
			 * infatti dopo avremo 10/10 = 1 con resto di 0 ed è il resto il ritorno che viene dato
			 * 
			 */
		}
		
		
	}

	private static int potenze(int base, int esponente) {
		/*
		 * metodo per effettuare le potenze con base e esponente positivi
		 */
		int b = base;
		int e = esponente;
		int tf = 0; //totale finale
		for(int i=0; i<=e; i++) {
			if(i==0) { //caso esponente 0 il risultato deve essere 1
				tf=1;
				continue;
			}
			if(i==1) { //caso esponente 1 il risultato è uguale alla base
				tf=b;
				continue;
			}
			if(i==2) { //caso esponente 2 il risultato è la base per se stessa
				tf=b*b;
				continue;
			}			
			tf=tf*b;	//in tutti gli altri casi continuo con un totale progressivo a moltiplicare per la stessa base			
		}
		return tf;		
	}

	private static void trasformaBinario(String binario) {
		/*dato un binario passato per stringa trasformarlo in un decimale
		 *creare anche un metodo proprio che faccia potenze e
		 *far lanciare un'eccezione qualora ci fossero numeri diversi da 0 e 1 
		 */
		String x = binario;
		int e = 0; //esponente iniziale 0
		int d = 0;	//decimale finale
		try {
			for(int i=x.length()-1;i>=0;i--) {
				/*Quello che vogliamo controllare è che il carattere sia diverso da 0 o 1
				 * per farlo dovremo sottrarre 48 perchè i caratteri 0 e 1 corrispondono rispettivamente a 48 e 49 in intero dalla tabella ascii
				 * Inoltre vogliamo && perché voglio che se un carattere è diverso da entrambi mi lancia eccezione
				 */
				if((x.charAt(i)-48)!=0 && (x.charAt(i)-48)!=1) {
					throw new EccezioneBinario();
				}
				d = d + (int) (((x.charAt(i))-48)*(Math.pow(2, e))); //il decimale è ottenuto con la somma per le potenze di due
				e++;
			}
			System.out.println("Il numero binario " + x + " e' stato convertito nel numero decimale " + d);
		}catch(EccezioneBinario ex) {
			System.out.println("Il numero inserito deve contenere solo '0' e '1'!");
			return;
		}
	}

	private static void dueGiocatori(int tentativi, int nFacce) {
		/*Costruisci un metodo per cui dopo un lancio di dadi il dado con 
		 * la faccia maggiore perde la faccia piu grande		 * 
		 */
		Random rnd = new Random();
		int z1 =nFacce, z2=nFacce; //facce dei due dadi
		int z3 = tentativi; //variabile di appoggio per poter stampare alla fine il n°di tentativi richiesti
		int x,y,p=0;	//x,y controllo dei lanci, p per i pareggi
		
		
		while(tentativi!=0 && z1>0 && z2>0) {
			x = rnd.nextInt(nFacce)+1;
			y = rnd.nextInt(nFacce)+1;
			if(x>y) {	//se x è maggiore di y, x perde la faccia maggiore
				z1--;
				tentativi--;
				System.out.println("x perde una faccia");
				continue;
			}
			else if(y>x) { //se y è maggiore di x, y perde la faccia maggiore
				z2--;
				tentativi--;
				System.out.println("y perde una faccia");
				continue;
				
			}
			else if(x==y){
				tentativi--;
				System.out.println("nessuno perde una faccia");
				p++;
				continue;
			}
		}
		System.out.println("\nIl numero di tentativi richiesti e' stato: " + z3 + 
				"\nIl numero inserito della faccia maggiore dei due dadi e' stato: " + nFacce +
				"\nIl numero di facce rimaste a x e': " + z1 + 
				"\nIl numero di facce rimaste ad y e': " + z2 +
				"\nIl numero di pareggi effettuati e': " + p);
	}

	private static void corniceNome(String name) {
		// una cornice attorno ad una stringa data

		Cornice a = new Cornice(name);
		System.out.println(a.toString());
		
	}

	private static void moltiplicazioniFor() {
		/*costruisci tre array di interi dove nel terzo andra' salvato il prodotto
		 * tra l'elemento '0' a crescere del primo e l'elemento n a decrescere del secondo
		 * array di stessa dimensione con valori definiti  
		 */
		
		int[]arr1= {1,2,3,4,5};
		int[]arr2= {5,4,3,2,1};
		int[]arr3= new int [arr1.length];
		
		//System.out.println(arr1.length);
		for (int i = 0; i < arr1.length; i++) {
			arr3[i]=arr1[i]*(arr2[(arr2.length-1)-i]);
								/*
								 * in quanto arr2.length-1 è posizione 4 dell'array (valore 5)
								 * voglio proceda a ritroso quindi -i così sottraggo di uno man mano
								 */
		}
		
		for (int i = 0; i < arr1.length; i++) {
			System.out.print(" " + arr3[i]);
		}		
	}

	private static void overrideToString() {
		//definisci una classe madre e una classe figlia e mostra override del 
		//metodo toString della classe Object
		
		ClasseMadre a = new ClasseMadre("Giulia",34);
		
		ClasseFiglia b = new ClasseFiglia("Valeria",38,false);
		
		System.out.println(a.toString()); //questo ovviamente non stamperà perchè un reference
											// java tenterà di stampare l'indirizzo in memoria
		System.out.println(b.toString());	//avendo fatto l'override del metodo toString() di Object
												//qui stamperà quanto impostato nell'override
		
	}

	private static void arrayInverso() {
		//definisci un array di stringhe e stampalo al contrario
		
		String [] arr = {"1","2","3","4","5"};
		
		for(int i=arr.length-1;i>=0;i--) {
			System.out.print(" " + arr[i]);
		}		
	}

}
