package Service;

import Service.AddressService;
import Model.Address;

public class AddressServiceImpl implements AddressService {
	@Override
	public void input(Address address) {
		address.input();
	}

	@Override
	public void info(Address address) {
		System.out.println("Id: " + address.getId());

		System.out.println("Street: " + address.getStreet());

		System.out.println("City: " + address.getCity());

	}

}
