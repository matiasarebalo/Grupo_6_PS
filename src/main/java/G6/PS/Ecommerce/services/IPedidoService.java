package G6.PS.Ecommerce.services;

import java.util.List;

import G6.PS.Ecommerce.entities.Pedido;
import G6.PS.Ecommerce.models.PedidoModel;



public interface IPedidoService {

  public List<PedidoModel> getAll();

	public PedidoModel insertOrUpdate(PedidoModel pedidoModel);

	public PedidoModel listarId(int id);

	public String delete(int id);


	public List<PedidoModel> getByProducto(int id); 
 
}
