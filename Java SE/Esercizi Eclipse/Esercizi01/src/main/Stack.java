package main;

public class Stack {
	int i=0;	//variabile per inizializzare array
	int arr[]=new int[i];	//variabili membro
	int tos;	//elemento al vertice dello stack (top of stack)
	int n_elem=0;	//numero di elementi già presenti nello stack
	int q;	//variabile quantità per contare elementi
	int k=1;
	
	//costruttore della classe per creare e dare dimensione all'array
	public Stack(int n) {
		arr=new int[n];
		tos=-1;
		q=n;
	}
	
	//metodo push per l'aggiunta di elementi allo stack
	public void push(int n) {
		if(n_elem==q) //se il numero di elementi raggiunge la quantià iniziale fermati
		{
			System.out.println("Massima capienza dello stack, devi rimuovere un elemento");
			return;
		}
		else
		{
			tos++;	//passo da posizione precedente a posizione successiva
			arr[tos]=n; 
			n_elem++;
		}
	}
	
	//metodo pop preleva (legge e restituisce) l'elemento in cima allo stack
	public void pop() {
		if(n_elem==0) 
		{
			System.out.println("Non ci sono valori nell'array");
			return;
		}
		if(k<=q)
		{
				System.out.println(arr[arr.length-k]);
				k++;
					
		}
		else
		{
			System.out.println("I valori nell'array sono terminati");
		}		
		
		//ricorda di sostituire int a void nel metodo se usi questa
		/*if(tos<0) 
		{
			return 0;
		}
		else
		{
			return arr[tos--];
		}*/
	}
	
	//restituisce "true" se lo stack è pieno
	public void isFull() {
		if(n_elem==q) 
		{
			System.out.println("L'array risulta pieno");
			return;
		}
		else
		{
			System.out.println("L'array non e' ancora pieno");
			return;
		}
	}
	
	//restituisce "true" se lo stack è vuoto
	public void isEmpy() {
		if(n_elem==0) 
		{
			System.out.println("L'array risulta vuoto");
			return;
		}
		else
		{
			System.out.println("L'array non e' più vuoto");
			return;
		}
	}
}
