package G6.PS.Ecommerce.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import G6.PS.Ecommerce.entities.Categoria;
import G6.PS.Ecommerce.entities.SubCategoria;

@Repository("subCategoriaRepository")
public interface ISubCategoriaRepository extends JpaRepository<SubCategoria, Serializable> {
	
	public abstract SubCategoria findById(int id);
	
	@Query(nativeQuery=true,value="select distinct * from subcategoria where sub_categoria =(:nombre)")
	public SubCategoria findBynombre(String nombre);
	
	@Query(nativeQuery=true,value="Select * from subcategoria where categoria_id = (select categoria_id from subcategoria where id=(:id)")
	public List<SubCategoria> findBySubCategoria(int id);
	
}
