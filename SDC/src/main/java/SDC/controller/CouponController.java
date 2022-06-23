package SDC.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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

import SDC.dto.Coupon;
import SDC.repository.CouponRepo;

@Controller
@RequestMapping("/coupon")
public class CouponController {
	private static Logger logger = LoggerFactory.getLogger(CouponController.class);
	@Autowired // DI
	CouponRepo couponRepo;// null

	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("coupon", new Coupon());

		return "coupon/create";
	}

	@PostMapping("/create")
	public String create(@ModelAttribute("coupon") @Valid Coupon coupon,
			@RequestParam("expired_Date") String expired_Date, BindingResult bindingResult) throws ParseException {

		if (bindingResult.hasErrors()) {
			return "coupon/create";
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		coupon.setExpiredDate(dateFormat.parse(expired_Date));

		couponRepo.save(coupon);

		return "redirect:/coupon/search";
	}

	@GetMapping("/update") // ?id=12
	public String update(@RequestParam("id") int id, Model model) {
		Coupon coupon = couponRepo.getById(id);
		model.addAttribute("coupon", coupon);
		return "coupon/update.html";
	}

	@PostMapping("/update")
	public String update(@ModelAttribute Coupon coupon) {
		Coupon oldOne = couponRepo.getById(coupon.getId());
		;

		oldOne.setName(coupon.getName());

		couponRepo.save(oldOne);
		return "redirect:/coupon/search";
	}

	@GetMapping("/delete") // ?id=12
	public String delete(@RequestParam(value = "id") int id) {
		couponRepo.deleteById(id);
		return "redirect:/coupon/search";// url maping
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
			Page<Coupon> pageCoupon = couponRepo.searchAll("%" + name + "%", pageable);

			model.addAttribute("list", pageCoupon.toList());
			model.addAttribute("totalPage", pageCoupon.getTotalPages());
		} else if (id != null) {
			Coupon coupon = couponRepo.findById(id).orElse(null);
			if (coupon != null) {
				model.addAttribute("list", Arrays.asList(coupon));
			} else
				// log
				logger.info("Id not found");

			model.addAttribute("totalPage", 0);
		} else {
			Page<Coupon> pageCoupon = couponRepo.findAll(pageable);

			model.addAttribute("list", pageCoupon.toList());
			model.addAttribute("totalPage", pageCoupon.getTotalPages());
		}

		model.addAttribute("page", page);
		model.addAttribute("size", size);
		model.addAttribute("name", name == null ? "" : name);
		model.addAttribute("id", id == null ? "" : id);
		model.addAttribute("sortBy", sortBy == null ? "" : sortBy);
		return "coupon/search";
	}

}
