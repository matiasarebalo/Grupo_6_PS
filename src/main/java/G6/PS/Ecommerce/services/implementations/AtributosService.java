package G6.PS.Ecommerce.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import G6.PS.Ecommerce.converters.AtributosConverter;
import G6.PS.Ecommerce.entities.Atributos;
import G6.PS.Ecommerce.models.AtributosModel;
import G6.PS.Ecommerce.repositories.IAtributosRepository;
import G6.PS.Ecommerce.services.IAtributosService;
import G6.PS.Ecommerce.services.IProductoService;

@Service("atributosService")
public class AtributosService implements IAtributosService {
	@Autowired
	private IAtributosRepository atributosRepository;

	@Autowired
	@Qualifier("atributosConverter")
	private AtributosConverter atributosConverter;

	@Autowired
	@Qualifier("productoService")
	private IProductoService productoService;

	
	@Override
	public List<Atributos> getAll() {
		return atributosRepository.findAll();
	}

	@Override
	public AtributosModel insertOrUpdate(AtributosModel model) {
		Atributos s= atributosRepository.save( atributosConverter.modelToEntity(model));
		return atributosConverter.entityToModel(s);
	}

	@Override
	public AtributosModel listarId(int id) {
		return atributosConverter.entityToModel(atributosRepository.findById(id));
	}

	@Override
	public List<AtributosModel> getByProducto(int id) {
		List<Atributos> atributosModels = atributosRepository.findByProducto(id);
		List<AtributosModel> aM = new ArrayList<AtributosModel>();
		for (Atributos a : atributosModels) {
			aM.add(atributosConverter.entityToModel(a));
		}
		return aM;
	}

	@Override
	public String delete(int id) {
		atributosRepository.deleteById(id);
		return "subCategoria Eliminada";
	}

	@Override
	public void deleteDependencies(int idProducto) {
		// traigo todos los atributos de el producto que voy a eliminar
		List<AtributosModel> aM = this.getByProducto(idProducto);
		for (AtributosModel a : aM) {
			delete(a.getId());
		}
	}

	
}
