package esercizi03;

// voglio che il PRODUTTORE sia un SINGLETONE: in una applicazione può esistere UNA SOLA ISTANZA DI QUESTA CLASSE
public class Producer extends Thread {
	static Producer instance = null;
	
	QueueInt queue;
	long timeout;
	
	boolean stop = false;
	
	private Producer(QueueInt queue, long timeout) {
		super("Producer");
		this.queue = queue;
		this.timeout = timeout;
	}

	// nessuno può chiamare il costruttore per creare altre istanze della stessa classe
	private Producer() {}
	
	// per avere una istanza di PRODUCER devo utilizzare questo metodo statico, che chiama il costruttore,
	// per creare una UNICA ISTANZA
	
	public static Producer getInstance() {
		if (instance == null) {
			instance = new Producer();
		}
		return instance;
	}
	
	public static Producer getInstance(QueueInt queue, long timeout) {
		if (instance == null) {
			instance = new Producer(queue, timeout);
		}
		return instance;
	}
	
	public void Stop() {
		stop = true;
	}
	
	public static Producer Start(QueueInt queue, long timeout) {
		var prod = Producer.getInstance(queue, timeout);
		
		prod.start();
		
		return prod;
	}
	
	@Override
	public void run() {
		// il produttore sia un CICLO che continuamente, ogni tot millisecondi (timeout), inserisca un intero
		// all'interno della coda

		int counter = 1;
		while (!stop) {
			System.out.println("Valore accodato: " + counter);
			
			queue.Enqueue(counter++);
			
			try {
				Thread.sleep(timeout);
			} catch (InterruptedException e) {
				System.out.println("Producer INTERROTTO!!");
			}
		}
	}
}
