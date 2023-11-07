package esercizi05;

public class Esercizi05 {

	public static void main(String[] args) {
		String str1 = "pippo";
        String str2 = "pluto";
        String str3 = "paperino";
        String str4 = "minnie";

        Lista lista = new Lista();
        lista.add(str1);
        lista.add(str2);
        lista.add(str3);
        lista.add(str4);

        System.out.println(lista);

	}

	private static void lambdaDemo2() {
		
	}

	private static void stringa() {
		//1
		FunzioneStringa frase = (str) -> str;
		
		System.out.println(frase.str("ciao"));
		//2
		FunzioneStringa testo = (String str) -> {
			String invertita = "";
			for(int i = str.length()-1;i>=0;i--) {
				invertita += str.charAt(i);
			}			
			return invertita;
		};
		
		System.out.println(testo.str(frase.str("ciao")));
		//3
		FunzioneStringa scrittura = (String str) -> {
			String scritta = "";
			char x;
			for(int i=0; i<str.length();i++) {
				x = str.charAt(i);
				if(x>=65 && x<=90) {
					x += 32;
				}
				else if(x>=97 && x<=122) {
					x -= 32;
				}
				scritta += x;			
			}			
			return scritta;
		};
		
		System.out.println(scrittura.str(frase.str("ciao")));
	}

	private static void lambdaDemo1() {
		//variabile contiene reference a questo metodo anonimo
		//qui sto solo dichiarando il metodo anonimo e poi assegno il reference alla variabile
		MyNumber mth_reference = () -> 123.45;
		//solo successivamente chiamando il metodo tramite la variabile 
		//equivale a chiamare l'espressione lambda
		System.out.println(mth_reference.getValue());
		//riassegno il reference alla variabile 
		mth_reference = () -> Math.random() * 100;
		
		System.out.println(mth_reference.getValue());
		
		IntFunction mth_reference2 = (n) -> (n % 2) == 0;
		
		System.out.println(mth_reference2.test(3));
		
		//Riusabilità infatti l'interfaccia chiede solo:
		//un intero in ingresso
		//un confronto che restituisca un booleano
		mth_reference2 = (n) -> n>0;
		
		System.out.println(mth_reference2.test(3));
		
		NumericTest2 mth_reference3 = (int n, int d) -> { 
			return (n%d)==0;
			};
		
		System.out.println(mth_reference3.test2(3,1));
		
		NumericTest3 mth_reference4 = (int n, int d) -> {
			int pow=0;
			while((n%d)==0) {
				//aggiorno n dandogli il quoziente della divisione 
				n=n/d;
				pow++;
			}
			return pow;
			};
		
		System.out.println(mth_reference4.test3(3,1));			
	}

}
