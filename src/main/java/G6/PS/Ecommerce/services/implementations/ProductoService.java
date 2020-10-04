package G6.PS.Ecommerce.services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import G6.PS.Ecommerce.converters.ProductoConverter;
import G6.PS.Ecommerce.entities.Producto;
import G6.PS.Ecommerce.models.ProductoModel;
import G6.PS.Ecommerce.repositories.IProductoRepository;
import G6.PS.Ecommerce.services.IAtributosService;
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
	@Qualifier("atributosService")
	private IAtributosService atributosService;

	
	@Autowired
	@Qualifier("productoConverter")
	private ProductoConverter productoConverter;
	
	@Override
	public List<ProductoModel> getAll(){
		List<Producto> productos = productoRepository.findAll();
		List<ProductoModel> pM = new ArrayList<ProductoModel>();
		for (Producto p : productos) {
			pM.add(productoConverter.entityToModel(p));
		}
		return pM;
	}
	
	@Override
	public ProductoModel insertOrUpdate(ProductoModel model) {
		Producto p = productoRepository.save( productoConverter.modelToEntity(model));

		return productoConverter.entityToModel(p);
	}
	
	@Override
	public ProductoModel listarId(int id) {
		return	productoConverter.entityToModel(productoRepository.findById(id));
	}
	
	@Override
	public String delete(int id) {
		
   		int sC=	productoRepository.findById(id).getSubCategoria().getId();
		
    	atributosService.deleteDependencies(id);
		subCategoriaService.deleteDependencies(sC);
		productoRepository.deleteById(id);
		
		return "producto Eliminado";
	}
	
	public List<ProductoModel> findDestacados(){
		List<Producto> productos = productoRepository.findDestacados();
		List<ProductoModel> pM = new ArrayList<ProductoModel>();
		for (Producto p : productos) {
			pM.add(productoConverter.entityToModel(p));
		}
		return pM;
	}
	
	public List<ProductoModel> findBySubCategoria(int id){
		List<Producto> productos = productoRepository.findBySubCategoria(id);
		List<ProductoModel> pM = new ArrayList<ProductoModel>();
		for (Producto p : productos) {
			pM.add(productoConverter.entityToModel(p));
		}
		return pM;
	}
	
	public List<ProductoModel> findRelacionados(int id_articulo,int id_sub){
		List<Producto> productos = productoRepository.findRelacionados(id_articulo,id_sub);
		List<ProductoModel> pM = new ArrayList<ProductoModel>();
		for (Producto p : productos) {
			pM.add(productoConverter.entityToModel(p));
		}
		return pM;
	}

	@Override
	public List<ProductoModel> findDependency(int id) {
	//cuando tengamos envios verificar que un producto no tenga envios realizado o en curso antes de eliminarlos.
	//		List<Producto> productos = 	productoRepository.findIfExist(id);
	//		List<ProductoModel> pM = new ArrayList<ProductoModel>();
	//
	//		for (Producto p : productos) {
	//			pM.add(productoConverter.entityToModel(p));
	//		}
	//
	//		return pM;
		return null;
	}

	public List<ProductoModel> searchByProducto(String producto){
		List<Producto> productos = productoRepository.findBydescripcionCortaContainingIgnoreCase(producto);
		List<ProductoModel> pM = new ArrayList<ProductoModel>();
		for (Producto p : productos) {
			pM.add(productoConverter.entityToModel(p));
		}
		return pM;	
	}
	
	public List<ProductoModel> findByCategoria(int id){
		List<Producto> productos = productoRepository.findByCategoria(id);
		List<ProductoModel> pM = new ArrayList<ProductoModel>();
		for (Producto p : productos) {
			pM.add(productoConverter.entityToModel(p));
		}
		return pM;
	}
	
	@Override
	public Page<ProductoModel> findByCategoria(Pageable pageable,int id) {
		Page<Producto> productos = productoRepository.findAll(pageable);
		Page<ProductoModel> pages = productos.map(new Function<Producto, ProductoModel>() {
			public ProductoModel apply(Producto producto) {
				ProductoModel model = productoConverter.entityToModel(producto);
				return model;
			}
		});

		return pages;
	}
	
	@Override
	public Page<ProductoModel> findBySubCategoria(Pageable pageable,int id) {
		Page<Producto> productos = productoRepository.findAll(pageable);
		Page<ProductoModel> pages = productos.map(new Function<Producto, ProductoModel>() {
			public ProductoModel apply(Producto producto) {
				ProductoModel model = productoConverter.entityToModel(producto);
				return model;
			}
		});

		return pages;
	}

}
