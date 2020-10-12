package G6.PS.Ecommerce.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import G6.PS.Ecommerce.entities.Comentario;
import G6.PS.Ecommerce.models.ComentarioModel;

@Component("comentarioConverter")
public class ComentarioConverter {
	
	@Autowired
	@Qualifier("pedidoConverter")
	private PedidoConverter pedidoConverter;
	
	public ComentarioModel entityToModel(Comentario comentario) {
		return new ComentarioModel(comentario.getId(), comentario.getComentario(), comentario.getFechaComentario(), pedidoConverter.entityToModel(comentario.getPedido()));
	}
	
	public Comentario modelToEntity(ComentarioModel model) {
		return new Comentario(model.getId(), model.getComentario(), model.getFechaComentario(), pedidoConverter.modelToEntity(model.getPedido()));
	}
}
