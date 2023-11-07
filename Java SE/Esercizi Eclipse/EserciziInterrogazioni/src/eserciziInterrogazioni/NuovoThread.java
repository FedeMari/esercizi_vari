package eserciziInterrogazioni;

public class NuovoThread extends Thread{
	public Thread t;
	int n;
	int tot=0;
	int k=0;

	public NuovoThread(int n) {
		// Create a new, second thread
		t = new Thread();
		t.setName("figlio");
		this.n=n;
	}
	
	@Override
	public void run() {
		System.out.println("Entering child thread: " + t.getName());

		try {
			for(int i=0; i<=n+2;i++) {
				if(tot>n) {
					k=i-2; 
					break;
				}
				tot=i*i;
				Thread.sleep(500);
			}
			
		} catch (InterruptedException e) {
			System.out.println("Child interrupted.");
		}
		System.out.println("Exiting child thread: " + t.getName());
		System.out.println("Il massimo numero intero per non sforare con le potenze e' " + k);
	}

}
