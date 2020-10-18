package G6.PS.Ecommerce.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import G6.PS.Ecommerce.models.ProductoModel;


public interface IProductoService {
	
	public List<ProductoModel> getAll();

	public List<ProductoModel> getAllVisibles();

	public ProductoModel insertOrUpdate(ProductoModel productoModel);

	public ProductoModel listarId(int id);

	public ProductoModel findBySku(String Sku);

	public String delete(int id);
	
	public List<ProductoModel> findDestacados();
	
	public List<ProductoModel> findBySubCategoria(int id);
	
	public List<ProductoModel> findByCategoria(int id);
	
	public List<ProductoModel> findRelacionados(int id_articulo,int id_sub);

	public List<ProductoModel> findDependency(int id);

	public List<ProductoModel> searchByProducto(String producto);

	Page<ProductoModel> findByCategoria(Pageable pageable,int id);
	
	Page<ProductoModel> findBySubCategoria(Pageable pageable,int id);
	
	public boolean saveAll(MultipartFile file);
}
