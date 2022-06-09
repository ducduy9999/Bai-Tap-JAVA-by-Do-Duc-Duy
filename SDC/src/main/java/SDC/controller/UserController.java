package SDC.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import SDC.dto.Product;
import SDC.dto.User;
import SDC.repository.UserRepo;

@Controller
@RequestMapping("/user")
public class UserController {
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
		@Autowired // DI
		UserRepo userRepo;// null

		@GetMapping("/create")
		public String create(Model model) {
			model.addAttribute("user", new User());
			return "user/create";
		}

		@PostMapping("/create")
		public String create(@ModelAttribute("user") @Valid User user, BindingResult blindingResult) {
			if (blindingResult.hasErrors()) {
				return "user/create";
			}
			user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

			userRepo.save(user);
		

			return "redirect:/user/search";
		}
		@GetMapping("/update") // ?id=12
		public String update(@RequestParam("id") int id, Model model) {
			User user = userRepo.getById(id);
			model.addAttribute("user", user);
			return "user/update.html";
		}

		@PostMapping("/update")
		public String update(@ModelAttribute User user) {
			userRepo.save(user);
			return "redirect:/user/search";
		}

		@GetMapping("/delete") // ?id=12
		public String delete(HttpServletRequest req, @RequestParam("id") int id) {

			userRepo.deleteById(id);

//			req.setAttribute("msg", "Xoa Thanh cong!");

			return "redirect:/user/search";// url maping
		}

		@GetMapping("/search")
		public String search(Model model, @RequestParam(name = "username", required = false) String username,
				@RequestParam(name = "id", required = false) Integer id,
				@RequestParam(name = "sortBy", required = false) Integer sortBy,
				@RequestParam(name = "page", required = false) Integer page,
				@RequestParam(name = "size", required = false) Integer size) {

			if (size == null)
				size = 3;// max records per page
			if (page == null)
				page = 0;// trang hien tai
			Sort sort = Sort.by("id").ascending();
			if (sortBy != null && sortBy.equals("username")) {
				sort = Sort.by("username").ascending();
			}

			Pageable pageable = PageRequest.of(page, size, sort);

			if (username != null && !username.isEmpty()) {
				Page<User> pageUser = userRepo.searchAl("%" + username + "%", pageable);

				model.addAttribute("list", pageUser.toList());
				model.addAttribute("totalPage", pageUser.getTotalPages());
			} else if (id != null) {
				User user = userRepo.findById(id).orElse(null);
				if (user != null) {
					model.addAttribute("list", Arrays.asList(user));
				} else
					// log
					logger.info("Id not found");

				model.addAttribute("totalPage", 0);
			} else {
				Page<User> pageUser = userRepo.findAll(pageable);

				model.addAttribute("list", pageUser.toList());
				model.addAttribute("totalPage", pageUser.getTotalPages());
			}

			model.addAttribute("page", page);
			model.addAttribute("size", size);
			model.addAttribute("username", username == null ? "" : username);
			model.addAttribute("id", id == null ? "" : id);
			model.addAttribute("sortBy", sortBy == null ? "" : sortBy);
			return "user/search";
		}

	}

