
public class Main {
	public static void main(String[] args) {
		Printer printer = new Printer();

		PrinterThread printerThread1 = new PrinterThread(printer);
		PrinterThread printerThread2 = new PrinterThread(printer);
		PrinterThread printerThread3 = new PrinterThread(printer);
		PrinterThread printerThread4 = new PrinterThread(printer);
		PrinterThread printerThread5 = new PrinterThread(printer);
		PrinterThread printerThread6 = new PrinterThread(printer);
		PrinterThread printerThread7 = new PrinterThread(printer);
		PrinterThread printerThread8 = new PrinterThread(printer);
		PrinterThread printerThread9 = new PrinterThread(printer);
		PrinterThread printerThread10 = new PrinterThread(printer);

		printerThread1.start();
		printerThread2.start();
		printerThread3.start();
		printerThread4.start();
		printerThread5.start();
		printerThread6.start();
		printerThread7.start();
		printerThread8.start();
		printerThread9.start();
		printerThread10.start();
	}
}
