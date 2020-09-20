package G6.PS.Ecommerce.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "categoria")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String categoria;

	@JsonBackReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "categoria")
	private List<SubCategoria> subCategorias;
	
	
	public Categoria() {}
	
	public Categoria(int id, String categoria) {
		super();
		this.id = id;
		this.categoria = categoria;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public List<SubCategoria> getSubCategorias() {
		return subCategorias;
	}
	public void setSubCategorias(List<SubCategoria> subCategorias) {
		this.subCategorias = subCategorias;
	}
	
	
	
	
	
	
}
