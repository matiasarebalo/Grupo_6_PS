package G6.PS.Ecommerce.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import G6.PS.Ecommerce.converters.SubCategoriaConverter;
import G6.PS.Ecommerce.entities.Categoria;
import G6.PS.Ecommerce.entities.SubCategoria;
import G6.PS.Ecommerce.models.CategoriaModel;
import G6.PS.Ecommerce.models.SubCategoriaModel;
import G6.PS.Ecommerce.repositories.ISubCategoriaRepository;
import G6.PS.Ecommerce.services.ISubCategoriaService;


@Service("subCategoriaService")
public class SubCategoriaService implements ISubCategoriaService{

	@Autowired
	private ISubCategoriaRepository subCategoriaRepository;

	@Autowired
	@Qualifier("subCategoriaConverter")
	private SubCategoriaConverter subCategoriaConverter;
	
	@Override
	public List<SubCategoriaModel> getAll() {
		List<SubCategoriaModel> subCategorias= new ArrayList<SubCategoriaModel>();
		List<SubCategoria> sC= subCategoriaRepository.findAll();
		for(SubCategoria s: sC) {
			subCategorias.add(subCategoriaConverter.entityToModel(s));
		}
		return subCategorias;
	}	

	@Override
	public SubCategoriaModel insertOrUpdate(SubCategoriaModel model) {
		SubCategoria s= subCategoriaRepository.save(subCategoriaConverter.modelToEntity(model));// TODO Auto-generated method stub
		return subCategoriaConverter.entityToModel(s);
	}

	@Override
	public SubCategoriaModel listarId(int id) {
		return	subCategoriaConverter.entityToModel(subCategoriaRepository.findById(id));
	
	}

	@Override
	public String delete(int id) {
		subCategoriaRepository.deleteById(id);
		return "subCategoria Eliminada";
	}
	

}
