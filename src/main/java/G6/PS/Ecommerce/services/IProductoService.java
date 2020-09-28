package G6.PS.Ecommerce.services;

import java.util.List;

import G6.PS.Ecommerce.entities.Producto;
import G6.PS.Ecommerce.models.ProductoModel;



public interface IProductoService {
	
	public List<ProductoModel> getAll();

	public ProductoModel insertOrUpdate(ProductoModel productoModel);

	public ProductoModel listarId(int id);

	public String delete(int id);
	
	public List<ProductoModel> findDestacados();
	
	public List<ProductoModel> findBySubCategoria(int id);
	
	public List<ProductoModel> findRelacionados(int id_articulo,int id_sub);

	public List<ProductoModel> findDependency(int id);

	public List<ProductoModel> searchByProducto(String producto);
}
