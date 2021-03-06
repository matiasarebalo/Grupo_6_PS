package G6.PS.Ecommerce.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.io.UnsupportedEncodingException;
import java.net.URLConnection;

import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;

import G6.PS.Ecommerce.models.AtributosModel;
import G6.PS.Ecommerce.models.CategoriaModel;
import G6.PS.Ecommerce.models.ComentarioModel;
import G6.PS.Ecommerce.models.EmbalajeModel;
import G6.PS.Ecommerce.models.PedidoModel;
import G6.PS.Ecommerce.models.ProductoModel;
import G6.PS.Ecommerce.models.SubCategoriaModel;
import G6.PS.Ecommerce.services.IAtributosService;
import G6.PS.Ecommerce.services.ICategoriaService;
import G6.PS.Ecommerce.services.IComentarioService;
import G6.PS.Ecommerce.services.IEmbalajeService;
import G6.PS.Ecommerce.services.IPedidoService;
import G6.PS.Ecommerce.services.IProductoService;
import G6.PS.Ecommerce.services.ISubCategoriaService;
import G6.PS.Ecommerce.helpers.ExcelHelper;
import G6.PS.Ecommerce.helpers.ViewRouteHelper;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessageHelper;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

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
	@Qualifier("pedidoService")
	private IPedidoService pedidoService;

	@Autowired
	@Qualifier("comentarioService")
	private IComentarioService comentarioService;
	
		

	@Autowired
	@Qualifier("embalajeService")
	private IEmbalajeService embalajeService;

	@Autowired
    private JavaMailSender mailSender;

	@GetMapping("/editar/{id}")
	public ModelAndView editCategoria(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.EDIT);

		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		boolean admin = false;
		if (roleString.equals("[ROLE_ADMIN]")) {
			admin = true;
		}
		mAV.addObject("admin", admin);
		mAV.addObject("step", 1);

		ProductoModel producto = productoService.listarId(id);
		producto.getSubCategoria().getCategoria().setSubcategoria(
				subCategoriaService.getSubcategoriasByCategoria(producto.getSubCategoria().getCategoria().getId()));
		producto.setProdAtributos(atributosService.getByProducto(id));

		mAV.addObject("nuevo", 1);
		mAV.addObject("categorias", categoriaService.getAll());
		mAV.addObject("atributos", atributosService.getByProducto(id));

		mAV.addObject("producto", producto);
		return mAV;
	}

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
		mAV.addObject("categoria", new CategoriaModel());
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
		if (subCategorias.isEmpty()) {
			mAV.addObject("nuevo", 0);
		} else {
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
		if (categoriaModel.getCategoria().isEmpty()) {
			cM = categoriaService.listarId(categoriaModel.getId());
		} else {
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
		CategoriaModel categoria = categoriaService.listarId(subCategoriaModel.getCategoria().getId());
		subCategoriaModel.setCategoria(categoria);

		List<ProductoModel> productoModel = new ArrayList<ProductoModel>();
		subCategoriaModel.setProductoModel(productoModel);

		SubCategoriaModel sC = new SubCategoriaModel();

		if (subCategoriaModel.getSubCategoria().isEmpty()) {
			sC = subCategoriaService.listarId(subCategoriaModel.getId());
		} else {
			subCategoriaModel.setId(0);
			sC = subCategoriaService.insertOrUpdate(subCategoriaModel);
		}

		return "redirect:/productos/newProducto/" + sC.getId();

	}

	@PostMapping("/saveProducto")
	public String saveProducto(@Valid @ModelAttribute("producto") ProductoModel productoModel,
			@RequestParam(name = "file", required = false) MultipartFile foto, BindingResult result,
			RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return ViewRouteHelper.FORM;

		}
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(Paths.get("")
        .toAbsolutePath()
        .toString());
		stringBuilder.append(File.separator);		
		stringBuilder.append("src");
		stringBuilder.append(File.separator);
		stringBuilder.append("main");
		stringBuilder.append(File.separator);
		stringBuilder.append("resources");
		stringBuilder.append(File.separator);
		stringBuilder.append("static");
		stringBuilder.append(File.separator);
		stringBuilder.append("img");
		stringBuilder.append(File.separator);
		stringBuilder.append(foto.getOriginalFilename());

		byte[] bytes = null;
		try {
			bytes = foto.getBytes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Path path = Paths.get(stringBuilder.toString());
		try {
			Files.write(path, bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		productoModel.setUrlImagen("/img/" + foto.getOriginalFilename());
		productoModel.setSubCategoria(subCategoriaService.listarId(productoModel.getSubCategoria().getId()));
		ProductoModel pM=	productoService.insertOrUpdate(productoModel);
		if(pM!=null) {	
		return "redirect:/productos/newAtributo/" + pM.getId();
		}else {
		redirect.addAttribute("message", "error al agregar el producto");	
		return	"redirect:/productos/newProducto/"+productoModel.getSubCategoria().getId();
		
		}
	}
	
	
	@PostMapping("/saveAtributo")
	public ModelAndView saveAtributo(@Valid @ModelAttribute("producto") AtributosModel atributosModel, BindingResult result, RedirectAttributes redirect) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.FORM);
		if (result.hasErrors()) {
			return  mAV = new ModelAndView(ViewRouteHelper.FORM);
		}
		ProductoModel producto = productoService.listarId(atributosModel.getProducto().getId());

		atributosModel.setProducto(producto);
		AtributosModel newAtributo = new AtributosModel();
		newAtributo.setProducto(producto);

		atributosService.insertOrUpdate(atributosModel);

		mAV.addObject("step", 4);
		mAV.addObject("atributo", newAtributo);
		return mAV;
	}	

	@GetMapping("/articulo/{id}")
	public ModelAndView productoUnico(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.ARTICULO_UNICO);
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		boolean admin = false;
		if (roleString.equals("[ROLE_ADMIN]")) {
			admin = true;
		}
		mAV.addObject("admin", admin);

		ProductoModel articulo = productoService.listarId(id);
		mAV.addObject("producto", articulo);
		
		List<ProductoModel> relacionados = productoService.findRelacionados(articulo.getId(),articulo.getSubCategoria().getId());
		mAV.addObject("relacionados", relacionados);
		ComentarioModel comentarioModel = new ComentarioModel();
		mAV.addObject("comentario", comentarioModel);

		List<ComentarioModel> comentarios = comentarioService.getByProducto(id);
		mAV.addObject("comentarios", comentarios);
		
		double estrellas = 0;
		for (ComentarioModel c : comentarios) {
			estrellas += c.getValoracion();
		}
		estrellas = estrellas / comentarios.size();

		mAV.addObject("estrellas", estrellas);

		List<CategoriaModel> categorias = categoriaService.getAll();
		if (categorias != null) {
			mAV.addObject("categorias", categorias);
		}

		return mAV;
	}

	@PostMapping("/saveEdit")
	public String SaveEdit(@Valid @ModelAttribute("producto") ProductoModel productoModel, BindingResult result,
			RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return ViewRouteHelper.FORM;

		}
		ProductoModel pm = productoService.listarId(productoModel.getId());
		productoModel.setUrlImagen(pm.getUrlImagen());
		productoService.insertOrUpdate(productoModel);
		return "redirect:/productos/lista/";
	}
	
	
	@GetMapping("/categoria/")
	public ModelAndView productoCategoria(@RequestParam int id,@RequestParam Map<String, Object> params,@RequestParam(defaultValue= "id") String atributo, Model model) {
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
		List<SubCategoriaModel> subCategorias = subCategoriaService.getSubcategoriasByCategoria(id);
		//List<SubCategoriaModel> subCategorias = subCategoriaService.getAll();
		if (subCategorias != null) {
			mAV.addObject("subcategorias", subCategorias);
		}

		mAV.addObject("admin", admin);
		
		//paginacion init
		int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
		PageRequest pageRequest = PageRequest.of(page, 3, Sort.by(atributo));
		Page<ProductoModel> pageProducto = productoService.findByCategoria(pageRequest,id);
		int totalPage = pageProducto.getTotalPages();
		if (totalPage > 0) {
			List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			model.addAttribute("pages", pages);

		}
		model.addAttribute("current", page + 1);
		model.addAttribute("next", page + 2);
		model.addAttribute("prev", page);
		model.addAttribute("last", totalPage);
		model.addAttribute("list", pageProducto.getContent());
		model.addAttribute("tam", pageProducto.getContent().size());
		model.addAttribute("cat_id", id);
		model.addAttribute("categoria_elegida", categoriaService.listarId(id));

		//paginacion end
		//		List<ProductoModel> productos = productoService.findByCategoria(id);
		//		if(productos != null) {
		//			mAV.addObject("productos", productos);
		//		}
		return mAV;
	}

	@GetMapping("/subcategoria/")
	public ModelAndView productoSubCategoria(@RequestParam int id,@RequestParam Map<String, Object> params,@RequestParam(defaultValue= "id") String atributo, Model model) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.CATALOGO_SUBCATEGORIA);
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		boolean admin = false;
		if (roleString.equals("[ROLE_ADMIN]")) {
			admin = true;
		}
		
		List<CategoriaModel> categorias = categoriaService.getAll();
		if (categorias != null) {
			mAV.addObject("categorias", categorias);
		}

		SubCategoriaModel sub = subCategoriaService.listarId(id);

		List<SubCategoriaModel> subCategorias = subCategoriaService.getSubcategoriasByCategoria(sub.getCategoria().getId());
		mAV.addObject("subCategorias", subCategorias);

		sub.getCategoria().getSubcategoria();

		mAV.addObject("admin", admin);
		
		//paginacion init
		int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
		PageRequest pageRequest = PageRequest.of(page, 3, Sort.by(atributo));
		Page<ProductoModel> pageProducto = productoService.findBySubCategoria(pageRequest,id);
			
		int totalPage = pageProducto.getTotalPages();
		if (totalPage > 0) {
			List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			model.addAttribute("pages", pages);

		}
		
		model.addAttribute("current", page + 1);
		model.addAttribute("next", page + 2);
		model.addAttribute("prev", page);
		model.addAttribute("last", totalPage);
		model.addAttribute("list", pageProducto.getContent());
		model.addAttribute("tam", pageProducto.getContent().size());
		model.addAttribute("scat_id", id);
		model.addAttribute("subcategoria_elegida", subCategoriaService.listarId(id));
		
		mAV.addObject("subcategoria_elegida", subCategoriaService.listarId(id));
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

		List<ProductoModel> productos = productoService.getAll();
		mAV.addObject("productos", productos);

		return mAV;
	}
	
	
	@GetMapping("/eliminar/{id}")
	public String delete(Model model, @PathVariable("id") int id, RedirectAttributes redirect) {
		//no eliminar productos con envios en curso.
		// List<ProductoModel> p = productoService.findDependency(id);
		// if (p.isEmpty()) {
		// 	productoService.delete(id);
		// 	return new RedirectView(ViewRouteHelper.GRILLA);
		// } else

		productoService.delete(id);
		
		return "redirect:/productos/lista/";
	}

	
	@PostMapping("/newComentario/{id}")
	public String NewComentario(@PathVariable("id") int id, @Valid @ModelAttribute("comentario") ComentarioModel comentarioModel, BindingResult result,RedirectAttributes redirect) {

		PedidoModel pedido = pedidoService.listarId(comentarioModel.getPedido().getId());
		
		if((pedido == null) || (pedido.getProducto().getId() != id)){
			redirect.addFlashAttribute("message", "Failed");
			return "redirect:/productos/articulo/" + id;
		}
		
		comentarioModel.setFechaComentario(Date.from(Instant.now()));
		comentarioModel.setPedido(pedido);
		comentarioService.insertOrUpdate(comentarioModel);


		return "redirect:/productos/articulo/" + id;
	}
	
	@GetMapping("/cargarExcel")
	public ModelAndView cargaExcel() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.EXCEL);
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		System.out.println(roleString);
		boolean admin = false;
		if (roleString.equals("[ROLE_ADMIN]")) {
			admin = true;
		}
		
		mAV.addObject("admin", admin);
		return mAV;
	}

	
	
	 @PostMapping("/upload")
	  public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirect) {
	    

	    if (ExcelHelper.hasExcelFormat(file)) {
	         productoService.saveAll(file);
				return "redirect:/productos/lista/";

	      } else {
				redirect.addFlashAttribute("message", "Failed");
				return "redirect:/productos/cargarExcel";

	      
	      }
	    }

	@GetMapping("/articulo/{id}/compra")
	public ModelAndView productoCompra(@PathVariable("id") int id, RedirectAttributes redirect) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.CHECKOUT);
		// compruebo si se logueo el admin y en tal caso muestro el menu
		// correspondiente, el resto de la pagina permanece igual
		
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		boolean admin = false;
		if (roleString.equals("[ROLE_ADMIN]")) {
			admin = true;
		}
		mAV.addObject("admin", admin);
		int codigo=0;
		ProductoModel articulo = productoService.listarId(id);
		mAV.addObject("codigo", codigo);
		mAV.addObject("prodId", articulo.getId());
		
		mAV.addObject("producto", articulo);
		PedidoModel pedido = new PedidoModel();
		EmbalajeModel embalaje = new EmbalajeModel();
		pedido.setEmbalaje(embalaje);
		mAV.addObject("pedido", pedido);

		List<EmbalajeModel> embalajes = embalajeService.getAll();

		mAV.addObject("embalajes", embalajes);

		List<CategoriaModel> categorias = categoriaService.getAll();
		if (categorias != null) {
			mAV.addObject("categorias", categorias);
		}
		
		return mAV;
	}
	
	@PostMapping("/articulo/compra/codDescuento/{id}")
	public ModelAndView descuento(@PathVariable("id") int id,@Valid @ModelAttribute("pedido") PedidoModel pedidoModel, BindingResult result,RedirectAttributes redirect) {

		ModelAndView mAV = new ModelAndView(ViewRouteHelper.CHECKOUT);
		
		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		boolean admin = false;
		if (roleString.equals("[ROLE_ADMIN]")) {
			admin = true;
		}
		mAV.addObject("admin", admin);

		int codigo=0;
		mAV.addObject("codigo", codigo);
		
		PedidoModel pedido = new PedidoModel();
		EmbalajeModel embalaje = new EmbalajeModel();
		pedido.setEmbalaje(embalaje);
		

		List<EmbalajeModel> embalajes = embalajeService.getAll();

		mAV.addObject("embalajes", embalajes);

		List<CategoriaModel> categorias = categoriaService.getAll();
		if (categorias != null) {
			mAV.addObject("categorias", categorias);
		}
		
		int codigoVerif = Integer.parseInt(pedidoModel.getCodigoPromocion());
		ProductoModel articulo = productoService.listarId(id);

		if(this.esPrimo(codigoVerif)) {
			pedido.setCodigoPromocion(pedidoModel.getCodigoPromocion());
			if(codigoVerif%2 == 0) 
			{
				articulo.setPrecio(articulo.getPrecio()-(articulo.getPrecio()*5/100));
			}
			else{
				articulo.setPrecio((articulo.getPrecio()-(articulo.getPrecio()*10/100)));
			}
		
		}else {
			mAV.addObject("mensaje", true);
		}
		mAV.addObject("pedido", pedido);
		mAV.addObject("prodId", articulo.getId());
		mAV.addObject("producto", articulo);

		return mAV;
	
	}
	

	@PostMapping("/articulo/{id}/compra/save")
	public ModelAndView remitoSave(@PathVariable("id") int id, @Valid @ModelAttribute("pedido") PedidoModel pedidoModel, BindingResult result,RedirectAttributes redirect)
			throws UnsupportedEncodingException, MessagingException {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.CHECKOUT);

		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		boolean admin = false;
		if (roleString.equals("[ROLE_ADMIN]")) {
			admin = true;
		}
		mAV.addObject("admin", admin);

		Map<String, Double> zonas = new HashMap<String, Double>();
		zonas.put("GBA", 500.00);
		zonas.put("Capital", 700.00);
		zonas.put("Provincia", 1000.00);
		double valorZona = 0;
		if(zonas.containsKey(pedidoModel.getZona())){

			for (String key : zonas.keySet()) {
				if(key.equalsIgnoreCase(pedidoModel.getZona())){
					valorZona = zonas.get(key);
				}
		 	}
		}
		ProductoModel articulo = productoService.listarId(id);
		EmbalajeModel embalaje = embalajeService.listarId(pedidoModel.getEmbalaje().getId());
		pedidoModel.setEmbalaje(embalaje);
		
		pedidoModel.setCosto(valorZona + embalaje.getPrecio());
		pedidoModel.setProducto(articulo);
		pedidoService.insertOrUpdate(pedidoModel);

		//resto descuento al precio del producto, segun codPromocion
		

		if(pedidoModel.getCodigoPromocion() != null){
			int codigoVerif = Integer.parseInt(pedidoModel.getCodigoPromocion());
			if( codigoVerif%2 == 0) 
			{
				articulo.setPrecio(articulo.getPrecio()-(articulo.getPrecio()*5/100));
			}
			else{
				articulo.setPrecio((articulo.getPrecio()-(articulo.getPrecio()*10/100)));
			}
		}

		//mandarMailCompra(pedidoModel);
		mAV.addObject("message", true);

		mAV.addObject("prodId", articulo.getId());
		articulo.setPrecio((float)pedidoModel.getCosto() + articulo.getPrecio());
		mAV.addObject("producto", articulo);
		mAV.addObject("pedido", pedidoModel);
		List<CategoriaModel> categorias = categoriaService.getAll();
		if (categorias != null) {
			mAV.addObject("categorias", categorias);
		}
		List<EmbalajeModel> embalajes = embalajeService.getAll();

		mAV.addObject("embalajes", embalajes);
		return mAV;
	}
	
	@GetMapping("/descuento/{id}")
	public ModelAndView paginaDescuento(Model model, @PathVariable("id") int id, RedirectAttributes redirect) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.DESCUENTO);

		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		boolean admin = false;
		if (roleString.equals("[ROLE_ADMIN]")) {
			admin = true;
		}
		mAV.addObject("admin", admin);

		List<ProductoModel> productos = productoService.getAll();
		mAV.addObject("productos", productos);
		 
		mAV.addObject("prod", productoService.listarId(id));
		return mAV;
	}

	@GetMapping("/descuentoRealizado/{id}")
	public ModelAndView descuentoRealizado(Model model, @PathVariable("id") int id, @Valid @ModelAttribute("producto") ProductoModel productoModel, RedirectAttributes redirect) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.DESCUENTO);

		String roleString = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		boolean admin = false;
		if (roleString.equals("[ROLE_ADMIN]")) {
			admin = true;
		}
		mAV.addObject("admin", admin);

		ProductoModel producto = productoService.listarId(id);
		producto.setPrecio( producto.getPrecio() - ((producto.getPrecio()*productoModel.getDescuento())/100) );	
		producto.setDescuento(productoModel.getDescuento());
		productoService.insertOrUpdate(producto);
		List<ProductoModel> productos = productoService.getAll();

		mAV.addObject("productos", productos);
		 
		mAV.addObject("prod", productoModel);
		return mAV;
	}

	private boolean esPrimo(int numero) { 
		Boolean esPrimoActual = true;
		if(numero<2)
		{
			esPrimoActual = false;
		}
		else
		{
			for(int x=2; x*x<=numero; x++)
			{
				if( numero%x==0 ){esPrimoActual = false;break;}
			}
		}
		return esPrimoActual;
	}

	private void mandarMailCompra(PedidoModel pedido)throws MessagingException, UnsupportedEncodingException{

        String firstName = pedido.getNombre_apellido();
        String email = pedido.getEmail();
		double costo = pedido.getProducto().getPrecio() + pedido.getCosto();
		int nroPedido = pedido.getId();
		String producto = pedido.getProducto().getDescripcionCorta();
		

        MimeMessage messageSend = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(messageSend);

        String mailSubject = firstName + " "  + "le notificamos la confirmacion de su compra";
        String mailContent = "<p><b>Enviado por:</b>" + " " +  "El Deporte On-line" + "</p>";
		mailContent += "<p><b>Email:</b>" + " " +  email + "</p>";
		mailContent += "<p><b>Producto:</b>" + " " +  producto + "</p>";
        mailContent += "<p><b>Precio total:</b>" + " $" +  costo + "</p>";
		mailContent += "<p><b>EL PAGO POR TU COMPRA REALIZADA EN NUESTRO NEGOCIO, CUYO NÚMERO DE PEDIDO ES " + nroPedido + ", FUE CONFIRMADO. PRONTO ENVIAREMOS TU PEDIDO.</b>"+ "</p>";

        helper.setFrom("el.deporte.online.g6@gmail.com", "El Deporte Online");
        helper.setTo(email);
        helper.setSubject(mailSubject);
        helper.setText(mailContent, true);

        mailSender.send(messageSend);
	}

	@RequestMapping("/template")
	public void downloadExcelTemplate(HttpServletRequest request, HttpServletResponse response) throws IOException {

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(Paths.get("")
        .toAbsolutePath()
        .toString());
		stringBuilder.append(File.separator);		
		stringBuilder.append("src");
		stringBuilder.append(File.separator);
		stringBuilder.append("main");
		stringBuilder.append(File.separator);
		stringBuilder.append("resources");
		stringBuilder.append(File.separator);
		stringBuilder.append("template.xlsx");

		File file = new File(stringBuilder.toString());
		if (file.exists()) {

			String mimeType = URLConnection.guessContentTypeFromName(file.getName());
			if (mimeType == null) {
				mimeType = "application/octet-stream";
			}

			response.setContentType(mimeType);

			response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));

			response.setContentLength((int) file.length());

			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

			FileCopyUtils.copy(inputStream, response.getOutputStream());

		}
	}
}
	

