package G6.PS.Ecommerce.converters;

import org.springframework.stereotype.Component;

import G6.PS.Ecommerce.entities.Embalaje;
import G6.PS.Ecommerce.models.EmbalajeModel;

@Component("embalajeConverter")
public class EmbalajeConverter {
	
	public EmbalajeModel entityToModel (Embalaje embalaje) {
		return new EmbalajeModel(embalaje.getId(), embalaje.getPeso(), embalaje.getAncho(), embalaje.getAlto(), embalaje.getProfundidad(), embalaje.getPrecio());
	}
	
	public Embalaje modelToEntity (EmbalajeModel model) {
		return new Embalaje(model.getId(), model.getPeso(), model.getAncho(), model.getAlto(), model.getProfundidad(), model.getPrecio());
	}
}
