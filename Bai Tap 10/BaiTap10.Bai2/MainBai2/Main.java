package MainBai2;

import ModelLaptop.Laptop;
import ModelLaptop.LaptopServiceImpl;
import ModelLaptop.ServiceLaptop;

public class Main {
	public static void main(String[] args) {
		Laptop myLaptop = new Laptop();
		LaptopServiceImpl myLaptopService = new LaptopServiceImpl();
		
		myLaptopService.input(myLaptop);
		myLaptopService.info(myLaptop);
	}

}
