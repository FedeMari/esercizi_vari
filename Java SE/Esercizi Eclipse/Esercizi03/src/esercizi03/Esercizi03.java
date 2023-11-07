package esercizi03;

import java.io.IOException;

enum Apple {
	golden(10), renetta(20), cotogna(30), annurca(50), stark(40);
	
	int prezzo;

	private Apple(int prezzo) {
		this.prezzo = prezzo;
	}

	public int getPrezzo() { return prezzo; }
}

public class Esercizi03 {

	public static void main(String[] args) {
		// CurrentThreadDemo();
		// ThreadDemo();
		// ExtendThread();
		// MultiThreadDemo();
		//MultiThreadDemo2();
		//MultiThreadDemo3();
		//TestProducerConsumer();
		//EnumDemo1();
		//EnumDemo2();
		//EnumDemo3();
	}
	private static void EnumDemo3() {
		var apples = Apple.values();
		
		for (Apple apple : apples) {
			System.out.println("il prezzo della mela " + apple + " è pari a " + apple.getPrezzo() + " euro");
		}
	}

	private static void EnumDemo2() {
		var apples = Apple.values();
		
		for (Apple apple : apples) {
			System.out.println(apple);
		}
		
		String nome_mela = "annurca";
		try {
			var mela = Apple.valueOf(nome_mela);
			System.out.println("la mela scelta è " + mela);
		} catch (Exception e) {
			System.out.println("Il nome della mela (" + nome_mela + ") è sbagliato!");
		}
		
		return;
	}

	private static void EnumDemo1() {
		Apple ap = Apple.annurca;

		switch (ap) {
		case annurca:
			//System.out.println("hai scelto la mela " + ap.name() + " e costa 1 euro");
			System.out.println("hai scelto la mela " + ap + " e costa 1 euro");
			break;
		case cotogna:
			System.out.println("hai scelto la mela " + ap.name() + " e costa 2 euro");
			break;
		case golden:
			System.out.println("hai scelto la mela " + ap.name() + " e costa 3 euro");
			break;
		case renetta:
			System.out.println("hai scelto la mela " + ap.name() + " e costa 4 euro");
			break;
		case stark:
			System.out.println("hai scelto la mela " + ap.name() + " e costa 5 euro");
			break;
		default:
			System.out.println("mela sconosciuta ...");
			break;
		}
		return;
	}

