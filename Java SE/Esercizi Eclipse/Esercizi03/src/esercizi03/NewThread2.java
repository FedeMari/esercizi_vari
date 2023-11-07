package esercizi03;

public class NewThread2 extends Thread {
	private long timeout;
	private String name;

	public NewThread2() {
		timeout = 1000;
		name = "Demo Thread";
	}

	public NewThread2(String name, long timeout) {
		super(name);

		this.timeout = timeout;
		this.name = name;

		System.out.println("Child thread: " + this);
	}

	@Override
	public void run() {
		// String thisThreadName = Thread.currentThread().getName();

		System.out.println("Entering child thread: " + name);

		try {
			for (int i = 10; i > 0; i--) {
				System.out.println("\t" + name + ": " + i);
				Thread.sleep(timeout);
			}
		} catch (InterruptedException e) {
			System.out.println("Child interrupted.");
		}
		System.out.println("Exiting child thread: " + name);
	}
}
