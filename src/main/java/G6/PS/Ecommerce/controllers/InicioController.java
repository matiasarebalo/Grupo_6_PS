package G6.PS.Ecommerce.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import G6.PS.Ecommerce.helpers.ViewRouteHelper;

@Controller
@RequestMapping("/")

public class InicioController {

	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.INDEX);
		
		//compruebo si se logueo el admin y en tal caso muestro el menu correspondiente, el resto de la pagina permanece igual
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        System.out.println(roleString);

		boolean admin = false;
		if(roleString =="[ROLE_ANONYMOUS]") {admin=false;}
		mAV.addObject("admin", admin);


		return mAV;
	}
	
	@GetMapping("pag_institucional")
	public String pag_institucional() {
		return "home/pagina_institucional";
	}
}
