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

import SDC.dto.Bill;
import SDC.dto.BillItems;
import SDC.dto.ListBillItems;
import SDC.dto.Product;
import SDC.repository.BillItemsRepo;
import SDC.repository.BillRepo;
import SDC.repository.ProductRepo;

@RestController
@RequestMapping("/api/billitems")
public class BillItemsAPI {
	private static Logger logger = LoggerFactory.getLogger(BillItemsAPI.class);
	@Autowired // DI
	BillItemsRepo billItemsRepo;// null
	@Autowired
	BillRepo billRepo;
	@Autowired
	ProductRepo productRepo;

	@PostMapping("/create")

	public BillItems create(@RequestBody @Validated BillItems billItems) {
		List<Bill> bills = billRepo.findAll();
		List<Product> products = productRepo.findAll();
		billItemsRepo.save(billItems);
		

		return billItems;
	}

	@PutMapping("/update")
	public void update(@RequestBody @Validated BillItems billItems) {
		BillItems oldOne = billItemsRepo.getById(billItems.getBillItemsId());
		;

		oldOne.setName(billItems.getName());
		billItemsRepo.save(oldOne);
		
	}

	@DeleteMapping("/delete") // ?id=12
	public void delete(@RequestParam(value = "id") int id) {
		billItemsRepo.deleteById(id);
		
	}

	@PostMapping("/search")
	public ListBillItems search(@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "BillItemsId", required = false) Integer billItemsId,
			@RequestParam(name = "idBill", required = false) Integer idBill,
			@RequestParam(name = "productId", required = false) Integer productId,
			@RequestParam(name = "sortBy", required = false) Integer sortBy,
			@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "size", required = false) Integer size) {

		if (size == null)
			size = 3;// max records per page
		if (page == null)
			page = 0;// trang hien tai
		Sort sort = Sort.by("billItemsId").ascending();
		if (sortBy != null && sortBy.equals("name")) {
			sort = Sort.by("name").ascending();
		}

		Pageable pageable = PageRequest.of(page, size, sort);
		Page<BillItems> pageBillItems = null;
		if (name != null && !name.isEmpty()) {
			pageBillItems = billItemsRepo.searchByAll("%" + name + "%", productId, idBill, pageable);

			
		} else {
			pageBillItems = billItemsRepo.findAll(pageable);
		}
		ListBillItems listBillItems = new ListBillItems();
		listBillItems.setList(pageBillItems.toList());
		listBillItems.setTotalPage(pageBillItems.getTotalPages());
		listBillItems.setSize(size);
		listBillItems.setPage(page);
	

		return listBillItems;
	}
}
