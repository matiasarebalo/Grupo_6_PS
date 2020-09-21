package G6.PS.Ecommerce.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import G6.PS.Ecommerce.entities.Atributos;
import G6.PS.Ecommerce.models.AtributosModel;

@Component("atributosConverter")
public class AtributosConverter {
	
	@Autowired
	@Qualifier("atributoValorConverter")
	private AtributoValorConverter atributoValorConverter;

	
	@Autowired
	@Qualifier("productoConverter")
	private ProductoConverter productoConverter;
	public AtributosModel entityToModel (Atributos atributo) {
		return new AtributosModel(atributo.getId(), atributo.getAtributo(), atributoValorConverter.entityToModel(atributo.getAtributoValor()),productoConverter.entityToModel(atributo.getProducto()));
	}

	public Atributos modelToEntity(AtributosModel model) {
		return new Atributos(model.getId(), model.getAtributo(), atributoValorConverter.modelToEntity(model.getAtributoValor()),productoConverter.modelToEntity(model.getProducto()));
	}
}
