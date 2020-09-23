package G6.PS.Ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import G6.PS.Ecommerce.helpers.ViewRouteHelper;
import G6.PS.Ecommerce.models.ProductoModel;
import G6.PS.Ecommerce.services.IProductoService;

@Controller
public class LoginController {
	@Autowired
	@Qualifier("productoService")
	private IProductoService productoService;
	
    @GetMapping("/login")
	public String login(Model model,
						@RequestParam(name="error",required=false) String error,
						@RequestParam(name="logout", required=false) String logout,
						RedirectAttributes redirect) {
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		return "/user/login";
    }
    
    @GetMapping("/logout")
	public String logout(Model model) {
		SecurityContextHolder.clearContext();
		return "/user/logout";
    }
	
	@PostMapping("/loginsuccess")
	public ModelAndView loginCheckPost()  {
		return loginCheckBase();
	}
		
	
	@GetMapping("/loginsuccess")
	public ModelAndView loginCheckGet() {
		return loginCheckBase();
	}

	@GetMapping("/home")
	public ModelAndView home() {
		return loginCheckBase();
	}

    public ModelAndView loginCheckBase() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.INDEX);
		
		//compruebo si se logueo el admin y en tal caso muestro el menu correspondiente, el resto de la pagina permanece igual
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        System.out.println(roleString);

		boolean admin = false;
		switch(roleString){ 				  
			case "[ROLE_ADMIN]":				      
				System.out.println("cosas de admin");
				admin = true;
				break;
		}

		mAV.addObject("admin", admin);

		List<ProductoModel> productos = productoService.findDestacados();
		
		if(productos!= null){
			mAV.addObject("productos", productos);
		}
		return mAV;
	}
}