	private static void TestProducerConsumer() {
		// creiamo la BLOCKING QUEUE (RISORSA CONDIVISA)
		int queue_len = 5;
		long prod_timeout = 1000;
		long cons_timeout = 1500;
		
		QueueInt queue = new QueueInt(queue_len);
		
//		Producer prod = Producer.getInstance(queue, prod_timeout);
//		Consumer cons = Consumer.getInstance(queue, cons_timeout);
//
//		prod.start();
//		cons.start();
		
		// per creare e avviare i due thread, utilizziamo i loro metodi factory
		
		Producer prod = Producer.Start(queue, prod_timeout);
		Consumer cons = Consumer.Start(queue, cons_timeout);
		
		// per creare i due thread e contemp. avviarli, utilizziamo il metodo factory "startProducer/Consumer()"
		
		System.out.println("premere un tasto per interrompere i due thread");
	
		try {
			System.in.read();
			
			prod.Stop();
			cons.Stop();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void MultiThreadDemo3() {
		System.out.println("Current thread: " + Thread.currentThread());

		long timeout = 1000;
		var nt1 = new NewThread2("One", timeout / 2);
		var nt2 = new NewThread2("Two", timeout / 3);
		var nt3 = new NewThread2("Three", timeout / 4);

		long start = System.currentTimeMillis(), stop;

		// Start the threads.
		nt1.start();
		nt2.start();
		nt3.start();

		// faccio in modo che il MAIN THREAD sospenda se stesso, per rimanere IN ATTESA
		// che siano
		// terminati TUTTI i suoi THREAD SECONDARI

		// quando il thread genitore chiama il metodo "join()" su un thread figlio, si
		// blocca e
		// aspetta che il thread figlio termini ===> "join()" è un METODO BLOCCANTE ===>
		// l'esecuzione NON procede, finché il meotodo bloccante NON termina

		while (nt1.isAlive() || nt2.isAlive() || nt3.isAlive()) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		stop = System.currentTimeMillis();

		System.out.println("Main thread exiting: delta = " + (stop - start) / 1000.0);
	}

	private static void MultiThreadDemo2() {
		System.out.println("Current thread: " + Thread.currentThread());

		long timeout = 1000;
		var nt1 = new NewThread2("One", timeout / 2);
		var nt2 = new NewThread2("Two", timeout / 3);
		var nt3 = new NewThread2("Three", timeout / 4);

		long start = System.currentTimeMillis(), stop;

		// Start the threads.
		nt1.start();
		nt2.start();
		nt3.start();

		// faccio in modo che il MAIN THREAD sospenda se stesso, per rimanere IN ATTESA
		// che siano terminati TUTTI i suoi THREAD SECONDARI

		// quando il thread genitore chiama il metodo "join()" su un thread figlio, si
		// blocca e aspetta che il thread figlio termini ===> "join()" è un METODO BLOCCANTE 
		// ===> l'esecuzione NON procede, finché il meotodo bloccante NON termina

		try {
			nt1.join();
			nt2.join();
			nt3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		stop = System.currentTimeMillis();

		System.out.println("Main thread exiting: delta = " + (stop - start) / 1000.0);
	}

	private static void MultiThreadDemo() {
		System.out.println("Current thread: " + Thread.currentThread());

		long timeout = 1000;
		var nt1 = new NewThread2("One", timeout);
		var nt2 = new NewThread2("Two", timeout / 2);
		var nt3 = new NewThread2("Three", timeout / 3);

		// Start the threads.
		nt1.start();
		nt2.start();
		nt3.start();

		try {
			// wait for other threads to end
			Thread.sleep(11000);
		} catch (InterruptedException e) {
			System.out.println("Main thread Interrupted");
		}
		System.out.println("Main thread exiting.");
	}

	private static void ExtendThread() {
		System.out.println("Current thread: " + Thread.currentThread());

		long timeout = 1000;

		var nt = new NewThread2();

		nt.start(); // start the thread

		try {
			for (int i = 10; i > 0; i--) {
				System.out.println("Main Thread: " + i);
				Thread.sleep(timeout);
			}
		} catch (InterruptedException e) {
			System.out.println("Main thread interrupted.");
		}
		System.out.println("Main thread exiting.");
	}

	private static void ThreadDemo() {
		String mainThreadName = Thread.currentThread().getName();

		System.out.println("Current thread: " + mainThreadName);

		// 1) creazione del thread

		NewThread nt = new NewThread();

		// 2) START del thread

		nt.t.start();

		try {
			for (int i = 10; i > 0; i--) {
				System.out.println("Main Thread: " + i);
				Thread.sleep(500);
			}
		} catch (InterruptedException e) {
			System.out.println("Main thread interrupted.");
		}
		System.out.println("Main thread exiting.");
	}

	private static void CurrentThreadDemo() {
		// otteniamo il reference al THREAD CORRENTE: thread il cui flusso di esecuzione
		// viene attualmente eseguito dalla CPU

		Thread t = Thread.currentThread();

		// siccome la classe "Thread" effettua l'override del metodo "toString()",
		// allora ha senso stampare un oggetto "Thread"
		// viene stampato: nome thread, priorità, nome gruppo

		System.out.println("Current thread: " + t);

		// change the name of the thread
		t.setName("Thread Principale");

		System.out.println("After name change: " + t);
		long start, stop;
		double delta = 0;

		try {
			for (int n = 10; n > 0; n--) {
				System.out.println("t = " + delta + ": " + n);
				start = System.currentTimeMillis();
				Thread.sleep(500);
				stop = System.currentTimeMillis();
				delta = (stop - start) / 1000.0;
				stop = start;
			}
		} catch (InterruptedException e) {
			System.out.println("Main thread interrupted");
		}

	}
}
