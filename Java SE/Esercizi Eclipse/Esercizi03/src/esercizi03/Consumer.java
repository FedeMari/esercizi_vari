package esercizi03;

public class Consumer extends Thread {
	static Consumer instance = null;
	
	QueueInt queue;
	long timeout;
	boolean stop = false;
	
	private Consumer(QueueInt queue, long timeout) {
		super("Consumer");
		this.queue = queue;
		this.timeout = timeout;
	}
	private Consumer() {}
	
	public static Consumer getInstance() {
		if (instance == null) {
			instance = new Consumer();
		}
		return instance;
	}
	
	public static Consumer getInstance(QueueInt queue, long timeout) {
		if (instance == null) {
			instance = new Consumer(queue, timeout);
		}
		return instance;
	}
	
	public void Stop() {
		stop = true;
	}
	
	public static Consumer Start(QueueInt queue, long timeout) {
		var cons = Consumer.getInstance(queue, timeout);
		
		cons.start();
		
		return cons;
	}

	@Override
	public void run() {
		while (!stop) {
			System.out.println("\t"
					+ "\tvalore scodato: " + queue.Dequeue());
			
			try {
				Thread.sleep(timeout);
			} catch (InterruptedException e) {
				System.out.println("Consumer INTERROTTO!!");
			}
		}
	}
}
