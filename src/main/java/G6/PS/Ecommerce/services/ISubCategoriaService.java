package G6.PS.Ecommerce.services;

import java.util.List;

import G6.PS.Ecommerce.entities.SubCategoria;
import G6.PS.Ecommerce.models.SubCategoriaModel;



public interface ISubCategoriaService {

	public List<SubCategoriaModel> getAll();

	public SubCategoriaModel insertOrUpdate(SubCategoriaModel subCategoriaModel);

	public SubCategoriaModel listarId(int id);

	public String delete(int id);

	
}
