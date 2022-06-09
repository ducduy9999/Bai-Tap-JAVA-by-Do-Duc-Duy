package SDC.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import SDC.dto.Product;
import SDC.repository.ProductRepo;

@Controller
@RequestMapping("/product")
public class ProductController {
	private static Logger logger = LoggerFactory.getLogger(ProductController.class);
	@Autowired // DI
	ProductRepo productRepo;// null

	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("product", new Product());

		return "product/create";
	}

	@PostMapping("/create")
	public String create(@ModelAttribute("product") @Valid Product product,
			@RequestParam(name = "file", required = false) MultipartFile file, BindingResult blindingResult) {
		if (blindingResult.hasErrors()) {
			return "product/create";
		}
		// lưu lại file vào 1 folder, sau đó lấy url save to db
		if (file != null && file.getSize() > 0) {
			try {
				final String folder = "/Applications";
				String originFilename = file.getOriginalFilename();

				File newFile = new File(folder + "/" + originFilename);
				// coppy noi dung file upload vao file new
				file.transferTo(newFile);
				// lưu lại url flie vao db
				product.setImageURL(originFilename);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		productRepo.save(product);
		return "redirect:/product/search";
	}

	@GetMapping("/download") // ?imageURL
	public void download(@RequestParam("imageURL") String imageURL, HttpServletResponse response) {
		final String folder = "/Applications";
		File file = new File(folder + "/" + imageURL);
		if (file.exists()) {
			try {
				Files.copy(file.toPath(), response.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@GetMapping("/update") // ?id=12
	public String update(@RequestParam("id") int id, Model model) {
		Product product = productRepo.getById(id);
		model.addAttribute("product", product);
		return "product/update.html";
	}

	@PostMapping("/update")
	public String update(@ModelAttribute Product product) {
		Product oldOne = productRepo.getById(product.getId());
		;

		oldOne.setName(product.getName());

		productRepo.save(oldOne);
		return "redirect:/product/search";
	}

	@GetMapping("/delete") // ?id=12
	public String delete(@RequestParam(value = "id") int id) {
		productRepo.deleteById(id);
		return "redirect:/product/search";// url maping
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
			Page<Product> pageProduct = productRepo.searchAll("%" + name + "%", pageable);

			model.addAttribute("list", pageProduct.toList());
			model.addAttribute("totalPage", pageProduct.getTotalPages());
		} else if (id != null) {
			Product product = productRepo.findById(id).orElse(null);
			if (product != null) {
				model.addAttribute("list", Arrays.asList(product));
			} else
				// log
				logger.info("Id not found");

			model.addAttribute("totalPage", 0);
		} else {
			Page<Product> pageProduct = productRepo.findAll(pageable);

			model.addAttribute("list", pageProduct.toList());
			model.addAttribute("totalPage", pageProduct.getTotalPages());
		}

		model.addAttribute("page", page);
		model.addAttribute("size", size);
		model.addAttribute("name", name == null ? "" : name);
		model.addAttribute("id", id == null ? "" : id);
		model.addAttribute("sortBy", sortBy == null ? "" : sortBy);
		return "product/search";
	}

}
