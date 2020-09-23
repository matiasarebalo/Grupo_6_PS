package G6.PS.Ecommerce.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import G6.PS.Ecommerce.converters.AtributoValorConverter;
import G6.PS.Ecommerce.entities.AtributoValor;
import G6.PS.Ecommerce.models.AtributoValorModel;
import G6.PS.Ecommerce.repositories.IAtributoValorRepository;
import G6.PS.Ecommerce.services.IAtributoValorService;

@Service("atributoValorService")
public class AtributoValorService implements IAtributoValorService{

    @Autowired
	private IAtributoValorRepository atributoValorRepository;

	@Autowired
	@Qualifier("atributoValorConverter")
	private AtributoValorConverter atributoValorConverter;
	
	@Override
	public List<AtributoValor> getAll() {
		// TODO Auto-generated method stub
		return atributoValorRepository.findAll();
	}

	@Override
	public AtributoValorModel insertOrUpdate(AtributoValorModel model) {
		AtributoValor s= atributoValorRepository.save(atributoValorConverter.modelToEntity(model));// TODO Auto-generated method stub
		return atributoValorConverter.entityToModel(s);
	}

	@Override
	public AtributoValorModel listarId(int id) {
		return	atributoValorConverter.entityToModel(atributoValorRepository.findById(id));
	
	}

	@Override
	public String delete(int id) {
		atributoValorRepository.deleteById(id);
		return "subCategoria Eliminada";
	}
}
