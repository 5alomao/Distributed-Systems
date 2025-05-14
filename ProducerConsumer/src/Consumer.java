
public class Consumer extends Thread {
	private Cube cube;

	public Consumer(Cube cube) {
		this.cube = cube;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			cube.get();
		}
	}
}
