package main;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		StringDemo1();
		//StringDemo2();
		//Es_Stringa();
		//VarargsDemo1();
		//VarargsDemo2();
		//VarargsDemo3();
		//Occorrenza("amaca", 'a');
		//separatore("xxxxx_aaaaa_ggggg_ ... _zzzz_");
		//Dado();
		//GeneratoreNumeri(5,2);
		//ExceptionDemo1();
		//ExceptionDemo4();
		//customExceptionDemo1();
		
		/*Box [] arr = new Box [5];
		arr[0] = new Box(1.0,2.0,3.0);
		arr[1] = new Box(4.0,4.0,6.0);
		arr[2] = new Box(6.0,4.0,4.0);
		arr[3] = new Box(3.0,2.0,8.0);
		arr[4] = new Box(1.0,3.0,3.0);
		
		
		Sort(arr);*/
		
		//ricercaStringa();
	}
	
	
		
		
	

	private static void ricercaStringa() {
		String testo = "il monte è alto come il monte che si affaccia davanti al monte de tu sorella";
		int occorrenze = 0;
		int k=0,j=0;
		do {
			j = testo.indexOf("monte", k);
			
			if(j == -1) {
				break;
			}
			
			System.out.println("La posizione della parola \"monte\" e' " + j);
			
			occorrenze++;
			k = j + 4;
		}while(true);
		System.out.println("Il numero di occorrenze della parola \"monte\" e' " + occorrenze);
		
		/*
		 *  String str1 = "monte";

        while (true) {
            start_index = str.indexOf(str1, start_index);
            if (start_index == -1)
                break;
            System.out.println("Indice : " + start_index);
            nVolte++;
            start_index += str1.length();
        }
        System.out.println("Il numero di volte che appare il vocabolo ricercato è " + nVolte);
		 */
	}

	public static void Sort(Box [] arr) {
		for(int i=0; i<arr.length-1;i++) {
			for(int j=i+1; j<arr.length;j++) {
				if(arr[i].getW()<arr[j].getW()) {	//riordino in base al w
					Box temp = arr[j];
					arr[j]=arr[i]; 
					arr[i] = temp;				
				}
				System.out.println(arr[i]);
			}
		}
	}
	
	private static void customExceptionDemo1() {
		 int N = 50, M = 20;

	        var rnd = new Random();

	        for (int n = 0; n < N; n++) {
	            try {
	                compute(rnd.nextInt(M));
	            } catch (Maggiore5Exception e) {
	                System.out.println("CustomExceptionDemo1: eccezione ignota: " + e.getClass().getCanonicalName() + ", " + e.getMessage());
	            }
	        }
		
	}

	private static void compute(int a) throws Maggiore5Exception {
        System.out.println("Called compute(" + a + ")");
        if(a > 5)
            throw new Maggiore5Exception(a, "VALORE ERRATO: deve essere minore di 5");
        System.out.println("Normal exit");		
	}

	private static void ExceptionDemo4() {
		int N = 50, M = 20;

        var rnd = new Random();

        Box box = null;

        for (int n = 0; n < N; n++) {
            try {
                if (rnd.nextInt(M) == 0)
                    box = null;
                else
                    box = new Box(1,2,3);

                String str = box.toString();
                System.out.println(str);
            }
            catch (Exception e) {
                System.out.println("ExceptionDemo4: eccezione ignota: " + e.getClass().getCanonicalName() + ", " + e.getMessage());
                //break;
            }
        }
		
	}

	private static void ExceptionDemo1() {
		int num=10, den=0,div;
		try{		//in questo caso è un'eccezione di tipo unchecked non devo necessariamente catturarla
			div=num/den;
			int[] arr=new int[10];
			arr[10]=2;
		}catch(ArithmeticException ex) {	//eccezione figlia di Exception, più specifica viene catturata prima
			System.out.println("Impossibile effettuare l'operazione " + ex.getMessage());
		}catch(Exception ex) {
			System.out.println("Impossibile effettuare l'operazione " + ex.getMessage());
		}finally {
			System.out.println("Ciao");
		}
		
		double dnum=10, dden=2,ddiv;
		ddiv=dnum/dden;
		
		
		
		
		
	}
	//n=numero di iterazioni, r=range di valori
	private static void GeneratoreNumeri(int n, int r) {
		Random rand = new Random();
		int [] arr = new int [r];
		int x;
		
		for(int i=0;i<n;i++) 
		{
			x = rand.nextInt(r)+1; //ho generato un numero random tra 1 e r
			arr[x-1]=arr[x-1]+1;  	
			/*se esce 1 arr[1]=arr[1]+1; 
			 * se avessi messo arr[x]=k++; quando arr[1]=k++ ,ma anche quando arr[2] = k++
			 * quindi non faceva distinzioni avremmo avuto bisogno di tante variabili.
			 * il concetto è che il numero random che esce deve essere uguale alla posizione 
			 * nell'array e li aumentiamo il numero 
			 * 
			 */
		}
		for(int i=0;i<arr.length;i++)  //posizioni dell'array
		{
			for(int j=0;j<arr[i];j++)	//elementi nella posizione i
			{
				System.out.print("*");
			}
			System.out.println("");
		}
			
			/*if(x==1) 
			 {
				 arr[0]=k;
				 k++;
			 }*/
		System.out.println(arr[0] + " " + arr[1]);		
	}
	
	private static void Dado() {
		Random rand = new Random();
		
		/*int x = rand.nextInt(7);
		if(x!=0) {
			System.out.println(x);
		}*/
		
		//System.out.println(rand.nextInt(6)+1);
		
		int y = rand.nextInt(6)+1;
		if(y%2==0) {
			System.out.println(y + " Numero pari.");
		}
		else
		{
			System.out.println(y + " Numero dispari.");
		}
		
	}
	private static void separatore(String s) {
		int n=0;		
		for(int i=0; i<s.length();i++) //questo for serve solo per per capire la dimensione dell'array
		{
			if(s.charAt(i)=='_')
			{
				n++; 
			}			
		}
		String str=""; //per aggiungere caratteri a str inizialmente deve essere vuota ""
		String [] arr = new String [n]; //n+1 se non finisce con _
		
		int q=0;
		for(int k=0; k<s.length();k++) 
		{
			if(s.charAt(k) == '_') 
			{
				arr[q]=str;	//mettiamo qui queste istruzioni perchè vogliamo che quando passa
				str="";	//alla parola successiva inserisca la stringa nell'array e aumenti q
				q++;	//passa alla successiva posizione dell'array
			} 
			else 
			{
				str = str + s.charAt(k);//se non è un _ aggiungi il carattere a str
			}						
		}
		
		//arr[q]=str; //questa istruzione serve nel caso generale per dire aggiungi un ultima stringa vuota
						//perchè senza carattere finale _ non c'è successiva "divisione"
						//e rimaniamo con str "piena" ma non assegnata e la assegnamo qui 
						//q rimane l'ultima posizione definita dal q++ sopra
		
		for(int w=0; w<arr.length;w++) //questo for serve solo per la stampa
		{
			System.out.print(arr[w] + " ");
		}		
	}
	
	private static void Occorrenza(String s, char c) {
		int k=0;
		for(int i=s.length()-1;i>=0;i--) 
		{
			if(s.charAt(i)==c) 
			{
				System.out.println("Il carattere si trova nella posizione " + i);
				k++;
			}
		}
		System.out.println("Il numero di occorrenze e' " + k);	
	}
	
	private static int lastCharacter(String string, char c) {
        for (int i = string.length() - 1; i > 0; i--) {
            if (string.charAt(i) == c) {
                return i;
            }
        }
        return -1;
    }
	
	private static void VarargsDemo3() {
		String messaggio = "Il risultato è uguale a ";
		
		SommaInteri3(messaggio,1);
		SommaInteri3(messaggio,1,2);
		SommaInteri3(messaggio,1,2,3);
		SommaInteri3(messaggio,1,2,3,4);
		
	}
	private static String SommaInteri3(String msg, int ... numeri) {
		int somma = 0;
		for(int n:numeri) {
			somma = somma + n;
		}
		String ris= msg + " " + Integer.toString(somma);
		return ris;
	}
	
	private static int SommaInteri2(int ... numeri) {
		int somma = 0;
		for(int n:numeri) {
			somma = somma + n;
		}
		return somma;		
	}
	
	private static void VarargsDemo2() {
		int somma;
		
		somma=SommaInteri2(1);
		somma=SommaInteri2(1,2);
		somma=SommaInteri2(1, 2, 3);
		somma=SommaInteri2(1, 2, 3, 4);
		System.out.println("Somma = " + SommaInteri2(1));
	}

	private static int SommaInteri(int [] numeri) {
		int somma = 0;
		for(int n:numeri) {
			somma = somma + n;
		}
		return somma;		
	}

	private static void VarargsDemo1() {
		//il metodo deve poter sommare un numero indefinito di interi
		
		int [] numeri= {1, 2, 3, 4, 5 ,6, 7, 8, 9, 10, 11};
		System.out.println("Somma = " + SommaInteri(numeri));
		
		
	}

	private static void Es_Stringa() {
		/*
		 * data una stringa di partenza ottenere una stringa di arrivo con tutti 
		 * i nuovi caratteri che siano i successivi dei precedenti nella stessa posizione
		 * usando charAt() e equals() 
		 */
		
		String str = "ci ao";
		String str2 = "";
		for (int i=0;i<str.length();i++) 
		{
			int c,d;
			c=str.charAt(i);
			if(c==' ') {	//gli apici anzichè le doppie spuntee!!!
				str2=str2 + " ";
				continue;	//serve continue per dire che se ci sono spazi finisce qui
			}
			d=c+1;
			char x=(char) d;			
			str2=str2 + x;
			
		}
		System.out.println(str2);
		
	}

	private static void StringDemo2() {
		String str1 = "pippo";
		String str2 = "pluto";
		String str3 = str1;
		String str4 = "pippo";	//non c'è bisogno di creare un altro "pippo" se esiste già
								//quindi viene assegnato lo stesso reference sia ad str1 che str4
				
		
		System.out.println(str1==str2);	//false
		System.out.println(str1.equals(str2)); //false
		System.out.println();
		System.out.println(str1==str4);	//true
		System.out.println(str1.equals(str4));	//true
		System.out.println();		
	}

	private static void StringDemo1() {
		String str = "pippo";	
		str = "pluto";
		System.out.println(str);
		return;
	}

}
