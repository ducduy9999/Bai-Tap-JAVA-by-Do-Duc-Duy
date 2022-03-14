package Model;

import java.util.Scanner;

public class Student {
	private int age;
	private int id;
	private String name;

	/**
	 * 
	 */
	public Student() {
		super();
	}

	/**
	 * @param age
	 * @param id
	 * @param name
	 */
	public Student(int age, int id, String name) {
		super();
		this.age = age;
		this.id = id;
		this.name = name;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(int age) {
		this.age = age;
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

		System.out.println("Nhap ten");
		name = new Scanner(System.in).nextLine();

		System.out.println("Nhap tuoi");
		age = new Scanner(System.in).nextInt();

	}
}