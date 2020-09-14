package G6.PS.Ecommerce.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "subcategoria")
public class SubCategoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String subCategoria;
	@JsonBackReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "subcategoria")
	private List<Producto> producto;
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private Categoria categoria;
	
	public SubCategoria() {	}
	
	
	public SubCategoria(int id, String subCategoria, List<Producto> producto) {
		super();
		this.id = id;
		this.subCategoria = subCategoria;
		this.producto = producto;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSubCategoria() {
		return subCategoria;
	}
	public void setSubCategoria(String subCategoria) {
		this.subCategoria = subCategoria;
	}
	public List<Producto> getProducto() {
		return producto;
	}
	public void setProducto(List<Producto> producto) {
		this.producto = producto;
	}

}
