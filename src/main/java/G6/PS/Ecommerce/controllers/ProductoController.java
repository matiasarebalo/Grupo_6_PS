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
import G6.PS.Ecommerce.services.IAtributosService;
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

	@Autowired
	@Qualifier("atributosService")
	private IAtributosService atributosService;
	
	
	@GetMapping("/newCategoria")
	public ModelAndView newCategoria() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.FORM_CATEGORIA);
		// compruebo si se logueo el admin y en tal caso muestro el menu
		// correspondiente, el resto de la pagina permanece igual
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		boolean admin = false;
		if (roleString.equals("[ROLE_ADMIN]")) {
			admin = true;
		}
		mAV.addObject("admin", admin);
		List<CategoriaModel> categorias = categoriaService.getAll();
		mAV.addObject("categorias", categorias);
		mAV.addObject("categoria", new CategoriaModel() );
		return mAV;
	}

	@GetMapping("/newSubCategoria/{id}")
	public ModelAndView newSubCategoria(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.FORM_SUBCATEGORIA);
		// compruebo si se logueo el admin y en tal caso muestro el menu
		// correspondiente, el resto de la pagina permanece igual
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		boolean admin = false;
		if (roleString.equals("[ROLE_ADMIN]")) {
			admin = true;
		}
		mAV.addObject("admin", admin);
		mAV.addObject("c_id", id);
		List<SubCategoriaModel> subCategorias = subCategoriaService.getAll();
		mAV.addObject("subCategorias", subCategorias);
		mAV.addObject("subCategoria", new SubCategoriaModel());
		return mAV;
	}
	
	@GetMapping("/newProducto/{id}")
	public ModelAndView newProducto(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.FORM_PRODUCTO);
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		System.out.println(roleString);
		boolean admin = false;
		if (roleString.equals("[ROLE_ADMIN]")) {
			admin = true;
		}
		mAV.addObject("admin", admin);
		mAV.addObject("sC_id", id);
		mAV.addObject("producto", new ProductoModel());
		return mAV;
	}

	
	@GetMapping("/newAtributo/{id}")
	public ModelAndView newAtributo(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.FORM_PRODUCTO);
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		System.out.println(roleString);
		boolean admin = false;
		if (roleString.equals("[ROLE_ADMIN]")) {
			admin = true;
		}
		mAV.addObject("admin", admin);
		mAV.addObject("pId", id);
		mAV.addObject("atributos", new AtributosModel());
		return mAV;
	}

	@PostMapping("/saveCategoria")
	public String saveCategoria(@Valid @ModelAttribute("categoria") CategoriaModel categoriaModel, BindingResult result,
			RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return ViewRouteHelper.FORM_CATEGORIA;
		}
		CategoriaModel cM = categoriaService.insertOrUpdate(categoriaModel);
		return "redirect:/productos/newSubCategoria/" + cM.getId();
	}

	@PostMapping("/saveSubCategoria")
	public String saveSubCategoria(@Valid @ModelAttribute("subCategoria") SubCategoriaModel subCategoriaModel,
			BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return ViewRouteHelper.FORM_SUBCATEGORIA;

		}
		subCategoriaModel.setCategoria(categoriaService.listarId(subCategoriaModel.getCategoria().getId()));
		SubCategoriaModel sC =subCategoriaService.insertOrUpdate(subCategoriaModel);
		return "redirect:/productos/newProducto/" + sC.getId();

	}

	@PostMapping("/saveProducto")
	public String saveProducto(@Valid @ModelAttribute("producto") ProductoModel productoModel, BindingResult result,
			RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return ViewRouteHelper.FORM_PRODUCTO;

		}
	productoModel.setSubCategoriaModel(subCategoriaService.listarId(productoModel.getSubCategoria().getId()));	
	ProductoModel pM=	productoService.insertOrUpdate(productoModel);
		return "redirect:/productos/newAtributo/" + pM.getId();
	}
	
	@PostMapping("/saveAtributo")
	public String saveAtributo(@Valid @ModelAttribute("producto") AtributosModel atributosModel, BindingResult result,
			RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return ViewRouteHelper.FORM_PRODUCTO;

		}
	atributosModel.setProducto(productoService.listarId(atributosModel.getProducto().getId()));
		atributosService.insertOrUpdate(atributosModel);
		return "redirect:/";
	}	
	
}
