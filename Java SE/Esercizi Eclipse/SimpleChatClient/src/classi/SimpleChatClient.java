package classi;

import java.io.*;
import java.net.*;

public class SimpleChatClient {
	private Socket socket = null;
	private DataInputStream console = null;
	private DataOutputStream streamOut = null;
	private DataInputStream streamIn = null;
	
	public SimpleChatClient(String host, int portNum) {
		System.out.println("Establishing connection. Please wait ...");
		// proviamo a creare una CONNESSIONE col SERVER (host, portNum)
		try {
			//un Client richiede sia indirizzo IP in formato DNS che numero di Porta
			socket = new Socket(host, portNum);
			System.out.println("Connected: " + socket);
			start();

			String line = "";
			while (!line.trim().equalsIgnoreCase(".bye")) {
				try {
					// leggiamo un messaggio scritto dall'utente nella console
					line = console.readLine();
					
					// inviamo il messaggio al SERVER
					streamOut.writeUTF(line);
					
					// chiediamo allo STREAM di inviare immediatamente tutti i byte del
					// messaggio al server
					streamOut.flush();

					// il client rimane IN ATTESA che il server gli RISPONDA
					// metodo bloccante
					line = streamIn.readUTF();
					
					// è arrivato il messaggio di risposta e questo viene scritto 
					System.out.println("Server RESPONSE: " + line);
				} catch (IOException ioe) {
					System.out.println("Sending error: " + ioe.getMessage());
				}
			}
			System.out.println("BYE BYE from CLIENT!!!");
		} catch (UnknownHostException e) {
			System.out.println("Host unknown: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("I/O exception: " + e.getMessage());
		}
		stop();
	}

	private void stop() {
		// chiude tutti gli stream aperti nel costruttore. Chiude anche il socket
		try {
			if (console != null)
				console.close();
			// in realtà, la chiusura del socket chiude anche gli stream associati
//			if (streamOut != null)
//				streamOut.close();
//			if (streamIn != null)
//				streamIn.close();
			if (socket != null)
				socket.close();
			// client.close();
		} catch (IOException ioe) {
			System.out.println("Error closing ...");
		}
	}

	private void start() throws IOException {
		// 1. apriamo uno stream verso la tastiera del computer in quanto per avviare una chat è 
		//    necessario battere sulla tastiera
		// 2. apriamo uno stream di input verso il server
		// 3.    "     "     "    " output  "   "     "
		
		console = new DataInputStream(System.in);
		streamOut = new DataOutputStream(socket.getOutputStream());
		streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
	}

}
