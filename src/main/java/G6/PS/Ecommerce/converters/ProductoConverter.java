package G6.PS.Ecommerce.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import G6.PS.Ecommerce.entities.Producto;
import G6.PS.Ecommerce.models.ProductoModel;

@Component("productoConverter")
public class ProductoConverter {
	
	@Autowired
	@Qualifier("subCategoriaConverter")
	private SubCategoriaConverter subCategoriaConverter;
	
	ProductoModel entityToModel (Producto producto) {
		return new ProductoModel(producto.getId(), producto.getDescripcionCorta(), producto.getDescripcionLarga(),
				producto.getTalle(), producto.getColor(), subCategoriaConverter.entityToModel(producto.getSubCategoria()),
				producto.getSku(), producto.getUrlImagen(), producto.getPrecio(), producto.getValoracion());
	}
	
	Producto modelToEntity (ProductoModel model) {
		return new Producto(model.getId(), model.getDescripcionCorta(), model.getDescripcionLarga(),
				model.getTalle(), model.getColor(), subCategoriaConverter.modelToEntity(model.getSubCategoriaModel()),
				model.getSku(), model.getUrlImagen(), model.getPrecio(), model.getValoracion());
	}

}
