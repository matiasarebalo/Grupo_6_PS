package G6.PS.Ecommerce.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import G6.PS.Ecommerce.entities.Pedido;
import G6.PS.Ecommerce.models.PedidoModel;


@Component("pedidoConverter")
public class PedidoConverter {

	@Autowired
	@Qualifier("productoConverter")
	private ProductoConverter productoConverter;
	
	@Autowired
	@Qualifier("embalajeConverter")
	private EmbalajeConverter embalajeConverter;
	
	
	public PedidoModel entityToModel(Pedido pedido) {
		return new PedidoModel(pedido.getId(), productoConverter.entityToModel(pedido.getProducto()), pedido.getDireccion(), pedido.getCosto(), pedido.getCodigoPromocion(), 
		pedido.getMetodoDePago(), pedido.getAceptado(),embalajeConverter.entityToModel(pedido.getEmbalaje()), pedido.getNombre_apellido(), pedido.getEmail());
	}
	
	public Pedido modelToEntity(PedidoModel model) {
		return new Pedido(model.getId(), productoConverter.modelToEntity(model.getProducto()), model.getDireccion(), model.getCosto(), model.getCodigoPromocion(), 
		model.getMetodoDePago(), embalajeConverter.modelToEntity(model.getEmbalaje()), model.getNombre_apellido(), model.getEmail());
	}
}
