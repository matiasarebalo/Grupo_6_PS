package G6.PS.Ecommerce.services;

import java.util.List;

import G6.PS.Ecommerce.entities.Atributos;
import G6.PS.Ecommerce.models.AtributosModel;

public interface IAtributosService {
    
    public List<Atributos> getAll();

	public AtributosModel insertOrUpdate(AtributosModel atributosModel);

	public AtributosModel listarId(int id);

	public String delete(int id);
}
