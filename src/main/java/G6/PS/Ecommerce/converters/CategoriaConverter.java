package G6.PS.Ecommerce.converters;

import G6.PS.Ecommerce.models.CategoriaModel;
import G6.PS.Ecommerce.models.SubCategoriaModel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import G6.PS.Ecommerce.entities.Categoria;
import G6.PS.Ecommerce.entities.SubCategoria;

@Component("categoriaConverter")
public class CategoriaConverter {
	
	@Autowired
	@Qualifier("subCategoriaConverter")
	private SubCategoriaConverter subCategoriaConverter;
	
	public CategoriaModel entityToModel(Categoria entity) {
		return new CategoriaModel(entity.getId(), entity.getCategoria(),this.entityToModels(entity.getSubCategorias()));
		
	}

	public Categoria modelToEntity(CategoriaModel model) {
		return new Categoria(model.getId(),model.getCategoria(),this.modelsToEntity(model.getSubcategoria()));
		
	}
	
	
	public List<SubCategoria> modelsToEntity(List<SubCategoriaModel> lista) {
		List<SubCategoria> subCategorias=new ArrayList<SubCategoria>();
		for(SubCategoriaModel s: lista) {
			subCategorias.add(subCategoriaConverter.modelToEntity(s));
		}
		return subCategorias;
	}
	
	public List<SubCategoriaModel> entityToModels(List<SubCategoria> lista){
		List<SubCategoriaModel> subCategorias = new ArrayList<SubCategoriaModel>();
		for(SubCategoria s: lista) {
			subCategorias.add(subCategoriaConverter.entityToModel(s));
		}
		return subCategorias;
	}
}
