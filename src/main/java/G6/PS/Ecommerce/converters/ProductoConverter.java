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

	@Autowired
	@Qualifier("atributosConverter")
	private AtributosConverter atributosConverter;
	
	public ProductoModel entityToModel (Producto producto) {
		return new ProductoModel(producto.getId(), producto.getDescripcionCorta(), producto.getDescripcionLarga(), 
				subCategoriaConverter.entityToModel(producto.getSubCategoria()), producto.getUrlImagen(),producto.getSku(),
				producto.getPrecio(), producto.getDestacado(), producto.isVisible(), producto.getDescuento(), producto.isPromocion());
	}

	public Producto modelToEntity(ProductoModel model) {
		return new Producto(model.getId(), model.getDescripcionCorta(), model.getDescripcionLarga(), 
				subCategoriaConverter.modelToEntity(model.getSubCategoria()), model.getUrlImagen(), model.getSku(),
				model.getPrecio(), model.getDestacado(), model.isVisible(), model.getDescuento(), model.isPromocion());
	}
}
