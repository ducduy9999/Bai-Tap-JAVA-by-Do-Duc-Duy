package SDC.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import SDC.dto.Category;
import SDC.dto.ListCategory;
import SDC.dto.ListUser;
import SDC.dto.User;
import SDC.repository.UserRepo;


@RestController
@RequestMapping("/api/user")
public class UserAPI {
	private static Logger logger = LoggerFactory.getLogger(UserAPI.class);
	User user = new User();
	@Autowired // DI
	UserRepo userRepo;// null

	@PostMapping("/create")

	public User create(@RequestBody @Validated User user) {

		userRepo.save(user);

		return user;
	}

	@PutMapping("/update")
	public void update(@RequestBody @Validated User user) {
		User oldOne = userRepo.getById(user.getId());
		;

		oldOne.setName(user.getName());
		userRepo.save(oldOne);
		
	}

	@DeleteMapping("/delete") // ?id=12
	public void delete(@RequestParam(value = "id") int id) {
		userRepo.deleteById(id);
		
	}

	@PostMapping("/search")
	public ListUser search(@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "id", required = false) Integer id,
			@RequestParam(name = "sortBy", required = false) Integer sortBy,
			@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "size", required = false) Integer size) {

		if (size == null)
			size = 3;// max records per page
		if (page == null)
			page = 0;// trang hien tai
		Sort sort = Sort.by("id").ascending();
		if (sortBy != null && sortBy.equals("name")) {
			sort = Sort.by("name").ascending();
		}

		Pageable pageable = PageRequest.of(page, size, sort);
		Page<User> pageUser = null;
		if (name != null && !name.isEmpty()) {
			pageUser = userRepo.searchAl("%" + name + "%", pageable);

			
		} else {
			pageUser = userRepo.findAll(pageable);
		}
		ListUser listUser   = new ListUser();
		listUser.setList(pageUser.toList());
		listUser.setTotalPage(pageUser.getTotalPages());
		listUser.setSize(size);
		listUser.setPage(page);
	

		return listUser;
	}
}
