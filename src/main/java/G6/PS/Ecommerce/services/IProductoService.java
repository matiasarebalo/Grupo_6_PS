package G6.PS.Ecommerce.services;

import java.util.List;

import G6.PS.Ecommerce.entities.Producto;
import G6.PS.Ecommerce.models.ProductoModel;



public interface IProductoService {
	
	public List<Producto> getAll();

	public ProductoModel insertOrUpdate(ProductoModel productoModel);

	public ProductoModel listarId(int id);

	public String delete(int id);
	
	public List<ProductoModel> findDestacados();
}
