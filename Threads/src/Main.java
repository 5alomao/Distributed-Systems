
public class Main {

	public static final int MAX = 1000;

	public static void main(String[] args) {

		JobThread jobThread = new JobThread();
		jobThread.setName("Job Thread");
		// Coloca a thread em estado Runnable
		jobThread.start();

		// Thread tem tratamentos de manipulação, Runnable executa thread
		JobRunnable jobRunnable = new JobRunnable();
		Thread jobRunnableThread = new Thread(jobRunnable);
		jobRunnableThread.setName("Job Runnable");
		jobRunnableThread.start();

		String threadName = Thread.currentThread().getName();
		for (int i = 0; i <= MAX; i++) {
			System.out.printf("%s - %d\n", threadName, i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
		}
	}

}
