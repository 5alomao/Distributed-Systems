
public class Main {

	public static void main(String[] args) {
		Cube cube = new Cube();

		Producer producer = new Producer(cube);
		producer.setName("Produtor");
		producer.start();

		Consumer consumer = new Consumer(cube);
		consumer.setName("Consumidor");
		consumer.start();

		System.out.println("Fim Thread 'main'!");
	}
}
