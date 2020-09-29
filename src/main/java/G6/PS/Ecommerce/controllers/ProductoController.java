package G6.PS.Ecommerce.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

import G6.PS.Ecommerce.models.AtributosModel;
import G6.PS.Ecommerce.models.CategoriaModel;
import G6.PS.Ecommerce.models.ProductoModel;
import G6.PS.Ecommerce.models.SubCategoriaModel;
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
		List<SubCategoriaModel> subCategorias = subCategoriaService.getSubcategoriasByCategoria(categoria.getId());
		if(subCategorias.isEmpty()) {
			mAV.addObject("nuevo", 0);
		}else{
			mAV.addObject("subCategorias", subCategorias);
			mAV.addObject("nuevo", 1);
		}
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
		
		mAV.addObject("admin", admin);
		mAV.addObject("step", 4);
		mAV.addObject("atributo", newAtributo);
		return mAV;
	}

	@PostMapping("/saveCategoria")
	public String saveCategoria(@Valid @ModelAttribute("categoria") CategoriaModel categoriaModel, BindingResult result,
			RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return ViewRouteHelper.FORM;
		}
		CategoriaModel cM = new CategoriaModel();
		if(categoriaModel.getCategoria().isEmpty()){
			cM = categoriaService.listarId(categoriaModel.getId());
		}else{
			categoriaModel.setId(0);
			cM = categoriaService.insertOrUpdate(categoriaModel);
		}
		return "redirect:/productos/newSubCategoria/" + cM.getId();
	}

	@PostMapping("/saveSubCategoria")
	public String saveSubCategoria(@Valid @ModelAttribute("subCategoria") SubCategoriaModel subCategoriaModel,
			BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return ViewRouteHelper.FORM;

		}
		subCategoriaModel.setCategoria(categoriaService.listarId(subCategoriaModel.getCategoria().getId()));

		List<ProductoModel> productoModel = new ArrayList<ProductoModel>();
		subCategoriaModel.setProductoModel(productoModel);

		SubCategoriaModel sC = new SubCategoriaModel();

		if(subCategoriaModel.getSubCategoria().isEmpty()){
			sC = subCategoriaService.listarId(subCategoriaModel.getId());
		}else{
			subCategoriaModel.setId(0);
			sC =subCategoriaService.insertOrUpdate(subCategoriaModel);
		}

		return "redirect:/productos/newProducto/" + sC.getId();

	}

	@PostMapping("/saveProducto")
	public String saveProducto(@Valid @ModelAttribute("producto") ProductoModel productoModel, BindingResult result,
			RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return ViewRouteHelper.FORM;

		}
		productoModel.setSubCategoria(subCategoriaService.listarId(productoModel.getSubCategoria().getId()));
		productoModel.setUrlImagen("urlImagen");
		ProductoModel pM=	productoService.insertOrUpdate(productoModel);
			return "redirect:/productos/newAtributo/" + pM.getId();
	}
	
	
	@PostMapping("/saveAtributo")
	public String saveAtributo(@Valid @ModelAttribute("producto") AtributosModel atributosModel, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return ViewRouteHelper.FORM;
		}
		atributosModel.setProducto(productoService.listarId(atributosModel.getProducto().getId()));
		atributosService.insertOrUpdate(atributosModel);
		return "redirect:/";
	}	

	@GetMapping("/articulo/{id}")
	public ModelAndView productoUnico(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.ARTICULO_UNICO);
		// compruebo si se logueo el admin y en tal caso muestro el menu
		// correspondiente, el resto de la pagina permanece igual
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		boolean admin = false;
		if (roleString.equals("[ROLE_ADMIN]")) {
			admin = true;
		}
		mAV.addObject("admin", admin);

		ProductoModel articulo = productoService.listarId(id);
		mAV.addObject("producto", articulo);
		
		//List<ProductoModel> relacionados = productoService.findBySubCategoria(articulo.getSubCategoriaModel().getId());
		
		List<ProductoModel> relacionados = productoService.findRelacionados(articulo.getId(),articulo.getSubCategoriaModel().getId());
		mAV.addObject("relacionados", relacionados);
		return mAV;
	}
	
	@GetMapping("/categoria/{id}")
	public ModelAndView productoCategoria(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.CATALOGO_CATEGORIA);
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		boolean admin = false;
		if (roleString.equals("[ROLE_ADMIN]")) {
			admin = true;
		}
		
		List<CategoriaModel> categorias = categoriaService.getAll();
		if (categorias != null) {
			mAV.addObject("categorias", categorias);
		}
		
		mAV.addObject("admin", admin);
		List<ProductoModel> productos = productoService.findByCategoria(id);
		if(productos != null) {
			mAV.addObject("productos", productos);
		}
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

		List<ProductoModel> productos = productoService.findDestacados();
		mAV.addObject("productos", productos);

		return mAV;
	}
	
	
	@GetMapping("/eliminar/{id}")
	public RedirectView delete(Model model, @PathVariable("id") int id, RedirectAttributes redirect) {
		//no eliminar productos con envios en curso.
		List<ProductoModel> p = productoService.findDependency(id);
		if (p.isEmpty()) {
			productoService.delete(id);
			return new RedirectView(ViewRouteHelper.GRILLA);
		} else

			redirect.addFlashAttribute("popUp", "error");
		return new RedirectView(ViewRouteHelper.GRILLA);
	}
}
