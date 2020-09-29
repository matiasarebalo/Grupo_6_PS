package G6.PS.Ecommerce.services.implementations;

import java.util.ArrayList;
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
	public List<CategoriaModel> getAll() {
		List<CategoriaModel> categorias= new ArrayList<CategoriaModel>();
		List<Categoria> ct= categoriaRepository.findAll();
		for(Categoria c: ct) {
			categorias.add(categoriaConverter.entityToModel(c));
		}
		return categorias;
	}

	@Override
	public CategoriaModel insertOrUpdate(CategoriaModel categoriaModel) {
		Categoria categoria= categoriaRepository.save(categoriaConverter.modelToEntity(categoriaModel));
		return categoriaConverter.entityToModel(categoria);
	}

	@Override
	public CategoriaModel listarId(int id) {
		return	categoriaConverter.entityToModel(categoriaRepository.findById(id));
		
	}

	@Override
	public String delete(int id) {
		categoriaRepository.deleteById(id);
		return "Categoria eliminada";
	}

}
