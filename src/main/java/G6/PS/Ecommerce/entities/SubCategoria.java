package G6.PS.Ecommerce.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "subCategoria")
	private List<Producto> producto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable=false)
	private Categoria categoria;
	
	public SubCategoria() {	}
	
	
	public SubCategoria(int id, String subCategoria, Categoria categoria) {
		super();
		this.id = id;
		this.subCategoria = subCategoria;
		this.categoria = categoria;
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	

}
