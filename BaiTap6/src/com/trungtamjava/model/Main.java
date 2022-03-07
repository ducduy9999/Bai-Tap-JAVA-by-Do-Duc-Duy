package com.trungtamjava.model;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		// TODO Auto-generated method stub

		School school1 = new School();
		school1.setDiaChi("So nha A");
		school1.setId(5);
		school1.setNameSchool("Dai hoc A");

		School school2 = new School(5);
		System.out.println(school2.getId());

		School school3 = new School("So nha A", "Dai Hoc A");
		System.out.println(school3.getDiaChi());

		School school4 = new School("So nha A", "Dai Hoc A", 5);
		System.out.println(school4.getNameSchool());

	System.out.println("Nhap ten truong");
		String D = new Scanner(System.in).nextLine();
	//	checkD();
		
		


		String School[] = 
		for (int i = 0; i < school.length; i++) {
			school[i] = new School();
			school[i].input();
		}

		System.out.println("Nhap Ten Truong: ");
		Scanner name = new Scanner(System.in);
		String s = name.nextLine();
		for(int i=0;i<school.length;i++) {
			if (school[i].getSchoolName().contains(s)) {
				school[i].info();
		

	}
		}
}
}
