
public class Consumer extends Thread {

	private Cube cube;

	public Consumer(Cube cube) {
		this.cube = cube;
	}

	@Override
	public void run() {
		while (true) {
			cube.get();
		}
	}
}
