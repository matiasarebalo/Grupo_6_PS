package G6.PS.Ecommerce.services.implementations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
import G6.PS.Ecommerce.models.ProductoModel;
import G6.PS.Ecommerce.models.SubCategoriaModel;
import G6.PS.Ecommerce.repositories.IProductoRepository;
import G6.PS.Ecommerce.services.IAtributosService;
import G6.PS.Ecommerce.services.ICategoriaService;
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
	@Qualifier("productoConverter")
	private ProductoConverter productoConverter;

	@Override
	public List<ProductoModel> getAll() {
		List<Producto> productos = productoRepository.findAll();
		List<ProductoModel> pM = new ArrayList<ProductoModel>();
		for (Producto p : productos) {
			pM.add(productoConverter.entityToModel(p));
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
		Producto p = productoRepository.save(productoConverter.modelToEntity(model));

		return productoConverter.entityToModel(p);
	}

	@Override
	public ProductoModel listarId(int id) {
		Producto producto = productoRepository.findById(id);
		if (producto == null) {
			return null;
		}
		return productoConverter.entityToModel(producto);
	}

	@Override
	public String delete(int id) {

		int sC = productoRepository.findById(id).getSubCategoria().getId();

		atributosService.deleteDependencies(id);
		subCategoriaService.deleteDependencies(sC);
		productoRepository.deleteById(id);

		return "producto Eliminado";
	}

	public List<ProductoModel> findDestacados() {
		List<Producto> productos = productoRepository.findDestacados();
		List<ProductoModel> pM = new ArrayList<ProductoModel>();
		for (Producto p : productos) {
			pM.add(productoConverter.entityToModel(p));
		}
		return pM;
	}

	public List<ProductoModel> findBySubCategoria(int id) {
		List<Producto> productos = productoRepository.findBySubCategoria(id);
		List<ProductoModel> pM = new ArrayList<ProductoModel>();
		for (Producto p : productos) {
			pM.add(productoConverter.entityToModel(p));
		}
		return pM;
	}

	public List<ProductoModel> findRelacionados(int id_articulo, int id_sub) {
		List<Producto> productos = productoRepository.findRelacionados(id_articulo, id_sub);
		List<ProductoModel> pM = new ArrayList<ProductoModel>();
		for (Producto p : productos) {
			pM.add(productoConverter.entityToModel(p));
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
			pM.add(productoConverter.entityToModel(p));
		}
		return pM;
	}

	public List<ProductoModel> findByCategoria(int id) {
		List<Producto> productos = productoRepository.findByCategoria(id);
		List<ProductoModel> pM = new ArrayList<ProductoModel>();
		for (Producto p : productos) {
			pM.add(productoConverter.entityToModel(p));
		}
		return pM;
	}

	@Override
	public Page<ProductoModel> findByCategoria(Pageable pageable, int id) {
		Page<Producto> productos = productoRepository.findPageByCategoria(pageable, id);
		Page<ProductoModel> pages = productos.map(new Function<Producto, ProductoModel>() {
			public ProductoModel apply(Producto producto) {
				ProductoModel model = productoConverter.entityToModel(producto);
				return model;
			}
		});

		return pages;
	}

	@Override
	public Page<ProductoModel> findBySubCategoria(Pageable pageable, int id) {
		Page<Producto> productos = productoRepository.findPageByCategoria(pageable, id);
		Page<ProductoModel> pages = productos.map(new Function<Producto, ProductoModel>() {
			public ProductoModel apply(Producto producto) {
				ProductoModel model = productoConverter.entityToModel(producto);
				return model;
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
				CategoriaModel cm = categoriaService.traerPorNombre(p.getSubCategoria().getCategoria().getCategoria());
				if (cm != null) {
					p.getSubCategoria().setCategoria(cm);
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
	
	public String generarSku(ProductoModel productoModel) {
		
		List<AtributosModel> atributos= productoModel.getProdAtributos();
		String talle=null;
		String color=null;
		
		String precio= String.valueOf(productoModel.getPrecio());
		char ch1 = precio.charAt(0);
		char ch2 = precio.charAt(precio.length()-1);
		char [] a = { ch1 , ch2 };

		for(AtributosModel at : atributos) {
			if(at.getAtributo().equalsIgnoreCase("talle")) {
				talle=at.getValor();
			}
			}
			
			for(AtributosModel at : atributos) {
				if(at.getAtributo().equalsIgnoreCase("color")) {
					color=at.getValor();
				}	
		}
		if(talle==null) {
			talle="St";
		}
		if(color==null) {
			color="NaC";
		}
		String sku=String.valueOf(a)+talle+color;
		
		
	return sku;	
	}

}
