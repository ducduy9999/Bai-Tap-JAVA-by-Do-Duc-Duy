package SDC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import SDC.service.MailService;

@Controller
public class HomeController {
	@Autowired
	MailService mailService;

	@GetMapping("/home")
	public String home() {
		mailService.sendEmail("test@gmail.com", "test thoi", "test thoi");
		return "home.html";
	}


}
