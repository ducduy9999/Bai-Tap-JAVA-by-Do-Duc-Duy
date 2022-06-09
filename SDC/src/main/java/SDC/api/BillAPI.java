package SDC.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import SDC.dto.ListBill;
import SDC.dto.User;
import SDC.repository.BillRepo;
import SDC.repository.UserRepo;

@RestController
@RequestMapping("/api/bill")
public class BillAPI {
	private static Logger logger = LoggerFactory.getLogger(BillAPI.class);
	@Autowired // DI
	BillRepo billRepo;// null
	@Autowired
	UserRepo userRepo;

	@PostMapping("/create")
	public Bill create(@RequestBody @Validated Bill bill) {
		List<User> users = userRepo.findAll();
		billRepo.save(bill);

		return bill;
	}

	@PutMapping("/update")
	public void update(@RequestBody @Validated Bill bill) {
		Bill oldOne = billRepo.getById(bill.getBillId());
		;

		oldOne.setName(bill.getName());
		billRepo.save(oldOne);
	}

	@DeleteMapping("/delete") // ?id=12
	public void delete(@RequestParam(value = "id") int id) {
		billRepo.deleteById(id);
		}

	@PostMapping("/search")
	public ListBill search(@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "id", required = false) Integer id,
			@RequestParam(name = "userId", required = false) Integer userId,
			@RequestParam(name = "fromDate", required = false) String fromDate,
			@RequestParam(name = "toDate", required = false) String toDate,
			@RequestParam(name = "sortBy", required = false) Integer sortBy,
			@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "size", required = false) Integer size) throws ParseException {

		if (size == null)
			size = 3;// max records per page
		if (page == null)
			page = 0;// trang hien tai
		Sort sort = Sort.by("buyDate").ascending();
		if (sortBy != null && sortBy.equals("name")) {
			sort = Sort.by("name").ascending();
		}

		Pageable pageable = PageRequest.of(page, size, sort);
		Page<Bill> pageBill = null;
		if (name != null && userId != null && fromDate != null && toDate != null && !fromDate.trim().isEmpty()
				&& !toDate.trim().isEmpty()) {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			pageBill = billRepo.searchByAll(userId, sdf.parse(fromDate), sdf.parse(toDate), name, pageable);
		} else {
			pageBill = billRepo.findAll(pageable);
		}
		// dùng list trả về api những thằng ko có trong dto
		ListBill listBill = new ListBill();
		listBill.setList(pageBill.toList());
		listBill.setTotalPage(pageBill.getTotalPages());
		listBill.setSize(size);
		listBill.setPage(page);
		listBill.setToDate(toDate);
		listBill.setFromDate(fromDate);
		listBill.setList(pageBill.toList());

		return listBill;
	}

}
