package main;

import java.io.*;

import classi.*;

public class App {
	

	public static void main(String[] args) {
		//SimpleServerExample(args);
		ThreadServerExample(args);
	}
	
	// semplice esempio di SERVER TCP/IP il quale accetta UN SOLO CLIENT
	// Tuttavia, questa volta il codice del server verrà eseguito in un thread diverso dal thread MAIN. 
	// In questo modo il thread MAIN non viene bloccato, quando il thread del server si trova nello stato 
	// BLOCKED in attesa di qualsiasi cosa provenga dal client
	// sulla riga di comando, va specificata la PORTA su cui deve rispondere il SERVER
	
	private static void ThreadServerExample(String[] args) {
//		if (args.length != 1) {
//			System.out.println("Usage: java ChatServer port");
//			return;
//		}
		try {
			// questa volta, il SERVER gira in un THREAD tutto suo, diverso dal thread MAIN
			// al costruttore del thread passiamo il nome del thread e in più la PORTA DI ASCOLTO

			//ThreadChatServer server = new ThreadChatServer(Integer.parseInt(args[0]));
			ThreadChatServer server = new ThreadChatServer(3000);
			System.out.println("Starting Server Thread: " + server);
			server.start();
			server.join();
		} catch (NumberFormatException e) {
			System.out.println("numero porta errato");
		} catch (IOException e) {
			System.out.println("network error: " + e.getMessage());
		} catch (InterruptedException e) {
			System.out.println("Il MAIN thread   morto prematuramente");
		}
	}			
					

	// semplice esempio di SERVER TCP/IP accetta UN SOLO CLIENT è MONO THREAD (solo il thread MAIN)
	// sulla riga di comando, va specificata la PORTA su cui deve rispondere il SERVER
	
	private static void SimpleServerExample(String[] args) {
		//il Server richiede solo una Porta in Ingresso per questo il controllo su un argomento solo
		if (args.length != 1) {
			System.out.println("Usage: java ChatServer port");
			return;
		}
		// se sulla riga di comando è presente una PORTA, questa viene trasformata in una STRINGA
		// e viene passata come argomento al costruttore della CLASSE del SERVER
		String port_num = args[0];
		
		//se tutto è andato ok prendi il parametro inserito, passalo come porta e crea un Server
		var server = new SimpleChatServer(Integer.parseInt(port_num));
	}
}

// NOTA: per conoscere le porte già utilizzate, usare il comando "netstat"

