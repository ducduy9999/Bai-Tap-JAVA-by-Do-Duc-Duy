package SDC.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import SDC.dto.Category;
import SDC.repository.CategoryRepo;

@Controller
@RequestMapping("/category")
public class CategoryController {
	private static Logger logger = LoggerFactory.getLogger(CategoryController.class);
	@Autowired // DI
	CategoryRepo categoryRepo;// null

	@GetMapping("/create")
	//@Secured(value = { "ROLE ADMIN" })
	@PreAuthorize("hasRole('ROLE_ADMIN')") //phân role theo hàm
	public String create(Model model, HttpServletRequest request) {//sicurity theo hàm

		User currentUser = (User) SecurityContextHolder.getContext() // User của sicurity
		.getAuthentication().getPrincipal();

		System.out.println(currentUser.getUsername());

		if (request.isUserInRole("ADMIN")) {
			System.out.println("ADMIN");
		}

		model.addAttribute("category", new Category());

		return "category/create";
	}

//	@GetMapping("/create")
//	public String create(Model model) {
//		model.addAttribute("category", new Category());
//
//		return "category/create";
//	}

	@PostMapping("/create")
	public String create(@ModelAttribute("category") @Valid Category category, BindingResult blindingResult) {
		if (blindingResult.hasErrors()) {
			return "category/create";
		}
		categoryRepo.save(category);

		return "redirect:/category/search";
	}

	@GetMapping("/update") // ?id=12
	public String update(@RequestParam("id") int id, Model model) {
		Category category = categoryRepo.getById(id);
		model.addAttribute("category", category);
		return "category/update.html";
	}

	@PostMapping("/update")
	public String update(@ModelAttribute Category category) {
		Category oldOne = categoryRepo.getById(category.getId());
		;

		oldOne.setName(category.getName());

		categoryRepo.save(oldOne);
		return "redirect:/category/search";
	}

	@GetMapping("/delete") // ?id=12
	public String delete(@RequestParam(value = "id") int id) {
		categoryRepo.deleteById(id);
		return "redirect:/category/search";// url maping
	}

	@GetMapping("/search")
	public String search(Model model, @RequestParam(name = "name", required = false) String name,
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

		if (name != null && !name.isEmpty()) {
			Page<Category> pageCategory = categoryRepo.searchAll("%" + name + "%", pageable);

			model.addAttribute("list", pageCategory.toList());
			model.addAttribute("totalPage", pageCategory.getTotalPages());
		} else if (id != null) {
			Category category = categoryRepo.findById(id).orElse(null);
			if (category != null) {
				model.addAttribute("list", Arrays.asList(category));
			} else
				// log
				logger.info("Id not found");

			model.addAttribute("totalPage", 0);
		} else {
			Page<Category> pageCategory = categoryRepo.findAll(pageable);

			model.addAttribute("list", pageCategory.toList());
			model.addAttribute("totalPage", pageCategory.getTotalPages());
		}

		model.addAttribute("page", page);
		model.addAttribute("size", size);
		model.addAttribute("name", name == null ? "" : name);
		model.addAttribute("id", id == null ? "" : id);
		model.addAttribute("sortBy", sortBy == null ? "" : sortBy);
		return "category/search";
	}
}
