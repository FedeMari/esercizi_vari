package main;

import java.io.*;

import classi.SimpleChatClient;

public class App {
	/* Il client richiede due argomenti:
	 *  1) host name/ip
	 *  2) porta del server a cui il client si vuole connettere
	 */

	public static void main(String[] args) {
		// se non vengono passati i due parametri usciamo
//		if (args.length != 2) {
//			System.out.println("Usage: java ChatClient host port");
//			return;
//		}
		//var client = new SimpleChatClient(args[0], Integer.parseInt(args[1])); 
		var client = new SimpleChatClient("localhost",3000);  
    }
}
