package G6.PS.Ecommerce.services;

import java.util.List;

import G6.PS.Ecommerce.entities.AtributoValor;
import G6.PS.Ecommerce.models.AtributoValorModel;

public interface IAtributoValorService {

    public List<AtributoValor> getAll();

	public AtributoValorModel insertOrUpdate(AtributoValorModel atributosModel);

	public AtributoValorModel listarId(int id);

	public String delete(int id);
}
