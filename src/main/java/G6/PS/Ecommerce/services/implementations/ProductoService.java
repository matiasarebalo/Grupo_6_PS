package G6.PS.Ecommerce.services.implementations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import G6.PS.Ecommerce.converters.ProductoConverter;
import G6.PS.Ecommerce.entities.Producto;
import G6.PS.Ecommerce.helpers.ExcelHelper;
import G6.PS.Ecommerce.models.AtributosModel;
import G6.PS.Ecommerce.models.CategoriaModel;
import G6.PS.Ecommerce.models.PedidoModel;
import G6.PS.Ecommerce.models.ProductoModel;
import G6.PS.Ecommerce.models.SubCategoriaModel;
import G6.PS.Ecommerce.repositories.IPedidoRepository;
import G6.PS.Ecommerce.repositories.IProductoRepository;
import G6.PS.Ecommerce.services.IAtributosService;
import G6.PS.Ecommerce.services.ICategoriaService;
import G6.PS.Ecommerce.services.IPedidoService;
import G6.PS.Ecommerce.services.IProductoService;
import G6.PS.Ecommerce.services.ISubCategoriaService;

@Service("productoService")
public class ProductoService implements IProductoService {

	@Autowired
	private IProductoRepository productoRepository;

	@Autowired
	@Qualifier("subCategoriaService")
	private ISubCategoriaService subCategoriaService;

	@Autowired
	@Qualifier("categoriaService")
	private ICategoriaService categoriaService;

	@Autowired
	@Qualifier("atributosService")
	private IAtributosService atributosService;

	@Autowired
	@Qualifier("pedidoService")
	private IPedidoService pedidoService;
	
	@Autowired
	private IPedidoRepository pedidoRepository;
	
	@Autowired
	@Qualifier("productoConverter")
	private ProductoConverter productoConverter;

	@Override
	public List<ProductoModel> getAll() {
		List<Producto> productos = productoRepository.findAll();
		List<ProductoModel> pM = new ArrayList<ProductoModel>();
		for (Producto p : productos) {
			ProductoModel pModel = productoConverter.entityToModel(p);
			pModel.setProdAtributos(atributosService.getByProducto(pModel.getId()));
			pM.add(calcularPrecioTachado(pModel));
			
		}
		return pM;
	}

	public List<ProductoModel> getAllVisibles() {
		List<ProductoModel> pM = new ArrayList<ProductoModel>();
		for (ProductoModel p : this.getAll()) {
			if (p.isVisible()) {
				pM.add(p);
			}
		}
		return pM;
	}

	@Override
	public ProductoModel insertOrUpdate(ProductoModel model) {
		model.setSku(this.generarSku(model));
		if (this.findBySku(model.getSku().toUpperCase()) == null) {
			Producto p = productoRepository.save(productoConverter.modelToEntity(model));

			return productoConverter.entityToModel(p);
		} else {
			return null;
		}
	}

	@Override
	public ProductoModel listarId(int id) {
		Producto producto = productoRepository.findById(id);
		if (producto == null) {
			return null;
		}
		ProductoModel pModel = productoConverter.entityToModel(producto);
		pModel.setProdAtributos(atributosService.getByProducto(id));
		return calcularPrecioTachado(pModel);
	}

	@Override
	public String delete(int id) {

		int sC = productoRepository.findById(id).getSubCategoria().getId();
		
		for (PedidoModel pm: pedidoService.getByProducto(id)) {
			pedidoRepository.deleteById(pm.getId());
		}
		atributosService.deleteDependencies(id);
		subCategoriaService.deleteDependencies(sC);
		productoRepository.deleteById(id);

		return "producto Eliminado";
	}

	public List<ProductoModel> findDestacados() {
		List<Producto> productos = productoRepository.findDestacados();
		List<ProductoModel> pM = new ArrayList<ProductoModel>();
		for (Producto p : productos) {
			ProductoModel pModel = productoConverter.entityToModel(p);
			pModel.setProdAtributos(atributosService.getByProducto(pModel.getId()));
			pM.add(calcularPrecioTachado(pModel));
		}
		return pM;
	}

