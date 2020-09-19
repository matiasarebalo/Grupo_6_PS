package G6.PS.Ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import G6.PS.Ecommerce.entities.Producto;

@Repository("productoRepository")
public interface IProductoRepository extends JpaRepository<Producto,Integer>{
	public abstract Producto findById(int id);
	
	@Query(nativeQuery=true,value="select * from producto where destacado = 'si'")
	public List<Producto> findDestacados();
}