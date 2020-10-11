package G6.PS.Ecommerce.converters;

import org.springframework.stereotype.Component;

import G6.PS.Ecommerce.entities.Comentario;
import G6.PS.Ecommerce.models.ComentarioModel;

@Component("comentarioConverter")
public class ComentarioConverter {
	
	public ComentarioModel entityToModel(Comentario comentario) {
		return new ComentarioModel(comentario.getId(), comentario.getComentario(), comentario.getFechaComentario());
	}
	
	public Comentario modelToEntity(ComentarioModel model) {
		return new Comentario(model.getId(), model.getComentario(), model.getFechaComentario());
	}
}
