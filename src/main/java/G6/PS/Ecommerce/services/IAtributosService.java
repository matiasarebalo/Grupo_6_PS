package G6.PS.Ecommerce.services;

import java.util.List;

import G6.PS.Ecommerce.entities.Atributos;
import G6.PS.Ecommerce.models.AtributosModel;
import G6.PS.Ecommerce.models.ProductoModel;

public interface IAtributosService {
    
    public List<Atributos> getAll();

	public AtributosModel insertOrUpdate(AtributosModel atributosModel);

	public AtributosModel listarId(int id);

	public String delete(int id);

	public void deleteDependencies(int id);

	public List<AtributosModel> getByProducto(int id);

	List<AtributosModel> saveAll(ProductoModel p);
}
