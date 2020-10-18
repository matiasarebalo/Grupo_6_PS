package G6.PS.Ecommerce.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import G6.PS.Ecommerce.entities.Categoria;
import G6.PS.Ecommerce.entities.SubCategoria;

@Repository("subCategoriaRepository")
public interface ISubCategoriaRepository extends JpaRepository<SubCategoria, Serializable> {
	
	public abstract SubCategoria findById(int id);
	
	@Query(nativeQuery=true,value="select distinct * from subcategoria where subcategoria =(:nombre)")
	public SubCategoria findBynombre(String nombre);
	

}