	public List<ProductoModel> findBySubCategoria(int id) {
		List<Producto> productos = productoRepository.findBySubCategoria(id);
		List<ProductoModel> pM = new ArrayList<ProductoModel>();
		for (Producto p : productos) {
			ProductoModel pModel = productoConverter.entityToModel(p);
			pModel.setProdAtributos(atributosService.getByProducto(pModel.getId()));
			pM.add(calcularPrecioTachado(pModel));
		}
		return pM;
	}

	public List<ProductoModel> findRelacionados(int id_articulo, int id_sub) {
		List<Producto> productos = productoRepository.findRelacionados(id_articulo, id_sub);
		List<ProductoModel> pM = new ArrayList<ProductoModel>();
		for (Producto p : productos) {
			ProductoModel pModel = productoConverter.entityToModel(p);
			pModel.setProdAtributos(atributosService.getByProducto(pModel.getId()));
			pM.add(calcularPrecioTachado(pModel));
		}
		return pM;
	}
	
	public List<ProductoModel> findEnPromocion() {
		List<Producto> productos = productoRepository.findEnPromocion();
		List<ProductoModel> pM = new ArrayList<ProductoModel>();
		for (Producto p : productos) {
			ProductoModel pModel = productoConverter.entityToModel(p);
			pModel.setProdAtributos(atributosService.getByProducto(pModel.getId()));
			pM.add(calcularPrecioTachado(pModel));
		}
		return pM;
	}

	@Override
	public List<ProductoModel> findDependency(int id) {
		// cuando tengamos envios verificar que un producto no tenga envios realizado o
		// en curso antes de eliminarlos.
		// List<Producto> productos = productoRepository.findIfExist(id);
		// List<ProductoModel> pM = new ArrayList<ProductoModel>();
		//
		// for (Producto p : productos) {
		// pM.add(productoConverter.entityToModel(p));
		// }
		//
		// return pM;
		return null;
	}

	public List<ProductoModel> searchByProducto(String producto) {
		List<Producto> productos = productoRepository.findBydescripcionCortaContainingIgnoreCase(producto);
		List<ProductoModel> pM = new ArrayList<ProductoModel>();
		for (Producto p : productos) {
			ProductoModel pModel = productoConverter.entityToModel(p);
			pModel.setProdAtributos(atributosService.getByProducto(pModel.getId()));
			pM.add(calcularPrecioTachado(pModel));
		}
		return pM;
	}

	public List<ProductoModel> findByCategoria(int id) {
		List<Producto> productos = productoRepository.findByCategoria(id);
		List<ProductoModel> pM = new ArrayList<ProductoModel>();
		for (Producto p : productos) {
			ProductoModel pModel = productoConverter.entityToModel(p);
			pModel.setProdAtributos(atributosService.getByProducto(pModel.getId()));
			pM.add(calcularPrecioTachado(pModel));
		}
		return pM;
	}

	@Override
	public Page<ProductoModel> findByCategoria(Pageable pageable, int id) {
		Page<Producto> productos = productoRepository.findPageByCategoria(pageable, id);
		Page<ProductoModel> pages = productos.map(new Function<Producto, ProductoModel>() {
			public ProductoModel apply(Producto producto) {
				ProductoModel model = productoConverter.entityToModel(producto);
				model.setProdAtributos(atributosService.getByProducto(model.getId()));
				return calcularPrecioTachado(model);
			}
		});

		return pages;
	}

	@Override
	public Page<ProductoModel> findBySubCategoria(Pageable pageable, int id) {
		Page<Producto> productos = productoRepository.findPageBySubCategoria(pageable, id);
		Page<ProductoModel> pages = productos.map(new Function<Producto, ProductoModel>() {
			public ProductoModel apply(Producto producto) {
				ProductoModel model = productoConverter.entityToModel(producto);
				model.setProdAtributos(atributosService.getByProducto(model.getId()));
				return calcularPrecioTachado(model);
			}
		});

		return pages;
	}

