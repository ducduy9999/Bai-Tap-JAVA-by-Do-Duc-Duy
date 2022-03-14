package Model;

import java.util.Scanner;

public class School {
	private int id;
	private String name;

	/**
	 * 
	 */
	public School() {
		super();
	}

	/**
	 * @param id
	 * @param name
	 */
	public School(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public void input() {
		System.out.println("Nhap Id");
		id = new Scanner(System.in).nextInt();

		System.out.println("Nhap street");
		name = new Scanner(System.in).nextLine();

	}
}
