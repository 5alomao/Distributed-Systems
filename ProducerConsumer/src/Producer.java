
public class Producer extends Thread {

	private Cube cube;

	public Producer(Cube cube) {
		this.cube = cube;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			cube.put((int) (Math.random() * 100));
		}
	}
}
