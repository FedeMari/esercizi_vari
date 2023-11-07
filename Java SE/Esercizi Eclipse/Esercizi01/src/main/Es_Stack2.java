package main;

public class Es_Stack2 {

	public static void main(String[] args) {
		int indice = 5;
		Stack2 s1 = new Stack2(indice);
		int ar[] = { 10, 50, 70, 80, 100, 90, 20 };

		for (int i = 0; i < ar.length; i++) {
			try {
				if (!s1.push(ar[i]))
					break;
			} catch (StackFullException e) {
				
				e.printStackTrace();
			}
		}
		if (s1.isFull()) {
			System.out.println("Lo stack è pieno!");
		}
		
		try {
			while (true) {
				System.out.println(s1.pop());
			}
		}catch (StackEmptyException e) {
			System.out.println("Lo stack è vuoto!");
		}
		
		
		
		
		/*while (true) {
			if (s1.isEmpty()) {
				break;
			}
			try {
				System.out.println(s1.pop());
			} catch (StackEmptyException e) {
				e.printStackTrace();
				break;
			}
		}
		if (s1.isEmpty()) {
			System.out.println("Lo stack è vuoto!");
		}*/
	}

}
