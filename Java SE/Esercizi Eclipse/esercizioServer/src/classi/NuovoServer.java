package classi;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class NuovoServer extends Thread{
	private String server = "Nuovo Server";
	private int numPorta;
	private ServerSocket s;
	private DataInputStream in;
	private DataOutputStream out;
	
	public NuovoServer(int numPorta) throws IOException {
		setName(server);
		this.numPorta=numPorta;
		System.out.println(server + " in avvio.");
		
		s = new ServerSocket(numPorta);

		System.out.println("Server started: " + s);
	}

	@Override
	public void run() {
		System.out.println("Waiting for a client ...");
		try {
			Socket socket = s.accept();
			
			System.out.println("Client accepted: " + socket);
			
			//apriamo canali in ingresso e uscita
			in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			out = new DataOutputStream(socket.getOutputStream());
			
			while(true) {
				String line = in.readUTF();
				if(line.trim().equalsIgnoreCase(".bye")) {
					break;
				}
				
				System.out.println(line);
				out.writeUTF(line);				
			}
			System.out.println("BYE BYE from SERVER!!!");
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}