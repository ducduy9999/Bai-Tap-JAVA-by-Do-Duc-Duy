package com.trungtamjava.model;

import java.util.Scanner;
import java.util.Set;

public class School {
	private int id;
	private String nameSchool;
	private String diaChi;
    
	public void info() {
		System.out.println("id:"+id);
		System.out.println("nameSchool: "+nameSchool);
		System.out.println("diaChi: "+diaChi);
	}

	public void input(int i) {
		id = i;

		System.out.println("nhap truong Dai Hoc: ");
		nameSchool = new Scanner(System.in).nextLine();

		System.out.println("nhap dia chi nha: ");
		diaChi = new Scanner(System.in).nextLine();

	}

	public static void checkSchool() {

	}

	public School() {

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
