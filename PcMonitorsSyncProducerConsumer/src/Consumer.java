
public class Consumer extends Thread {

	private Cube cube;

	public Consumer(Cube cube) {
		this.cube = cube;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (cube) {
				if (!cube.getSomeToConsumer()) {
					try {
						System.out.println("Consumidor entrando na SC");
						cube.wait();
					} catch (InterruptedException e) {
					}
				}
				cube.get();
				cube.notify();
			}
		}
	}
}
