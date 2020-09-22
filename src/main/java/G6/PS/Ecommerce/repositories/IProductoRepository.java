package G6.PS.Ecommerce.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import G6.PS.Ecommerce.entities.Producto;

@Repository("productoRepository")
public interface IProductoRepository extends JpaRepository<Producto, Serializable>{
	
	public abstract Producto findById(int id);
	
	@Query(nativeQuery=true,value="select * from producto where destacado = true")
	public List<Producto> findDestacados();
	
	@Query(nativeQuery=true,value="Select * from producto where sub_categoria_id=(:id)")
	public List<Producto> findBySubCategoria(int id);
	
	@Query(nativeQuery=true,value="Select * from producto where sub_categoria_id=(:id_sub) and id!=(:id_articulo)")
	public List<Producto> findRelacionados(int id_articulo,int id_sub);
}
