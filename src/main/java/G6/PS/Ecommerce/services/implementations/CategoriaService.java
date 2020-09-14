package G6.PS.Ecommerce.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import G6.PS.Ecommerce.converters.CategoriaConverter;
import G6.PS.Ecommerce.entities.Categoria;
import G6.PS.Ecommerce.models.CategoriaModel;
import G6.PS.Ecommerce.repositories.ICategoriaRepository;
import G6.PS.Ecommerce.services.ICategoriaService;

@Service("categoriaService")
public class CategoriaService implements ICategoriaService{

	

	@Autowired
	private ICategoriaRepository categoriaRepository;

	@Autowired
	@Qualifier("categoriaConverter")
	private CategoriaConverter categoriaConverter;
	
	@Override
	public List<Categoria> getAll() {
		// TODO Auto-generated method stub
		return categoriaRepository.findAll();
	}

	@Override
	public CategoriaModel insertOrUpdate(CategoriaModel categoriaModel) {
		// TODO Auto-generated method stub
		Categoria categoria= categoriaRepository.save(categoriaConverter.modelToEntity(categoriaModel));
		return categoriaConverter.entityToModel(categoria);
	}

	@Override
	public CategoriaModel ListarId(int id) {
		return	categoriaConverter.entityToModel(categoriaRepository.findById(id));
		
	}

	@Override
	public String delete(int id) {
		// TODO Auto-generated method stub
		categoriaRepository.deleteById(id);
		return "Categoria eliminada";
	}

}
