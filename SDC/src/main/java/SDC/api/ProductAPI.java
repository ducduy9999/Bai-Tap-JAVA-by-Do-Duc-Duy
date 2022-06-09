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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import SDC.dto.Category;
import SDC.dto.ListCategory;
import SDC.dto.ListProduct;
import SDC.dto.Product;
import SDC.repository.ProductRepo;


@RestController
@RequestMapping("/api/product")
public class ProductAPI {
	private static Logger logger = LoggerFactory.getLogger(ProductAPI.class);
	@Autowired // DI
	ProductRepo productRepo;// null

	@PostMapping("/create")

	public Product create(@RequestBody @Validated Product product) {

		productRepo.save(product);

		return product;
	}

	@PutMapping("/update")
	public void update(@RequestBody @Validated Product product) {
		Product oldOne = productRepo.getById(product.getId());
		;

		oldOne.setName(product.getName());
		productRepo.save(oldOne);
		
	}

	@DeleteMapping("/delete") // ?id=12
	public void delete(@RequestParam(value = "id") int id) {
		productRepo.deleteById(id);
		
	}

	@PostMapping("/search")
	public ListProduct search(@RequestParam(name = "name", required = false) String name,
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
		Page<Product> pageProduct = null;
		if (name != null && !name.isEmpty()) {
			pageProduct = productRepo.searchAll("%" + name + "%", pageable);

			
		} else {
			pageProduct = productRepo.findAll(pageable);
		}
		ListProduct listProduct = new ListProduct();
		listProduct.setList(pageProduct.toList());
		listProduct.setTotalPage(pageProduct.getTotalPages());
		listProduct.setSize(size);
		listProduct.setPage(page);
	

		return listProduct;
	}
}
