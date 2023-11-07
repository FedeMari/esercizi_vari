package classi;

import java.io.*;
import java.net.*;

public class SimpleChatServer {
	private Socket socket = null;
	
	// i due seguenti STREAM servono per leggere/scrivere dei DATI di TIPO PRIMITIVO JAVA dal/nello
	// STREAM associato al SERVER SOCKET/SOCKET
	
	private DataInputStream streamIn = null;
	private DataOutputStream streamOut = null;
	
	public SimpleChatServer(int portNum) {
		// 1. per prima cosa, creiamo un oggetto SERVER SOCKET, che verrà utilizzato dal SERVER
		// per rimanere in attesa delle richieste di connessione che gli può inviare il CLIENT

		try (var server = new ServerSocket(portNum)) {
			// se non "casco" nell'eccezione, allora il SO ha dato l'OK ad associare il SERVER
			// alla PORTA scelta ===> da questo momento il SERVER è IN ASCOLTO

			System.out.println("Server started: " + server);
			System.out.println("Waiting for a client ...");

			// il metodo "accept()" è BLOCCANTE: il THREAD (uno solo ed è il MAIN) viene
			// SOSPESO, fino a che non ARRIVA una RICHIESTA di CONNESSIONE da un CLIENT
			
			socket = server.accept();
			
			// adesso, c'è un CLIENT CONNESSO ed è stato creato un oggetto "Socket", che verrà
			// utilizzato dal SERVER per rispondere al CLIENT

			System.out.println("Client accepted: " + socket);
			
			// apriamo i due stream, forniti dal Socket (che usa i server per rispondere)
			open();

			// fino a che l'INPUT STREAM non viene chiuso, il SERVER continua a rimanere in attesa
			// di eventuali messaggi che gli invia il CLIENT
			
			boolean done = false;
			
			while (!done) {
				// leggiamo un riga di caratteri dall'input stream (può contenere dei DATI di TIPO PRIMITIVO)
				// anche questo è bloccante
				String line = streamIn.readUTF(); //metodo bloccante, non esco da qui finchè non arriva una risposta
				
				// per CHIUDERE LA CONNESSIONE, il CLIENT deve inviare il MESSAGGIO ".bye"
				done = line.trim().equalsIgnoreCase(".bye"); //diventa true e quindi !done diventa false e si esce dal ciclo while
				
				// stampiamo sulla console il messaggio inviato dal client
				System.out.println(line);
				
				// inviamo al client la risposta = al messaggio di richieta (ECHO)
				streamOut.writeUTF(line);
			}
			close();
		}
		catch (IOException e) {
			// il costruttore del "ServerSocket" chiede al SO se si può associare (bind)
			// alla
			// porta che abbiamo scelto.
			// Potrebbe però succedere che quella PORTA sia GIA' OCCUPATA da un altro SERVER
			// ===> ECCEZIONE
			System.out.println("unable to bind to port " + portNum + " : " + e.getMessage());
		}
	}

	private void close() throws IOException {
		// anche in questo caso, la chiusura del socket chiude anche i relativi stream
//		if (streamOut != null)
//			streamOut.close();
//		if (streamIn != null)
//			streamIn.close();
		if (socket != null)
			socket.close();
	}

	private void open() throws IOException {
		// STREAM utilizzato dal SERVER per leggere il MESSAGGIO che gli ha inviato il CLIENT
		streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
				
		// STREAM utilizzato per restituire un MESSAGGIO di RISPOSTA al CLIENT
		streamOut = new DataOutputStream(socket.getOutputStream());
		

		/* Si potrebbe anche usare la dicitura:
		 * 
		 * ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
		 * ObjectOutputStream ois = new ObjectOutputStream(socket.getOutputStream());
		 */		
	}

}
