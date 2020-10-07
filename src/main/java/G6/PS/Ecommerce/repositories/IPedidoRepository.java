package G6.PS.Ecommerce.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import G6.PS.Ecommerce.entities.Pedido;


@Repository("pedidoRepository")
public interface IPedidoRepository extends  JpaRepository<Pedido, Serializable>{
    
    public abstract Pedido findById(int id);
    
    @Query(nativeQuery=true,value="Select * from pedido where producto_id=(:id)")
	public List<Pedido> findByProducto(int id);
}