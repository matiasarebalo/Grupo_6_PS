package G6.PS.Ecommerce.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import G6.PS.Ecommerce.entities.Producto;
import G6.PS.Ecommerce.helpers.ViewRouteHelper;
import G6.PS.Ecommerce.models.AtributoValorModel;
import G6.PS.Ecommerce.models.AtributosModel;
import G6.PS.Ecommerce.models.CategoriaModel;
import G6.PS.Ecommerce.models.ProductoModel;
import G6.PS.Ecommerce.models.SubCategoriaModel;
import G6.PS.Ecommerce.repositories.IProductoRepository;
import G6.PS.Ecommerce.services.IProductoService;

@Controller
@RequestMapping("/")

public class InicioController {
	
	@Autowired
	@Qualifier("productoService")
	private IProductoService productoService;
	
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView("vendedor/crud-producto");
		
		//compruebo si se logueo el admin y en tal caso muestro el menu correspondiente, el resto de la pagina permanece igual
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        System.out.println(roleString);

		boolean admin = false;
		if(roleString.equals("[ROLE_ADMIN]")) {admin=true;}
		mAV.addObject("admin", admin);

		
		
		CategoriaModel categoriaModel = new CategoriaModel(1, "Hombres");
		SubCategoriaModel subCategoriaModel = new SubCategoriaModel(1, "Remera", categoriaModel);

		AtributoValorModel atributoValorModel = new AtributoValorModel(1, "XL");
		AtributoValorModel atributoValorModel2 = new AtributoValorModel(2, "S");

		List<AtributosModel> atributos = new ArrayList<AtributosModel>();
		AtributosModel atributo = new AtributosModel(1, "Talle", atributoValorModel);
		atributos.add(atributo);

		ProductoModel productos = new ProductoModel(1, "descripcionCorta", "descripcionLarga",
		subCategoriaModel, "urlImagen", "sku", 1200, true, atributos);
		
		mAV.addObject("productos",productos);
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
