package esercizioClient;

import java.io.*;
import java.net.*;

public class NuovoClient {
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private DataInputStream console;
	
	public NuovoClient (String host, int nPorta){
		System.out.println("Establishing connection. Please wait ...");
		try {
			socket = new Socket(host, nPorta);
			System.out.println("Connected: " + socket);
			
			console = new DataInputStream(System.in);
			in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			out = new DataOutputStream(socket.getOutputStream());

			String line = "";
			while (true) {
				line = console.readLine();
				if (line.trim().equalsIgnoreCase(".bye")) {
					break;
				}

				out.writeUTF(line);
				out.flush();

				line = in.readUTF();
				System.out.println("Risposta" + line);
			}
		} catch (UnknownHostException e) {
			System.out.println("Host unknown: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("I/O exception: " + e.getMessage());
		}
		
		try {
			console.close();
			socket.close();
		} catch (IOException e) {
			System.out.println("I/O exception: " + e.getMessage());
		}
		
	}

}
