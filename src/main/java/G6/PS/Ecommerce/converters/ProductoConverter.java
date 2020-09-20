package G6.PS.Ecommerce.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import G6.PS.Ecommerce.entities.Producto;
import G6.PS.Ecommerce.entities.Atributos;
import G6.PS.Ecommerce.models.AtributosModel;
import G6.PS.Ecommerce.models.ProductoModel;

@Component("productoConverter")
public class ProductoConverter {
	
	@Autowired
	@Qualifier("subCategoriaConverter")
	private SubCategoriaConverter subCategoriaConverter;

	@Autowired
	@Qualifier("atributosConverter")
	private AtributosConverter atributosConverter;
	
	public ProductoModel entityToModel (Producto producto) {
		return new ProductoModel(producto.getId(), producto.getDescripcionCorta(), producto.getDescripcionLarga(), 
				subCategoriaConverter.entityToModel(producto.getSubCategoria()), producto.getUrlImagen(),producto.getSku(),
				producto.getPrecio(), producto.getDestacado(), this.entityToModels(producto.getProdAtributos()));
	}

	public Producto modelToEntity(ProductoModel model) {
		return new Producto(model.getId(), model.getDescripcionCorta(), model.getDescripcionLarga(), 
				subCategoriaConverter.modelToEntity(model.getSubCategoriaModel()), model.getUrlImagen(), model.getSku(),
				model.getPrecio(), model.getDestacado(), this.modelsToEntity(model.getProdAtributos()));
	}

	public List<Atributos> modelsToEntity(List<AtributosModel> lista) {
		List<Atributos> atributos=new ArrayList<Atributos>();
		for(AtributosModel s: lista) {
			atributos.add(atributosConverter.modelToEntity(s));
		}
		return atributos;
	}
	
	public List<AtributosModel> entityToModels(List<Atributos> lista){
		List<AtributosModel> atributosModels = new ArrayList<AtributosModel>();
		for(Atributos s: lista) {
			atributosModels.add(atributosConverter.entityToModel(s));
		}
		return atributosModels;
	}
}
