package Service;


import Model.Student;

import Service.StudentService;

public class StudentServiceImpl implements StudentService {
	@Override
	public void input(Student student) {
		student.input();
	}

	@Override
	public void info(Student student) {
		System.out.println("Id: " + student.getId());

		System.out.println("Name: " + student.getName());

		System.out.println("age: " + student.getAge());

	}
}