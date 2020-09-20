package G6.PS.Ecommerce.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import G6.PS.Ecommerce.entities.SubCategoria;
import G6.PS.Ecommerce.models.SubCategoriaModel;

@Component("subCategoriaConverter")
public class SubCategoriaConverter {

	@Autowired
	@Qualifier("categoriaConverter")
	private CategoriaConverter categoriaConverter;

	public SubCategoriaModel entityToModel(SubCategoria entitie) {
		return new SubCategoriaModel(entitie.getId(), entitie.getSubCategoria(), categoriaConverter.entityToModel(entitie.getCategoria()));
	}
	
	public SubCategoria modelToEntity(SubCategoriaModel model) {
		
		return new SubCategoria(model.getId(), model.getSubCategoria(),categoriaConverter.modelToEntity(model.getCategoria()));
	}
}
