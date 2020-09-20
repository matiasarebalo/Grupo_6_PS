package G6.PS.Ecommerce.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//import javax.validation.Valid;
import javax.validation.Valid;

import G6.PS.Ecommerce.models.AtributoValorModel;
import G6.PS.Ecommerce.models.AtributosModel;
import G6.PS.Ecommerce.models.CategoriaModel;
import G6.PS.Ecommerce.models.ProductoModel;
import G6.PS.Ecommerce.models.SubCategoriaModel;
import G6.PS.Ecommerce.services.ICategoriaService;
import G6.PS.Ecommerce.services.IProductoService;
import G6.PS.Ecommerce.services.ISubCategoriaService;
import G6.PS.Ecommerce.converters.CategoriaConverter;
import G6.PS.Ecommerce.helpers.ViewRouteHelper;

@Controller
@RequestMapping("/productos")
public class ProductoController {

	@Autowired
	@Qualifier("productoService")
	private IProductoService productoService;

	@Autowired
	@Qualifier("categoriaService")
	private ICategoriaService categoriaService;

	@Autowired
	@Qualifier("subCategoriaService")
	private ISubCategoriaService subCategoriaService;

	@SuppressWarnings("null")
	@GetMapping("/new")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.FORM);

		// compruebo si se logueo el admin y en tal caso muestro el menu
		// correspondiente, el resto de la pagina permanece igual
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		System.out.println(roleString);

		boolean admin = false;
		if (roleString.equals("[ROLE_ADMIN]")) {
			admin = true;
		}
		mAV.addObject("admin", admin);
		
		List<CategoriaModel> categorias=  categoriaService.getAll();

		mAV.addObject("categorias", categorias);

		List<SubCategoriaModel> subCategorias=  subCategoriaService.getAll();

		mAV.addObject("subCategorias", subCategorias);
		
		mAV.addObject("categoria", new CategoriaModel());

		mAV.addObject("subCategoria", new SubCategoriaModel());

		mAV.addObject("producto", new ProductoModel());

		return mAV;
	}
	
	@GetMapping("/lista")
	public ModelAndView grillaProductos() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.GRILLA);

		// compruebo si se logueo el admin y en tal caso muestro el menu
		// correspondiente, el resto de la pagina permanece igual
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		System.out.println(roleString);

		boolean admin = false;
		if (roleString.equals("[ROLE_ADMIN]")) {
			admin = true;
		}
		mAV.addObject("admin", admin);

		CategoriaModel categoriaModel = new CategoriaModel(1, "Hombres");
		SubCategoriaModel subCategoriaModel = new SubCategoriaModel(1, "Remera", categoriaModel);
		AtributoValorModel atributoValorModel = new AtributoValorModel(1, "XL");
		AtributoValorModel atributoValorModel2 = new AtributoValorModel(2, "S");

		List<AtributosModel> atributos = new ArrayList<AtributosModel>();
		AtributosModel atributo = new AtributosModel(1, "Talle", atributoValorModel);
		atributos.add(atributo);

		ProductoModel producto = new ProductoModel(1, "Lisa 2020", "descripcionLarga", subCategoriaModel,"urlImagen", "sku", 1200, true, atributos);

		List<ProductoModel> productos = new ArrayList<ProductoModel>();
		productos.add(producto);

		mAV.addObject("productos", productos);
		mAV.addObject("producto", new ProductoModel());

		return mAV;
	}
	
	@PostMapping("/saveCategoria")
	public String saveCategoria(@Valid @ModelAttribute("categoria") CategoriaModel categoriaModel, BindingResult result,RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return ViewRouteHelper.FORM;

		}
		categoriaService.insertOrUpdate(categoriaModel);
		return ViewRouteHelper.FORM;

	}

	@PostMapping("/saveSubCategoria")
	public String saveSubCategoria(@Valid @ModelAttribute("subCategoria") SubCategoriaModel subCategoriaModel, BindingResult result,RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return ViewRouteHelper.FORM;

		}
		subCategoriaService.insertOrUpdate(subCategoriaModel);
		return ViewRouteHelper.FORM;

	}
	
	@PostMapping("/saveProducto")
	public String save(@Valid @ModelAttribute("producto") ProductoModel productoModel, BindingResult result,RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return ViewRouteHelper.FORM;

		}
		productoService.insertOrUpdate(productoModel);
		return ViewRouteHelper.FORM;

	}

	@PostMapping("/eliminar/{id}")
	public String delete(@PathVariable("id") int id) {
		productoService.delete(id);
		return "redirect:/productos/lista";
	}
}