	@Override
	public boolean saveAll(MultipartFile file) {
		try {
			List<ProductoModel> productos = ExcelHelper.excelToProductos(file.getInputStream());
			for (ProductoModel p : productos) {
				// verifico que exista la categoria y la seteo o agrego la agrego
				CategoriaModel c=categoriaService.traerPorNombre(p.getSubCategoria().getCategoria().getCategoria());
				if (c != null) {
					p.getSubCategoria().setCategoria(c);
				} else {
					categoriaService.insertOrUpdate(p.getSubCategoria().getCategoria());
					p.getSubCategoria().setCategoria(
							categoriaService.traerPorNombre(p.getSubCategoria().getCategoria().getCategoria()));
				}
				// verifico que exista la subcategoria y la seteo o agrego
				SubCategoriaModel sC = subCategoriaService.traerPorNombre(p.getSubCategoria().getSubCategoria());
				if (sC != null) {
					p.setSubCategoria(sC);
				} else {
					subCategoriaService.insertOrUpdate(p.getSubCategoria());
					p.setSubCategoria(subCategoriaService.traerPorNombre(p.getSubCategoria().getSubCategoria()));
				}
				// ahora reviso que el produco no exista por codigo sku
				if (this.findBySku(p.getSku()) == null) {
					this.insertOrUpdate(p);
				}
			}
			return true;

		} catch (IOException e) {
			throw new RuntimeException("fail to store excel data: " + e.getMessage());
		}
	}

	@Override
	public ProductoModel findBySku(String sku) {
		if (productoRepository.findBySku(sku) != null) {
			return productoConverter.entityToModel(productoRepository.findBySku(sku));
		} else {
			return null;
		}
	}

	private ProductoModel calcularPrecioTachado(ProductoModel producto) {
		if(producto.getDescuento() != 0){
			producto.setPrecioTachado( producto.getPrecio() + ((producto.getPrecio()*producto.getDescuento())/100));
		}
		return producto;
	}

	public String generarSku(ProductoModel productoModel) {
	
		String sku = null;
		List<AtributosModel> atributos = productoModel.getProdAtributos();
		if(atributos!=null) {
		String talle = null;
		String color = null;
		String precio = String.valueOf(productoModel.getPrecio());
		char ch1 = precio.charAt(0);
		char ch2 = precio.charAt(precio.length() - 1);
		char[] a = { ch1, ch2 };

		for (AtributosModel at : atributos) {
			if (at.getAtributo().equalsIgnoreCase("talle")) {
				talle = String.valueOf(at.getValor().charAt(0));
			}
		}

		for (AtributosModel at : atributos) {
			if (at.getAtributo().equalsIgnoreCase("color")) {
				color = String.valueOf(at.getValor().charAt(0)+at.getValor().charAt(1)+at.getValor().charAt(2));
			}
		}
		if (talle == null) {
			talle = "St";
		}
		if (color == null) {
			color = "NaC";
		}
		if (color == null & talle == null) {
			char ch3 = atributos.get(1).getAtributo().charAt(0);
			char ch4 = atributos.get(1).getValor().charAt(1);
			char[] b = { ch3, ch4 };
			sku = String.valueOf(a) + talle + color + String.valueOf(b);

		} else {
			sku = String.valueOf(a) + talle + color;

		}}else {
			String precio = String.valueOf(productoModel.getPrecio());
			char ch1 = precio.charAt(0);
			char ch2 = precio.charAt(precio.length() - 1);
			char ch3 = productoModel.getDescripcionCorta().charAt(1);
			char ch4 = productoModel.getDescripcionCorta().charAt(2);
			Random rand = new Random();
			
			String random = String.valueOf(rand.nextInt());
			char[] c = { ch1, ch2,ch3,ch4 };
			sku = String.valueOf(c) + random;
		}

		

		return sku;
	}

}
