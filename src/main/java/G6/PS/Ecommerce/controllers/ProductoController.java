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

import javax.validation.Valid;

import G6.PS.Ecommerce.models.AtributoValorModel;
import G6.PS.Ecommerce.models.AtributosModel;
import G6.PS.Ecommerce.models.CategoriaModel;
import G6.PS.Ecommerce.models.ProductoModel;
import G6.PS.Ecommerce.models.SubCategoriaModel;
import G6.PS.Ecommerce.services.IAtributoValorService;
import G6.PS.Ecommerce.services.IAtributosService;
import G6.PS.Ecommerce.services.ICategoriaService;
import G6.PS.Ecommerce.services.IProductoService;
import G6.PS.Ecommerce.services.ISubCategoriaService;
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

	@Autowired
	@Qualifier("atributoValorService")
	private IAtributoValorService atributoValorService;
	
	
	@GetMapping("/newCategoria")
	public ModelAndView newCategoria() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.FORM);
		
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		boolean admin = false;
		if (roleString.equals("[ROLE_ADMIN]")) {
			admin = true;
		}
		mAV.addObject("admin", admin);
		mAV.addObject("step", 1);
		List<CategoriaModel> categorias = categoriaService.getAll();
		mAV.addObject("categorias", categorias);
		mAV.addObject("categoria", new CategoriaModel() );
		return mAV;
	}

	@GetMapping("/newSubCategoria/{id}")
	public ModelAndView newSubCategoria(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.FORM);
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		boolean admin = false;
		if (roleString.equals("[ROLE_ADMIN]")) {
			admin = true;
		}
		mAV.addObject("admin", admin);
		mAV.addObject("step", 2);

		CategoriaModel categoria = categoriaService.listarId(id);
		SubCategoriaModel newSubcategoria = new SubCategoriaModel();
		newSubcategoria.setCategoria(categoria);
		List<SubCategoriaModel> subCategorias = subCategoriaService.getAll();
		mAV.addObject("subCategorias", subCategorias);
		mAV.addObject("subCategoria", newSubcategoria);
		
		return mAV;
	}
	
	@GetMapping("/newProducto/{id}")
	public ModelAndView newProducto(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.FORM);
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		System.out.println(roleString);
		boolean admin = false;
		if (roleString.equals("[ROLE_ADMIN]")) {
			admin = true;
		}
		mAV.addObject("admin", admin);
		mAV.addObject("step", 3);

		ProductoModel newProducto = new ProductoModel();
		newProducto.setSubCategoria(subCategoriaService.listarId(id));

		mAV.addObject("producto", newProducto);
		return mAV;
	}

	
	@GetMapping("/newAtributo/{id}")
	public ModelAndView newAtributo(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.FORM);
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		System.out.println(roleString);
		boolean admin = false;
		if (roleString.equals("[ROLE_ADMIN]")) {
			admin = true;
		}
		AtributosModel newAtributo = new AtributosModel();
		newAtributo.setProducto(productoService.listarId(id));

		AtributoValorModel atributoValorModel = new AtributoValorModel();
		newAtributo.setAtributoValor(atributoValorModel);
		mAV.addObject("admin", admin);
		mAV.addObject("step", 4);
		mAV.addObject("atributo", newAtributo);
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

		List<ProductoModel> productoModel = new ArrayList<ProductoModel>();
		subCategoriaModel.setProductoModel(productoModel);
		SubCategoriaModel sC =subCategoriaService.insertOrUpdate(subCategoriaModel);
		return "redirect:/productos/newProducto/" + sC.getId();

	}

	@PostMapping("/saveProducto")
	public String saveProducto(@Valid @ModelAttribute("producto") ProductoModel productoModel, BindingResult result,
			RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return ViewRouteHelper.FORM_PRODUCTO;

		}
		productoModel.setSubCategoria(subCategoriaService.listarId(productoModel.getSubCategoria().getId()));
		List<AtributosModel> atributosModels = new ArrayList<AtributosModel>();
		productoModel.setProdAtributos(atributosModels);
		ProductoModel productos = new ProductoModel(0, "descripcionCorta", "descripcionLarga",
		subCategoriaService.listarId(productoModel.getSubCategoria().getId()), "urlImagen", "sku", 1200, true, atributosModels);
		ProductoModel pM=	productoService.insertOrUpdate(productos);
			return "redirect:/productos/newAtributo/" + pM.getId();
	}
	
	@PostMapping("/saveAtributo")
	public String saveAtributo(@Valid @ModelAttribute("producto") AtributosModel atributosModel, BindingResult result,
			RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return ViewRouteHelper.FORM_PRODUCTO;

		}
		atributosModel.setProducto(productoService.listarId(atributosModel.getProducto().getId()));
		atributoValorService.insertOrUpdate(atributosModel.getAtributoValor());
		atributosService.insertOrUpdate(atributosModel);
		return "redirect:/";
	}	
	
}
