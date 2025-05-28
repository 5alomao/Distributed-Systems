
public class Producer extends Thread {
	private Cube cube;

	public Producer(Cube cube) {
		this.cube = cube;
	}

	@Override
	public void run() {
		while (true) {
			cube.put((int) (Math.random() * 100));
		}
	}
}
