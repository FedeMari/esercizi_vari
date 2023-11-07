package esercizi03;

public class NewThread implements Runnable {
	public Thread t;

	public NewThread() {
		// Create a new, second thread
		t = new Thread(this, "Demo Thread");
		System.out.println("Child thread: " + t);
	}

	// This is the entry point for the second thread.
	@Override
	public void run() {
		String thisThreadName = Thread.currentThread().getName();

		System.out.println("Entering child thread: " + thisThreadName);

		try {
			for (int i = 10; i > 0; i--) {
				System.out.println("\t" + thisThreadName + ": " + i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			System.out.println("Child interrupted.");
		}
		System.out.println("Exiting child thread: " + thisThreadName);
	}
}