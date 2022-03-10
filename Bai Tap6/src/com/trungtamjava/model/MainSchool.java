package com.trungtamjava.model;

import java.util.Scanner;
import com.trungtamjava.model.School;

public class MainSchool {

	public static void main(String[] args) {
//		 Tao mang voi so luong truong do nguoi dung nhap  
		int soLuongTruong;
		System.out.println("Nhap So Luong Truong");
		soLuongTruong = new Scanner(System.in).nextInt();
		School[] school = new School[soLuongTruong];
		
//		 tao tung school voi tung input
		for (int i = 0; i < soLuongTruong; i++) {
		   school[i] = new School();
		   school[i].input(i);
		}
		
		// in thong tin tung school trong mang
		for (int i = 0; i < soLuongTruong; i++) {
			school[i].info();
		}
	}
}