package G6.PS.Ecommerce.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import G6.PS.Ecommerce.entities.AtributoValor;
import G6.PS.Ecommerce.models.AtributoValorModel;

@Component("atributoValorConverter")
public class AtributoValorConverter {

	@Autowired
	@Qualifier("atributosConverter")
	private AtributosConverter atributosConverter;
	
	public AtributoValorModel entityToModel (AtributoValor atributo) {
		return new AtributoValorModel(atributo.getId(), atributo.getValor() );
	}

	public AtributoValor modelToEntity(AtributoValorModel model) {
		if(model != null){
			return new AtributoValor(model.getId(), model.getValor());
		}
		else return null;
	}
}
