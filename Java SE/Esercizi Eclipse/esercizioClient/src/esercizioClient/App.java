package esercizioClient;

import java.io.IOException;

public class App {

	public static void main(String[] args) {
		avvioCliente("localhost", 3000);

	}

	private static void avvioCliente(String address, int n) {
		String indirizzo = address;
		int numPorta = n;
		NuovoClient client = new NuovoClient (indirizzo, numPorta);
	}

}
