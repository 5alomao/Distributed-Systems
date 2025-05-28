
public class Cube {
	private int value;
	private boolean someToConsumer = false;

	public int get() {
		System.err.printf("Consumidor consumiu %d\n", value);
		someToConsumer = false;
		return value;
	}

	public void put(int value) {
		System.out.printf("Produtor produziu %d\n", value);
		someToConsumer = true;
		this.value = value;
	}

	public boolean getSomeToConsumer() {
		return someToConsumer;
	}

}
