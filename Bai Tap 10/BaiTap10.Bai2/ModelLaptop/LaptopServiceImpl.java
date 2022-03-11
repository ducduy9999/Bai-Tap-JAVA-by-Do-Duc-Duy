package ModelLaptop;

import ModelLaptop.ServiceLaptop;
import ModelLaptop.Laptop;

public class LaptopServiceImpl implements ServiceLaptop {
	@Override
	public void input(Laptop laptop) {
		laptop.input();
	}

	@Override
	public void info(Laptop laptop) {
		System.out.println("Id: " + laptop.getId());

		System.out.println("Cpu: " + laptop.getCpu());

		System.out.println("Ram: " + laptop.getRam());

		System.out.println("vga: " + laptop.getVga());
	}
}