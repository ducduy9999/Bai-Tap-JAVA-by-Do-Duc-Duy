import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ListCellRenderer;

public class ListExam {
	public static void main(String[] args) {
		List<Person> myListPerson = input();
		info(myListPerson);
	}

	public static List<Person> input() {
		List<Person> persons = new ArrayList<>();
		Scanner c = new Scanner(System.in);
		while (true) {
			System.out.println("1.Nhap du lieu \n2.Thoat");
			int choice = c.nextInt();
			if (choice == 1) {
				System.out.println("Nhap tuoi: ");
				int age = c.nextInt();
				Person person = new Person(age);
				persons.add(person);
			} else {
				break;
			}
		}
		return persons;
	}

	public static void info(List<Person> ab) {
       for (Person pc : ab) {
		System.out.println("Id: " + pc.getId());

		System.out.println("Name: " + pc.getName());
		System.out.println("Age: " + pc.getAge());
        }
	}

}
