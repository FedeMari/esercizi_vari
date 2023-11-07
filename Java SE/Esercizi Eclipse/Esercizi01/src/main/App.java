package main;

import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// System.out.println("Hello World!!!");
		// Example();
		// Ifsample();
		// ForTest();
		// Divisione();
		// DivisioneConFor();
		// Media(99);
		// Luce(2);
		// AreaCerchio(-5);
		// Alfabeto();
		// MatriceTriangolare(4);
		// Mesi();
		// Operatori(args);
		// Operatori2(args);
		//doWhileDemo();
		//doWhileDemo2();
		//OrdineInverso(45);
		//OrdineInverso2(2);
		//RicercaBinaria();
		//RicercaBinaria2();		
	}

	private static void RicercaBinaria2() {
		int arr[]= {3,5,7,9,41,86,35,75,34};
		int y = 78;
		int k = 0;
		boolean test = false;
		for (int x:arr) //finiti gli elementi il ciclo termina
		{ 
				if(x==y) 
				{
					System.out.println("Hai trovato il numero y: " + x + ", la sua posizione e' " + k);
					test=true;
					break;
				}
				k++;
		}
		if(test==false) //serve una variabile di appoggio altrimenti il foreach valuta ogni elemento
		{
			int f=-1;
			System.out.println("Valore non presente nel sistema.\n" + f);
		}
		
		/*
		for (int x:arr) //finiti gli elementi il ciclo termina
		{ 
				if(x==y) 
				{
					test=true;
					break;
				}
				k++;
		}
		if(test==true) //serve una variabile di appoggio altrimenti il foreach valuta ogni elemento
		{
			System.out.println("Hai trovato il numero y: " + x + ", la sua posizione e' " + k);
		}
		else
		{
			int f=-1;
			System.out.println("Valore non presente nel sistema.\n" + f);
		}
		 */
		
		/*
		int indice=-1;
		for (int x:arr) //finiti gli elementi il ciclo termina
		{ 
				if(x==y) 
				{
					indice=k;
					break;
				}
				k++;
		}
		System.out.println(indice);
		 */
	}

	private static void RicercaBinaria() {
		int arr[]= {3,5,7,9,41,86,35,75,34};
		int x = 3;
		int k = 0;
		//partiamo dal mezzo 41;
		for (int i = 4; i < arr.length && i>=0; ) {
				if(arr[i]==x) {
					System.out.println("Hai trovato il numero x: " + x + ", la sua posizione e' " + i);
					break;
				}
				else if(arr[i]>x) {
					i--;
				}
				else if(arr[i]<x) {
					i++;
				}
				else
				{
					System.out.println("Valore non presente nel sistema");
				}
				k++;
		}
		System.out.println("Valore trovato in " + k + " tentativi.");
		
	}

	private static void OrdineInverso2(int n) {
		int arr [] = new int[n];
		int a,b=0;
		int f=0;
		do{
			Scanner scanner = new Scanner(System.in);
			arr[f]= scanner.nextInt();			
			f++;
		}while(f<n);
		
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		for (int j = arr.length-1; j>=0; j--) {
			System.out.print(arr[j] + " ");
		}		
	}

	private static void OrdineInverso(int n) {
		int arr [] = new int[n];
		int a,b=0;
		int f=0;
		
		for (int j = 0; j < arr.length; j++) {
			arr[j]=b;
			System.out.print(b + " ");
			b++;			
		}
		System.out.println();
		for (int i = arr.length-1; i>=0 ;i--) {
			a=arr[i];
			System.out.print(a + " ");
		}
		
	}

	private static void doWhileDemo2() {
		String nomi [] = new String[4];
		nomi[0]="Paolo";
		nomi[1]="Fred";
		nomi[2]="Fine";
		nomi[3]="Giulia";
		int i=0;
		fine:do {
			if(nomi[i].equals("Fine"))
			{
				break fine;
			}
			else
			{
				System.out.println(nomi[i]);
			}
			i++;
		}while(true);
		
	}

	private static void doWhileDemo() {
		String nomi [] = new String[4];
		nomi[0]="Paolo";
		nomi[1]="Fred";
		nomi[2]="Fine";
		nomi[3]="Giulia";
		int i=0;
		boolean finished=false;
		do {
			switch(nomi[i]) {
			case "Fine":
				finished = true;
				break;
			default:
				System.out.println(nomi[i]);
				i++;
			}
		}while(!finished);
		
		
	}

	private static void Operatori2(String[] args) {
		String a = args[0];
		String b = args[1];
		String c = args[2];
		int anum = Integer.parseInt(a);
		int bnum = Integer.parseInt(b);
		int tot;
		switch (c) {
		case "+":
			tot = anum + bnum;
			System.out.println("L'operazione e' la somma ed il risultato e' " + tot);
			break;
		case "-":
			tot = anum - bnum;
			System.out.println("L'operazione e' la sottrazione ed il risultato e' " + tot);
			break;
		case "*":
			tot = anum * bnum;
			System.out.println("L'operazione e' la moltiplicazione ed il risultato e' " + tot);
			break;
		case "/":
			tot = anum / bnum;
			System.out.println("L'operazione e' la divisione ed il risultato e' " + tot);
			break;
		default:
			System.out.println("Nessuna operazione selezionata");
		}

	}

	private static void Operatori(String[] args) {
		String a = args[0];
		String b = args[1];
		String c = args[2];
		int anum = Integer.parseInt(a);
		int bnum = Integer.parseInt(b);
		int o = 0;
		int tot;
		if (c.equals("+")) {
			o = 1;
		} else if (c.equals("-")) {
			o = 2;
		} else if (c.equals("*")) {
			o = 3;
		} else if (c.equals("/")) {
			o = 4;
		}
		switch (o) {
		case 1:
			tot = anum + bnum;
			System.out.println("L'operazione e' la somma ed il risultato e' " + tot);
			break;
		case 2:
			tot = anum - bnum;
			System.out.println("L'operazione e' la sottrazione ed il risultato e' " + tot);
			break;
		case 3:
			tot = anum * bnum;
			System.out.println("L'operazione e' la moltiplicazione ed il risultato e' " + tot);
			break;
		case 4:
			tot = anum / bnum;
			System.out.println("L'operazione e' la divisione ed il risultato e' " + tot);
			break;
		default:
			System.out.println("Nessuna operazione selezionata");
		}
	}

	private static void Mesi() {
		int month_days[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		for (int i = 0; i < month_days.length; i++) {
			if ((i % 2 == 0)) {
				System.out.println(month_days[i]);
			}

		}
	}

	private static void MatriceTriangolare(int n) {
		int tr[][] = new int[n][];
		int i, j;
		int k = 0;
		for (i = 0; i < n; i++) {
			tr[i] = new int[i + 1]; // non mettere i++ che sarebbe i=i+1 - inoltre scrivo così perchè devo popolare
									// la matrice tr[0],tr[1] -- aumenta di 1 l'indice
			for (j = 0; j < i + 1; j++) { // j<i+1 stretto perché voglio una scala, tante colonne quante righe!!
				tr[i][j] = k;
				k++;
			}
		}

		for (i = 0; i < n; i++) {
			System.out.println(); // necessario per andare a capo ad ogni nuova iterazione
			for (j = 0; j < i + 1; j++) {
				System.out.print(tr[i][j] + "\t");
			}
		}
	}

	private static void Alfabeto() {
		char ch1; // dichiarazione variabile carattere
		int i; // dichiarazione contatore
		for (i = 65; i < 91; i++) {
			ch1 = (char) i; // casting per tradurre codice numerico in carattere
			System.out.println(ch1);
		}
	}

	private static void DivisioneConFor() {
		int a, b;
		a = 2;
		b = 3;
		int c = 0; // quoziente inizializzato a 0
		// c dichiarato fuori il for altrimenti sarebbe visibile solo internamente al
		// for
		if (a > 0) {
			for (; a >= b; c++) // finchè a>=b eseguo il codice
			{ // c++ è la quantita di volte che b sta in a che aumenta
				a -= b; // a=a-b,
			}
			System.out.println("Il resto e' " + a);

			System.out.println("Il quoziente e' " + c);
		} else {
			System.out.println("Errore di inserimento");
		}

	}

	private static void AreaCerchio(double r) {
		double PI = 3.14;
		double Ac;
		if (r > 0) {
			Ac = PI * r * r;
			System.out.println(Ac);
		} else {
			System.out.println("Errore! Raggio deve essere maggiore di 0");
		}
	}

	private static void Luce(int g) {
		long v = 300000; // Km/s
		long secondi = g * 24 * 60 * 60;
		long distanza;
		distanza = v * secondi;
		System.out.println(distanza);

	}

	private static void Media(int N) {
		// TODO Auto-generated method stub
		int i = 0, T = 0, E = 25;
		int M;
		if (E > 0 & E < 120) {
			do {
				T = T + E;
				i++;
			} while (i < N);
			M = T / N;
			System.out.println(M);
			System.out.println(N);
		} else {
			System.out.println("Eta non accettabile!");
		}

	}

	private static void Divisione() {
		// TODO Auto-generated method stub
		int x = 8, // dividendo
				y = 4, // divisore
				Q = 0; // quoziente
		int R = x; // resto
		if (y == 0) {
			System.out.println("Impossibile dividere per 0!");
		} else if (x >= 0) {
			while (R >= y) {
				R = R - y;
				Q = Q + 1;
			}
			System.out.println("Il resto e': " + R);
			System.out.println("Il quoziente e': " + Q);
		} else {
			System.out.println("Non si puo' dividere un numero negativo!!");
		}
	}

	private static void ForTest() {
		// TODO Auto-generated method stub
		int c;
		// int a;
		for (c = 10; c < 20; c = c + 1) {
			// a=c-10;
			/*
			 * se scrivessi c=c-10 andrei ogni volta a diminuire il contatore rendendo un
			 * ciclo infinito
			 */
			System.out.println("This is c: " + (c - 10));
		}

	}

	private static void Ifsample() {
		// TODO Auto-generated method stub
		int x, y;
		x = 10;
		y = 20;
		if (x < y) {
			System.out.println("x is less than y");
		}
		x = x * 2;
		if (x == y) {
			System.out.println("x now equal to y");
		}
		x = x * 2;
		if (x > y) {
			System.out.println("x now greater than y");
		}
		// questa istruzione non visualizza nulla
		if (x == y) {
			System.out.println("you won't see this");
		}
	}

	private static void Example() {
		int num; // dichiara una variabile di nome "num"
		num = 100; // assegna a "num" il valore 100
		System.out.println("Il valore di num è: " + num);
		num = num * 2;
		System.out.print("Il valore di num num * 2 è: ");
		System.out.println(num);
		if (num < 100) {
			System.out.println("num è minore di 100");
		}
		System.out.println("ma num era minore di 100?");
	}

}
