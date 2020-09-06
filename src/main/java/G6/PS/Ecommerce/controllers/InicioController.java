package G6.PS.Ecommerce.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


import G6.PS.Ecommerce.helpers.ViewRouteHelper;

@Controller
@RequestMapping("/")

public class InicioController {

	@GetMapping("/")
	public String index() {
		return "home/index";
	}
}
