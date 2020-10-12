package G6.PS.Ecommerce.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import G6.PS.Ecommerce.converters.PedidoConverter;
import G6.PS.Ecommerce.entities.Pedido;
import G6.PS.Ecommerce.models.PedidoModel;
import G6.PS.Ecommerce.repositories.IPedidoRepository;
import G6.PS.Ecommerce.services.IPedidoService;

@Service("pedidoService")
public class PedidoService implements IPedidoService {
	
	@Autowired
	private IPedidoRepository pedidoRepository;
	
	@Autowired
	@Qualifier("pedidoConverter")
	private PedidoConverter pedidoConverter;

	@Override
	public List<PedidoModel> getAll() {
		List<PedidoModel> pedidos = new ArrayList<PedidoModel>();
		List<Pedido> pe= pedidoRepository.findAll();
		for(Pedido p: pe) {
			pedidos.add(pedidoConverter.entityToModel(p));
		}
		return pedidos;
	}

	@Override
	public PedidoModel insertOrUpdate(PedidoModel pedidoModel) {
		Pedido pedido= pedidoRepository.save(pedidoConverter.modelToEntity(pedidoModel));
		
		return pedidoConverter.entityToModel(pedido);
	}

	@Override
	public PedidoModel listarId(int id) {
		Pedido pedido = pedidoRepository.findById(id);
		if(pedido == null){
			return null;
		}
		return pedidoConverter.entityToModel(pedido);
		
	}

	@Override
	public String delete(int id) {
pedidoRepository.deleteById(id);	
return "pedido eliminado";}


	@Override
	public List<PedidoModel> getByProducto(int id) {
		List <PedidoModel> pM = new ArrayList<PedidoModel>();
		List<Pedido> p = pedidoRepository.findByProducto(id);
		for(Pedido pedido : p) {
			pM.add(pedidoConverter.entityToModel(pedido));
		}
		
		return pM;
	}

}
