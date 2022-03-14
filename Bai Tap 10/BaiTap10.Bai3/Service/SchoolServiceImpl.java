package Service;

import Service.SchoolService;
import Model.School;

public class SchoolServiceImpl implements SchoolService {
	@Override
	public void input(School school) {
		school.input();
	}

	@Override
	public void info(School school) {
		System.out.println("Id: " + school.getId());

		System.out.println("Name: " + school.getName());

	
	}

}
