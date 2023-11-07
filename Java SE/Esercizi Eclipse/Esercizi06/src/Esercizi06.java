import java.util.*;
import java.io.*;

public class Esercizi06 {
	
	//static String dirPath =".";
	//static String dirPath ="../Esercizio_Garage";
	//static String filePath = dirPath + fileName;
	
		// array di stringhe, lunghezza media delle stringhe


	public static void main(String[] args) {
		//arrayList();
		//dirList("../Esercizio_Garage");
		
		String [] arr = {"Ciao come stai","Buonanotte tesoro","Michela e' simpatica"};
		int ct = 0;
		for(String txt : arr) {
			System.out.println(txt + ": " + txt.length());
			ct += txt.length();
		}
		
		int vm = ct/arr.length;
		
		System.out.println("Il valore medio dei caratteri e' " + vm);
		
	}

	private static void dirList(String dirPath) {
		File f = new File(dirPath);
		if (f.isDirectory()) {
			System.out.println(f.getName());
			String[] elementiContenuti = f.list();
			for (String elem : elementiContenuti) {
				File f1 = new File(dirPath + "/" + elem);
				if (f1.isDirectory()) {
					System.out.println("\t" + f1.getName());
					String[] elementiContenuti2 = f1.list();
					for (String elem2 : elementiContenuti2) {
						File f2 = new File(f1 + "/" + elem2);
						if (f2.isDirectory()) {
							System.out.println("\t\t" + f2.getName());
							String[] elementiContenuti3 = f2.list();
							for (String elem3 : elementiContenuti3) {
								File f3 = new File(f2 + "/" + elem3);
								if (f3.isDirectory()) {
									System.out.println("\t\t\t" + f3.getName());
								}
								else {
									System.out.println("\t\t\t\t" + f3.getName() + " is a file");
								}
							}
						}
					}
				}				
			}
		} else {
			//System.out.println(newFile.getName() + "(file)");
		}

	}
	
	private static void ricorsione(File f) {
		
	}

	private static String tab(int n) {
		String s = "";
		for(int i=0; i<n;i++) {
			s+="\t";
		}
		return s;				
	}

	private static void arrayList() {
		ArrayListCustom<Integer> list = new ArrayListCustom<Integer>(8);
		list.addAll(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
		System.out.println("dimensione: " + list.size() + " capacita: " + list.capacity());
		for (int i = 0; i < 3; i++) {
			list.removeAT(1);
		}
		System.out.println("dimensione: " + list.size() + " capacita: " + list.capacity());
		list.setElem(9,1);
		System.out.println(list);		
	}
}
