package ModelLaptop;

import java.util.Scanner;

public class Laptop {
	private int id;
	private int ram;
	private int cpu;
	private int vga;

	
	/**
	 * 
	 */
	public Laptop() {
		super();
	}

	/**
	 * @param id
	 * @param ram
	 * @param cpu
	 * @param vga
	 */
	public Laptop(int id, int ram, int cpu, int vga) {
		super();
		this.id = id;
		this.ram = ram;
		this.cpu = cpu;
		this.vga = vga;
	}
	
	public void input() {
		System.out.println("Nhap Id");
		id = new Scanner(System.in).nextInt();
		System.out.println("Nhap CPU");
		cpu = new Scanner(System.in).nextInt();
		System.out.println("Nhap VGA");
		vga = new Scanner(System.in).nextInt();
		System.out.println("Nhap Ram");
		ram = new Scanner(System.in).nextInt();

	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRam() {
		return ram;
	}

	public void setRam(int ram) {
		this.ram = ram;
	}

	public int getCpu() {
		return cpu;
	}

	public void setCpu(int cpu) {
		this.cpu = cpu;
	}

	public int getVga() {
		return vga;
	}

	public void setVga(int vga) {
		this.vga = vga;
	}

}
