
import java.util.*;

public class MyArrayList <T> extends AbstractList<T> implements List<T>{
	
	private final static int capacitaDefault = 16;
	private Object [] elementi;
	private int dimensione = 0;
	
	public MyArrayList() {
		elementi = new Object[capacitaDefault];	//imposto array di tipo Object a capacità di Default
	}
	
	public MyArrayList(int capacitaIniziale) {
		if(capacitaIniziale>0) {
			elementi = new Object [capacitaIniziale];
		}
		else if (capacitaIniziale==0){
			elementi = new Object[capacitaDefault];
		}
		else {
			throw new IllegalArgumentException("La capacita' iniziale non deve avere valore negativo");
		}
	}
	
	public MyArrayList (Collection<? extends T> c) {
		Object [] arr = c.toArray();
		if((dimensione = arr.length)!=0) {
			//c.getClass() == ArrayList.class
			if(c instanceof ArrayList) {
				elementi = arr;
			}
			else {
				//controlla se va bene arr.getClass() al posto di Object[].class
				elementi = Arrays.copyOf(arr, arr.length, arr.getClass()); //crea un array copia di tipo specificato
			}
		}
		else {
			elementi = new Object[capacitaDefault];
		}
	}
	
	//privato perchè non serve a me ma all'ArrayList
	private void grow(int aggiunta) {
		//se elementi aggiunti superano i posti resi disponibili dall'inizializzazione
		if((dimensione + aggiunta) > elementi.length) {
			elementi = Arrays.copyOf(elementi, elementi.length*2);
		}
	}
	
	public boolean add(T nuovoElemento) {
		grow(1); //stiamo aggiungendo un elemento
		elementi[dimensione]=nuovoElemento;
		dimensione++;
		return true;
	}
	
	public boolean addAt(int posizione, T nuovoElemento) {
		if(posizione>=0 && posizione<elementi.length) {
			grow(1); //stiamo aggiungendo un elemento
			//arraycopy metodo che mi copia parte di un Array anzichè CopyOf perchè quest'ultimo non mi farebbe la traslazione o comunque dovremmo operarla noi
			System.arraycopy(elementi, posizione, elementi, posizione++, elementi.length-posizione);
			elementi[posizione]=nuovoElemento;
			dimensione++;
			return true;
		}
		else {
			return false;
		}		
	}
	
	//privato perché serve all'applicazione non a me
	private void incremento(Object [] arr) {		
		int j;
		for(j=0; j<arr.length;j++) {
			elementi[j]=arr[j];
			dimensione++;
		}
		for(int k=j; k<elementi.length;k++) {
			elementi[k]=null;
			dimensione++; //devo aumentare la dimensione? non credo
		}
	}
	
	public boolean addAllElement(Collection<? extends T> c) {
		Object [] arr = c.toArray();
		if(elementi.length == arr.length) {
			for(int i=0; i<arr.length;i++) {
				elementi[i]=arr[i];
				dimensione++;
			}
		}
		else if(elementi.length > arr.length){
			incremento(arr);
		}
		else if(elementi.length < arr.length) {
			grow(1);
			incremento(arr);
		}		
		return true;
		
		/*in via alternativa se non vogliamo fare "meccanicamente" uso System.arraycopy:
		 * 
		  	if(elementi.length < arr.length) {
		  		grow(1);
				System.arraycopy(arr,0,elementi,dimensione,arr.length);
				dimensione += arr.length;
			}
			else {
				System.arraycopy(arr,0,elementi,dimensione,arr.length);
				dimensione += arr.length;
			}
		 *
		 */
	}
	
	//privato perché serve solo all'ArrayList
	private void decrease(Object [] array, int posizione) {
		if((dimensione - 1) >= 0) {
			System.arraycopy(array, posizione++, array, posizione, array.length-posizione); //-(posizione++)???
			dimensione--; //controlla se va lasciato questo visto il dimensione-1 sopra
		}
	}
	
	public boolean removeElement(T vecchioElemento) {
		//array di appoggio
		Object [] arr = elementi;
		for(int i=0;i<arr.length;i++) {
			if(arr[i].equals(vecchioElemento)) {
				decrease(arr,i);
				return true;
			}
		}	
		return false;
	}
	
	public boolean removeAt(int indice) {
		//array di appoggio
		Object [] arr = elementi;
		if(indice<0 || indice > arr.length) {
			throw new IllegalArgumentException("Indice inserito non valido!");
		}
		
		decrease(arr,indice);
			
		return true;		
	}
	
	public boolean setElement(T elemento, int pos) {
		if(pos<0 || pos > elementi.length) {
			throw new IllegalArgumentException("Indice inserito non valido!");
		}
		elementi[pos]=elemento;
		return true;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T get(int index) {
		return (T) elementi[index];
	}
	
	public int capacity() {
		return elementi.length;
	}
		
	@Override
	public int size() {		
		return dimensione;
	}

}
