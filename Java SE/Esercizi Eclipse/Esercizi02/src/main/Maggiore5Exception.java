package main;
//siccome "Maggiore5Exception" deriva da "Exception", sarà una CHECKED EXCEPTION

@SuppressWarnings("serial")
public class Maggiore5Exception extends Exception {
    public Maggiore5Exception(int value, String message) {
        super("valore: " + value + " - " + message); //il costruttore di Exception accetta un solo
        								//parametro di tipo Stringa che qui infatti abbiamo fornito
    }
}
