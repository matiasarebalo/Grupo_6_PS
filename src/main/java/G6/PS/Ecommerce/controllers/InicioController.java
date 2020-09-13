package G6.PS.Ecommerce.controllers;

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
		if(roleString.equals("[ROLE_ADMIN]")) {admin=true;}
		mAV.addObject("admin", admin);


		return mAV;
	}
	
	@GetMapping("institucional")
	public ModelAndView pag_institucional() {
	ModelAndView mAV = new ModelAndView(ViewRouteHelper.INSTITUCIONAL);
		
		//compruebo si se logueo el admin y en tal caso muestro el menu correspondiente, el resto de la pagina permanece igual
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        System.out.println(roleString);

		boolean admin = false;
		if(roleString.equals("[ROLE_ADMIN]")) {admin=true;}
		mAV.addObject("admin", admin);


		return mAV;
	}
}
