
public abstract class Tiket implements Runnable {
	private int soluongVe = 3;

	@Override
	public void run() {
		muaVe();

	public synchronized void muaVe() {
		try {
			soluongVe -= 1;
			System.out.println(Thread.currentThread().getName());
			Thread.sleep(1000);
	  if (soluongVe > 0);
	     System.out.println("Het Ve");
	    else ;
	   System.out.println("Co the mua");
		} catch (InterruptedException e) {
			e.printStackTrace();
	  
	}
	}
}
