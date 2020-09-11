package G6.PS.Ecommerce.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import G6.PS.Ecommerce.helpers.ViewRouteHelper;

@Controller
public class LoginController {

    @GetMapping("/login")
	public String login(Model model,
						@RequestParam(name="error",required=false) String error,
						@RequestParam(name="logout", required=false) String logout) {
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		return "user/login";		
    }
    
    @GetMapping("/logout")
	public String logout(Model model) {
		return "user/logout";
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
		ModelAndView mav 	= new ModelAndView(ViewRouteHelper.login_ok);		
		String username 	= SecurityContextHolder.getContext().getAuthentication().getName();		
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();		
		
		switch(roleString)				{ 				  
		   case "[ROLE_ADMIN]":				      
			   System.out.println("cosas de admin");
			   break;
		   case "[ROLE_VENDEDOR]" :
			   System.out.println("cosas de vendedor");
			   break;
		   case "[ROLE_GERENTE]" :					       
               System.out.println("cosas de gerente");
			   break;
		   default : 		
		}

		return mav ;
	}
}
