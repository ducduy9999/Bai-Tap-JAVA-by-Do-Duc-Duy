package com.trungtamjava.model;

import java.util.Scanner;

public class School {
	private int id;
	private String nameSchool;
	private String diaChi;

	public void info() {
		System.out.println("id:");
		;
		System.out.println("nameSchool: ");
		;
		System.out.println("diaChi: ");
		;
	}

	public void input() {
		System.out.println("nhap so id: ");
		id = new Scanner(System.in).nextInt();
		
		System.out.println("nhap truong Dai Hoc: ");
		nameSchool = new Scanner(System.in).nextLine();

		System.out.println("nhap dia chi nha: ");
		diaChi = new Scanner(System.in).nextLine();

	}

	public School() {

	}

	public School(String nameSchool, String diaChi) {
		this.id = id;
		this.nameSchool = nameSchool;
		this.diaChi = diaChi;
	}

	public School(int id) {
		this.id = id;

	}

	public School(String nameSchool, String diaChi, int id) {
		this.nameSchool = nameSchool;
		this.diaChi = diaChi;
		this.id = id;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameSchool() {
		return nameSchool;
	}

	public void setNameSchool(String nameSchool) {
		this.nameSchool = nameSchool;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

}
