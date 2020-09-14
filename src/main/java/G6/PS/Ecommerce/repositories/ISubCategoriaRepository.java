package G6.PS.Ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import G6.PS.Ecommerce.entities.SubCategoria;

@Repository("subCategoriaRepository")
public interface ISubCategoriaRepository extends JpaRepository<SubCategoria,Integer> {
	
	public abstract SubCategoria findById(int id);

}
