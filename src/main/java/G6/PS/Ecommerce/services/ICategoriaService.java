package G6.PS.Ecommerce.services;

import java.util.List;

import G6.PS.Ecommerce.entities.Categoria;
import G6.PS.Ecommerce.models.CategoriaModel;

public interface ICategoriaService  {

	public List<Categoria> getAll();

	public CategoriaModel insertOrUpdate(CategoriaModel categoriaModel);

	public CategoriaModel ListarId(int id);

	public String delete(int id);


}
