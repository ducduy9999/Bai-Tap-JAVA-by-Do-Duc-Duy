

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tiket tiket = new Tiket();

		Thread t1 = new Thread(Tiket);
		Thread t2 = new Thread(Tiket);

		t1.start();
		t2.start();

	}

}
