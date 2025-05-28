
public class Main {

	public static void main(String[] args) {
		Cube cube = new Cube();

		Producer producer = new Producer(cube);
		producer.setName("Producer 1");
		producer.start();

		Consumer consumer = new Consumer(cube);
		consumer.setName("Consumer 1");
		consumer.start();

		System.out.println("Fim thread 'main'.");
	}

}
