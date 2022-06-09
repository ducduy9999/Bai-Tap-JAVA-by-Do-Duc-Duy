package SDC.controller;

import java.util.Arrays;
import java.util.List;

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

import SDC.dto.Bill;
import SDC.dto.BillItems;
import SDC.dto.Product;
import SDC.repository.BillItemsRepo;
import SDC.repository.BillRepo;
import SDC.repository.ProductRepo;

@Controller
@RequestMapping("/billitems")
public class BillItemsController {
	private static Logger logger = LoggerFactory.getLogger(BillItemsController.class);

	@Autowired // DI
	BillItemsRepo billItemsRepo;// null

	@Autowired // DI
	BillRepo billRepo;// null

	@Autowired // DI
	ProductRepo productRepo;// null

	@GetMapping("/create")
	public String create(Model model) {
		//quan hệ lưu vào list mới
		List<Bill> bills = billRepo.findAll();
		model.addAttribute("bills", bills);

		List<Product> products = productRepo.findAll();
		model.addAttribute("products", products);
		model.addAttribute("billitems", new BillItems());
		return "billitems/create";
	}

	@PostMapping("/create")
	public String create(@ModelAttribute("billitems") @Valid BillItems billitems, BindingResult blindingResult, Model model) {
		if (blindingResult.hasErrors()) {
			List<Bill> bills = billRepo.findAll();
			model.addAttribute("bills", bills);

			List<Product> products = productRepo.findAll();
			model.addAttribute("products", products);
			return "billitems/create";
		}
		billItemsRepo.save(billitems);

		return "redirect:/billitems/search";
	}

	@GetMapping("/update") // ?id=12
	public String update(@RequestParam("id") int id, Model model) {
		BillItems billitems = billItemsRepo.getById(id);
		model.addAttribute("billitems", billitems);

		List<Bill> bills = billRepo.findAll();
		model.addAttribute("bills", bills);

		List<Product> products = productRepo.findAll();
		model.addAttribute("products", products);
		return "billitems/update.html";
	}

	@PostMapping("/update")
	public String update(@ModelAttribute BillItems billitems) {
		BillItems oldOne = billItemsRepo.getById(billitems.getBillItemsId());
		;
		billItemsRepo.save(oldOne);
		return "redirect:/billitems/search";
	}

	@GetMapping("/delete") // ?id=12
	public String delete(@RequestParam("id") int billItemsId) {
		billItemsRepo.deleteById(billItemsId);
		return "redirect:/billitems/search";// url maping
	}

	@GetMapping("/search")
	public String search(Model model, @RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "billItemsId", required = false) Integer billItemsId,
			@RequestParam(name = "idBill", required = false) Integer idBill,
			@RequestParam(name = "productId", required = false) Integer productId,
			@RequestParam(name = "sortBy", required = false) Integer sortBy,
			@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "size", required = false) Integer size) {

		if (size == null)
			size = 3;// max records per page
		if (page == null)
			page = 0;// trang hien tai
		Sort sort = Sort.by("billItemsId").ascending();//sắp sếp theo
		if (sortBy != null && sortBy.equals("name")) {
			sort = Sort.by("name").ascending();
		} else if (sortBy != null && sortBy.equals("idBill")) {
			sort = Sort.by("idBill").ascending();
		} else if (sortBy != null && sortBy.equals("productId")) {
			sort = Sort.by("productId").ascending();
		}

		Pageable pageable = PageRequest.of(page, size, sort); // phân trang

		if (name != null && !name.isEmpty()) {
			Page<BillItems> pageBillItems = billItemsRepo.searchByAll("%" + name + "%", productId, idBill, pageable);

			model.addAttribute("list", pageBillItems.toList());
			model.addAttribute("totalPage", pageBillItems.getTotalPages());
		} else if (billItemsId != null) {
			BillItems billItems = billItemsRepo.findById(billItemsId).orElse(null);
			if (billItems != null) {
				model.addAttribute("list", Arrays.asList(billItems));
			} else
				// log
				logger.info("Id not found");

			model.addAttribute("totalPage", 0);

		} else {
			Page<BillItems> pageBillItems = billItemsRepo.findAll(pageable);

			model.addAttribute("list", pageBillItems.toList());
			model.addAttribute("totalPage", pageBillItems.getTotalPages());
		}

		model.addAttribute("page", page);
		model.addAttribute("size", size);
		model.addAttribute("name", name == null ? "" : name);
		model.addAttribute("billItemsId", billItemsId == null ? "" : billItemsId);
		model.addAttribute("idBill", idBill == null ? "" : idBill);
		model.addAttribute("productId", productId == null ? "" : productId);
		model.addAttribute("sortBy", sortBy == null ? "" : sortBy);

		return "billitems/search";
	}

}
