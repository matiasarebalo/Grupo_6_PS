package G6.PS.Ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import G6.PS.Ecommerce.helpers.ViewRouteHelper;
import G6.PS.Ecommerce.models.CategoriaModel;
import G6.PS.Ecommerce.models.ProductoModel;
import G6.PS.Ecommerce.services.ICategoriaService;
import G6.PS.Ecommerce.services.IProductoService;

@Controller
@RequestMapping("/")

public class InicioController {
	
	@Autowired
	@Qualifier("productoService")
	private IProductoService productoService;
	
	@Autowired
	@Qualifier("categoriaService")
	private ICategoriaService categoriaService;
	
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.INDEX);
		
		//compruebo si se logueo el admin y en tal caso muestro el menu correspondiente, el resto de la pagina permanece igual
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        System.out.println(roleString);

		boolean admin = false;
		if(roleString.equals("[ROLE_ADMIN]")) {admin=true;}
		mAV.addObject("admin", admin);
		
		List<ProductoModel> productos = productoService.findDestacados();
		if(productos!= null){
			mAV.addObject("productos", productos);
		}
		
		List<CategoriaModel> categorias = categoriaService.getAll();
		if (categorias != null) {
			mAV.addObject("categorias", categorias);
		}
		
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

    @RequestMapping(value = "productoSearch", method = RequestMethod.GET)
	public ModelAndView search(@Param("productoSearch") String productoSearch) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.INDEX);
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        System.out.println(roleString);

		boolean admin = false;
		if(roleString.equals("[ROLE_ADMIN]")) {admin=true;}
		mAV.addObject("admin", admin);

		List<ProductoModel> productos = productoService.searchByProducto(productoSearch);
		if(productos!= null){
			mAV.addObject("productos", productos);
		}

		return mAV;
	}
}
