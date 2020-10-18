package G6.PS.Ecommerce.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import G6.PS.Ecommerce.entities.Categoria;
import G6.PS.Ecommerce.entities.Producto;

@Repository("categoriaRepository")
public interface ICategoriaRepository extends JpaRepository<Categoria, Serializable> {
	
	public abstract Categoria findById(int id);
	
	@Query(nativeQuery=true,value="select distinct * from categoria where categoria =(:nombre)")
	public Categoria findBynombre(String nombre);
	

}
