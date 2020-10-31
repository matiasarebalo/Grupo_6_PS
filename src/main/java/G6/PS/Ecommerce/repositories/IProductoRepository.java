package G6.PS.Ecommerce.repositories;

import java.io.Serializable;
import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import G6.PS.Ecommerce.entities.Producto;
import G6.PS.Ecommerce.models.ProductoModel;

@Repository("productoRepository")
public interface IProductoRepository extends JpaRepository<Producto, Serializable>{
	
	public abstract Producto findById(int id);
	
	@Query(nativeQuery=true,value="select * from producto where destacado = true and visible = true")
	public List<Producto> findDestacados();
	
	@Query(nativeQuery=true,value="select  * from producto where upper(sku) =(:sku)")
	public Producto findBySku(String sku);
	
	@Query(nativeQuery=true,value="Select * from producto where sub_categoria_id=(:id) and visible = true")
	public List<Producto> findBySubCategoria(int id);
	
	@Query(nativeQuery=true,value="Select * from producto where sub_categoria_id=(:id_sub) and id!=(:id_articulo) and visible = true")
	public List<Producto> findRelacionados(int id_articulo,int id_sub);

	public  List<Producto> findBydescripcionCortaContainingIgnoreCase(String producto);
	
	@Query(nativeQuery=true,value="select * from producto p where p.sub_categoria_id in (select s.id from subcategoria s, categoria c where s.categoria_id = c.id and c.id = (:id)) and visible = true")
	public List<Producto> findByCategoria(int id);
	
	@Query(nativeQuery=true,value="select * from producto p where p.sub_categoria_id in (select s.id from subcategoria s, categoria c where s.categoria_id = c.id and c.id = (:id)) and visible = true")
	Page<Producto> findPageByCategoria(Pageable pageable,int id);
	
	@Query(nativeQuery=true,value="select * from producto p where p.sub_categoria_id  = (:id) and visible = true")
	Page<Producto> findPageBySubCategoria(Pageable pageable,int id);

	@Query(nativeQuery=true,value="Select * from producto where promocion = true and visible = true")
	public List<Producto> findEnPromocion();
	
	
	//agregar cuando tengamos pedidos para ver si el producto tiene un pedido en curso o ya realizado.	
	//	@Query(nativeQuery=true,value=" Select * from producto p inner join lote l on p.id=l.producto_id inner join pedido pe on pe.producto_id=p.id where l.producto_id=(:id) or pe.producto_id=(:id)")
	//	public List<Producto> findIfExist(int id);

}
