package classi;

import java.io.*;
import java.net.*;

public class ThreadChatServer extends Thread{
	private String serverName = "ChatServer";
	private int portNum;
	private ServerSocket server = null;
	private Socket socket = null;
	private DataInputStream streamIn;
	private DataOutputStream streamOut;
	
	public ThreadChatServer(int portNum) throws IOException {
		setName(serverName); //nome thread
		
		this.portNum = portNum; //numero porta
		
		System.out.println("Binding to port " + portNum + ", please wait  ...");
		server = new ServerSocket(portNum);
		System.out.println("Server started: " + server);
	}

	// ENTRY POINT del THREAD
	@Override
	public void run() {
		System.out.println("Waiting for a client ...");
		// aspetta che un CLIENT chieda di CONNETTERSI
		try (Socket socket = server.accept()) {
			this.socket = socket;
			
			System.out.println("Client accepted: " + socket);
			
			/* se volessimo gestire un multithreading con array list, creiamo una classe che estende thread
			 * poi definiamo un array list di classe di tipo:
			 * private ArrayList <Classe> nome_lista = new ArrayList ();
			 * 
			 * Poi in questo punto del codice dichiareremmo l'istanza che cattura la socket:
			 * Classe nome_istanza = new Classe(socket_in_ingresso);
			 * nome_lista.add
			 * classe.start(); ricordiamo infatti la classe è un thread che viene contenuto in un'arrayList
			 * 
			 * per ultimo la classeha la stessa struttura di quelle già viste
			 */
			
			// connessione riuscita: apriamo i due STREAM di I/O dal/verso il CLIENT
			open();
			
			boolean done = false;
			while (!done) {
				try {
					// attesa di un messaggio dal CLIENT
					String line = streamIn.readUTF();
					
					// FINE CONNESSIONE?
					done = line.trim().equalsIgnoreCase(".bye");
					
					// stampiamo sulla console il messaggio inviato dal clietn
					System.out.println(line);
					
					// semplice echo verso il client
					streamOut.writeUTF(line);
				} catch (IOException ioe) {
					done = true;
					System.out.println("CLIENT IS DOWN!!!");
				}
			}			
			System.out.println("BYE BYE from SERVER!!!");
		} catch (IOException e) {
			System.out.println("Acceptance Error: " + e.getMessage());
		}
	}

	private void open() throws IOException {
		streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		streamOut = new DataOutputStream(socket.getOutputStream());
	}

}
