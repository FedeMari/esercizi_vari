package esercizioServer;

import java.io.*;
import java.net.*;

import classi.NuovoServer;

public class App {

	public static void main(String[] args) {
		avvioChat(3000);

	}

	private static void avvioChat(int n) {
		int numPorta = n;
		//dato che nella classe del server gestiamo le eccezioni con clausola throws queste verranno 
		//lanciate qui e qui le gestiamo con try-cathc
		try {
			//richiamo il costruttore del mio server passandogli il numero di porta
			//però attenzione qui server è un Thread!
			NuovoServer server = new NuovoServer (numPorta);
			System.out.println("Starting Server Thread: " + server);
			//comunico al Thread corrente che potrà intervenire prossimamente un altro Thread
			server.start();
			//facciamo subentrare un nuovo Thread figlio - dobbiamo aggiungere eccezione Interrupted 
			server.join();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println("Il MAIN thread è morto prematuramente");
		}		
	}

}
