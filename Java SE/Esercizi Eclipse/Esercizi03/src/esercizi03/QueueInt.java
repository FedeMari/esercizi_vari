package esercizi03;

// coda di elenmenti di tipo intero
public class QueueInt {
	int N;										// lunghezza del BUFFER CIRCOLARE con cui implementiamo al CODA
	int[] circ_buff = null;		// BUFFER CIRCOLARE con cui implementiamo al CODA
	int rear, front;  				// indici che puntano, rispettivamente, all'elemento del BUFFER CIRCOLARE in cui posso
														// inserire un nuovo valore/ dacui posso prelevare il successivo valore
	int num_elems; 						// numero di elementi goà presenti nella coda
														// 		se "num_elems" == N ===> la CODA E' PIENA
														// 		se "num_elems" == 0 ===> la CODA E' VUOTA
	public QueueInt(int N) {
		this.N = N;
		
		// allochiamo la memoria che deve contenere il BUFFER CIRCOLARE
		circ_buff = new int[N];
		
		rear = front = num_elems = 0;
	}
	
	// utilizzo questo metodo per "accodare" un elemento, ossia per inserire un elemento in coda da REAR
	
	public void Enqueue(int elem) {
		// ATT: se la CODA è PIENA ===> metto il PRODUTTORE nello stato WAIT. Rimane in attesa che il CONSUMATORE
		//			gli NOTIFICHI che la CODA contiene ALMENO UNA POSIZIONE VUOTA
		//			quando il CONSUMATORE preleva un elemento dalla coda, AVVISA (NOTIFY) il PRODUTTORE mandandogli
		//			il segnale
		
		// per mettere IN ATTESA un THREAD, questo deve chiamare il suo metodo "wait()"
		
		synchronized (this) {
			//if (num_elems == N) {
			while (num_elems == N) {
				System.out.println("LA CODA E' PIENA: " + Thread.currentThread().getName());
				try {
					// il PRODUTTORE ASPETTA che il CONSUMATORE liberi almeno una posizione nel BUFFER CIRCOLARE
					wait();
				} catch (InterruptedException e) {
					System.out.println(Thread.currentThread().getName() + " è stato interrotto prematuramente");
				}
			}
			// perché WHILE e non IF? Perchè, il se thread viene interrotto prematuramente (prima che il CONSUMATORE
			// esegua "notiyAll()", allora cadiamo nel CATCH e l'IF verrebbe PREMATURAMENTE CHIUSO, anche se la
			// CODA è ANCORA PIENA.
			// Col WHILE, anche se il thread viene interrotto prematuramente, il codica torna di nuovo a chiedersi
			// se la CODA è ANCORA PIENA e, in tal caso, chiama di nuovo "wait()"
		}
		synchronized (this) {
			add(elem);
			notifyAll();		// il PRODUTTORE NOTIFICA al CONSUMATORE il fatto che adesso ha a disposizione
											// almeno un elemento da prelevare
		}
	}
	
	private void add(int elem) {
		circ_buff[rear] = elem;				// aggiungo "elem" al BUFFER CIRCOLARE
		rear = (rear + 1) % N;				// INCREMENTO CIRCOLARMENTE "rear"
		num_elems++;									// incremento il numero di elementi già accodati
	}

	// utilizzo questo metodo per "scodare" un elemento, ossia per prelevare (lo leggo e lo elimino dalla coda)
	// un elemento dalla FRONT della coda
	
	public int Dequeue() {
		// ATT: se la CODA è VUOTA ===> metto il CONSUMATORE nello stato WAIT. Rimane in attesa che il PRODUTTORE
		//			gli NOTIFICHI che la CODA contiene ALMENO UN ELEMENTO
		//			quando il PRODUTTORE accoda un elemento, AVVISA (NOTIFY) il CONSUMATORE mandandogli il segnale
		
		synchronized (this) {
			//if (num_elems == 0) {
			while (num_elems == 0) {
				System.out.println("LA CODA E' VUOTA: " + Thread.currentThread().getName());
				try {
					// il CONSUMATORE ASPETTA che il PRODUTTORE inserisca almeno un elemento nel BUFFER CIRCOLARE
					wait();
				} catch (InterruptedException e) {
					System.out.println(Thread.currentThread().getName() + " è stato interrotto prematuramente");
				}
			}
		}
		int elem;
		synchronized (this) {
			elem = remove();
			notifyAll();		// il CONSUMATORE NOTIFICA al PRODUTTORE il fatto che adesso ha a disposizione
											// almeno una posizione libera da riempire
		}
		return elem;
	}

	private int remove() {
		int elem = circ_buff[front];			// prelevo l'elemento della CODA puntato da "front"
		front = (front + 1) % N;					// INCREMENTO CIRCOLARMENTE "front"
		num_elems--;											// decremento il numero di elementi già accodati
		return elem;
	}
	
}
