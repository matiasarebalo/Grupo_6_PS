package G6.PS.Ecommerce.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import G6.PS.Ecommerce.converters.ProductoConverter;
import G6.PS.Ecommerce.entities.Producto;
import G6.PS.Ecommerce.models.ProductoModel;
import G6.PS.Ecommerce.repositories.IProductoRepository;
import G6.PS.Ecommerce.services.IProductoService;

@Service("productoService")
public class ProductoService implements IProductoService {
	 
	@Autowired
	private IProductoRepository productoRepository;
	
	@Autowired
	@Qualifier("productoConverter")
	private ProductoConverter productoConverter;
	
	@Override
	public List<Producto> getAll(){
		return productoRepository.findAll();
	}
	
	@Override
	public ProductoModel insertOrUpdate(ProductoModel model) {
		Producto p = productoConverter.modelToEntity(model);
		return productoConverter.entityToModel(p);
	}
	
	@Override
	public ProductoModel listarId(int id) {
		return	productoConverter.entityToModel(productoRepository.findById(id));
	}
	
	@Override
	public String delete(int id) {
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
}
