package G6.PS.Ecommerce.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import G6.PS.Ecommerce.converters.AtributosConverter;
import G6.PS.Ecommerce.entities.Atributos;
import G6.PS.Ecommerce.models.AtributosModel;
import G6.PS.Ecommerce.repositories.IAtributosRepository;
import G6.PS.Ecommerce.services.IAtributosService;

@Service("atributosService")
public class AtributosService implements IAtributosService{
    @Autowired
	private IAtributosRepository atributosRepository;

	@Autowired
	@Qualifier("atributosConverter")
	private AtributosConverter atributosConverter;
	
	@Override
	public List<Atributos> getAll() {
		// TODO Auto-generated method stub
		return atributosRepository.findAll();
	}

	@Override
	public AtributosModel insertOrUpdate(AtributosModel model) {
		Atributos s= atributosRepository.save(atributosConverter.modelToEntity(model));// TODO Auto-generated method stub
		return atributosConverter.entityToModel(s);
	}

	@Override
	public AtributosModel listarId(int id) {
		return	atributosConverter.entityToModel(atributosRepository.findById(id));
	
	}

	@Override
	public String delete(int id) {
		atributosRepository.deleteById(id);
		return "subCategoria Eliminada";
	}
}
