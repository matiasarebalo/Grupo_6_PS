package G6.PS.Ecommerce.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import G6.PS.Ecommerce.converters.SubCategoriaConverter;
import G6.PS.Ecommerce.entities.SubCategoria;
import G6.PS.Ecommerce.models.SubCategoriaModel;
import G6.PS.Ecommerce.repositories.ISubCategoriaRepository;
import G6.PS.Ecommerce.services.ISubCategoriaService;


@Service("subCategoriaService")
public class SubCategoriaService implements ISubCategoriaService{

	@Autowired
	private ISubCategoriaRepository subCategoriaRepository;

	@Autowired
	@Qualifier("categoriaConverter")
	private SubCategoriaConverter subCategoriaConverter;
	
	@Override
	public List<SubCategoria> getAll() {
		// TODO Auto-generated method stub
		return subCategoriaRepository.findAll();
	}

	@Override
	public SubCategoriaModel insertOrUpdate(SubCategoriaModel model) {
		SubCategoria s= subCategoriaConverter.modelToEntity(model);// TODO Auto-generated method stub
		return subCategoriaConverter.entityToModel(s);
	}

	@Override
	public SubCategoriaModel ListarId(int id) {
		return	subCategoriaConverter.entityToModel(subCategoriaRepository.findById(id));
	
	}

	@Override
	public String delete(int id) {
		subCategoriaRepository.deleteById(id);
		return "subCategoria Eliminada";
	}
	

}
