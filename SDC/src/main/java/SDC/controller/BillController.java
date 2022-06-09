package SDC.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import SDC.dto.User;
import SDC.repository.BillRepo;
import SDC.repository.UserRepo;
import SDC.service.MailService;

@Controller
@RequestMapping("/bill")
public class BillController {
	private static Logger logger = LoggerFactory.getLogger(BillController.class);

	@Autowired // DI
	BillRepo billRepo;// null

	@Autowired // DI
	UserRepo userRepo;// null

	@Autowired // DI
	MailService mailService;// null

	@GetMapping("/create")
	public String create(Model model) {

		List<User> users = userRepo.findAll(); //quan hệ user để chọn 
		model.addAttribute("users", users);
		model.addAttribute("bill", new Bill());

		return "bill/create";
	}

	@PostMapping("/create")
	public String create(@ModelAttribute("bill") @Valid Bill bill, BindingResult blindingResult, Model model) {
		if (blindingResult.hasErrors()) {
			List<User> users = userRepo.findAll();
			model.addAttribute("users", users);
			return "bill/create";
		}
        
		bill.setBuyDate(new Date());
		//lên lịch quét để gửi về email xem có đơn hàng
		//convert current Date to milisecond
        long currentMilis = new Date().getTime();
        //convert milis to Date
        Date date = new Date(currentMilis - 5 * 60 * 1000);
        List<Bill> billList = billRepo.searchByDate(date);
		
		billRepo.save(bill);

		User user = userRepo.getById(bill.getUser().getId());
		mailService.sendEmail(user.getEmailUser(), "Hoa don", "ban da mua hang thanh cong");

		return "redirect:/bill/search";
	}

	@GetMapping("/update") // ?id=12
	public String update(@RequestParam("id") int id, Model model) {
		Bill bill = billRepo.getById(id);
		model.addAttribute("bill", bill);

		List<User> users = userRepo.findAll();
		model.addAttribute("users", users);

		return "bill/update.html";
	}

	@PostMapping("/update")
	public String update(@ModelAttribute Bill bill) {
		Bill oldOne = billRepo.getById(bill.getBillId());
		// set them
		billRepo.save(oldOne);
		return "redirect:/bill/search";
	}

	@GetMapping("/delete") // ?id=12
	public String delete(@RequestParam("id") int id) {
		billRepo.deleteById(id);
		return "redirect:/bill/search";// url maping
	}

	@GetMapping("/search")
	public String search(Model model, @RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "userId", required = false) Integer userId,
			@RequestParam(name = "fromDate", required = false) String fromDate,
			@RequestParam(name = "toDate", required = false) String toDate,
			@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "size", required = false) Integer size) throws ParseException {

		if (size == null)
			size = 3;// max records per page
		if (page == null)
			page = 0;// trang hien tai

		Pageable pageable = PageRequest.of(page, size, Sort.by("buyDate").ascending());

		if (name != null && userId != null && fromDate != null && toDate != null && !fromDate.trim().isEmpty()
				&& !toDate.trim().isEmpty()) {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Page<Bill> pageBill = billRepo.searchByAll(userId, sdf.parse(fromDate), sdf.parse(toDate), name, pageable);

			model.addAttribute("list", pageBill.toList());
			model.addAttribute("totalPage", pageBill.getTotalPages());
		}
		// viet them dieu kien va ham tim kiem vao day
		else {
			Page<Bill> pageBill = billRepo.findAll(pageable);

			model.addAttribute("list", pageBill.toList());
			model.addAttribute("totalPage", pageBill.getTotalPages());
		}

		model.addAttribute("page", page);
		model.addAttribute("size", size);

		model.addAttribute("name", name == null ? "" : name);
		model.addAttribute("userId", userId == null ? "" : userId);
		model.addAttribute("fromDate", fromDate == null ? "" : fromDate);
		model.addAttribute("toDate", toDate == null ? "" : toDate);

		List<User> users = userRepo.findAll();
		model.addAttribute("users", users);

		return "bill/search";
	}
	@GetMapping("/thongke")
	public String thongKe(Model model) throws ParseException {
		List<Object[]> list = billRepo.thongKeTheoThang();

		List<Bill> billss = new ArrayList<Bill>();
		if (list != null && !list.isEmpty()) {
			for (Object[] objects : list) {
				Bill bill = new Bill();
				bill.setSoLuong(Integer.parseInt((objects[0]).toString()));
				bill.setThang(Integer.parseInt((objects[1]).toString()));
				billss.add(bill);
			}
		}
		model.addAttribute("bill", billss);
		return "bill/thongke";
	}
//	@GetMapping("/thongkesoluong")
//	public String thongKeSoLuong(Model model) throws ParseException {
//		List<Object[]> list = billRepo.thongKeSoLuong();
//
//		List<Bill> billsss = new ArrayList<Bill>();
//		if (list != null && !list.isEmpty()) {
//			for (Object[] objects : list) {
//				Bill bill = new Bill();
//				bill.setSoLuong(Integer.parseInt((objects[0]).toString()));
//				bill.setNguoiMua(Integer.parseInt((objects[1]).toString()));
//				billsss.add(bill);
//			}
//		}
//		model.addAttribute("bill", billsss);
//		return "bill/thongkesoluong";
//	}
}
