package G6.PS.Ecommerce.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import G6.PS.Ecommerce.entities.Producto;
import G6.PS.Ecommerce.entities.SubCategoria;
import G6.PS.Ecommerce.models.ProductoModel;
import G6.PS.Ecommerce.models.SubCategoriaModel;

@Component("subCategoriaConverter")
public class SubCategoriaConverter {

	@Autowired
	@Qualifier("productoConverter")
	private ProductoConverter productoConverter;

	public SubCategoriaModel entityToModel(SubCategoria entitie) {
		return new SubCategoriaModel(entitie.getId(),entitie.getSubCategoria(),this.entityToModels(entitie.getProducto()));
	}
	
	public SubCategoria modelToEntity(SubCategoriaModel model) {
		
		return new SubCategoria(model.getId(),model.getSubCategoria(),this.modelsToEntity(model.getProductoModel()));
	}

	public List<ProductoModel> entityToModels(List<Producto> productos) {
		List<ProductoModel> productosModels = new ArrayList<ProductoModel>();
		for (Producto p : productos) {
			productosModels.add(productoConverter.entityToModel(p));
		}
		return productosModels;
	}

	public List<Producto> modelsToEntity(List<ProductoModel> productos) {
		List<Producto> productosEntity = new ArrayList<Producto>();
		for (ProductoModel p : productos) {
			productosEntity.add(productoConverter.modelToEntity(p));
		}
		return productosEntity;
	}

}
