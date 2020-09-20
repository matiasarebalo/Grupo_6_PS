package G6.PS.Ecommerce.converters;

import G6.PS.Ecommerce.models.CategoriaModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import G6.PS.Ecommerce.entities.Categoria;

@Component("categoriaConverter")
public class CategoriaConverter {
	
	@Autowired
	@Qualifier("subCategoriaConverter")
	private SubCategoriaConverter subCategoriaConverter;
	
	public CategoriaModel entityToModel(Categoria entity) {
		return new CategoriaModel(entity.getId(), entity.getCategoria());
		
	}

	public Categoria modelToEntity(CategoriaModel model) {
		return new Categoria(model.getId(), model.getCategoria());
		
	}
	
}
