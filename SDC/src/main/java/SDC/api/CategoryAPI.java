package SDC.api;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import SDC.dto.Category;
import SDC.dto.ListCategory;
import SDC.repository.CategoryRepo;


@RestController
@RequestMapping("/api/category")
public class CategoryAPI {
	private static Logger logger = LoggerFactory.getLogger(CategoryAPI.class);
	@Autowired // DI
	CategoryRepo categoryRepo;// null

	@PostMapping("/create")

	public Category create(@RequestBody @Validated Category category) {

		categoryRepo.save(category);

		return category;
	}

	@PutMapping("/update")
	public void update(@RequestBody @Validated Category category) {
		Category oldOne = categoryRepo.getById(category.getId());
		;

		oldOne.setName(category.getName());
		categoryRepo.save(oldOne);
		
	}

	@DeleteMapping("/delete") // ?id=12
	public void delete(@RequestParam(value = "id") int id) {
		categoryRepo.deleteById(id);
		
	}

	@PostMapping("/search")
	public ListCategory search(@RequestParam(name = "name", required = false) String name,
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
		Page<Category> pageCategory = null;
		if (name != null && !name.isEmpty()) {
			pageCategory = categoryRepo.searchAll("%" + name + "%", pageable);

			
		} else {
			pageCategory = categoryRepo.findAll(pageable);
		}
		ListCategory listCategory = new ListCategory();
		listCategory.setList(pageCategory.toList());
		listCategory.setTotalPage(pageCategory.getTotalPages());
		listCategory.setSize(size);
		listCategory.setPage(page);
	

		return listCategory;
	}
}

