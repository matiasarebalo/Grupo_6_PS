package G6.PS.Ecommerce.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "producto")
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "descripcionCorta", nullable = false, length = 20)
	private String descripcionCorta;

	@Column(name = "descripcionLarga", nullable = false, length = 40)
	private String descripcionLarga;

	@JsonManagedReference
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private SubCategoria subCategoria;
	
	@Column(name = "sku")
	private String sku;

	@Column(name = "urlImagen", nullable = false)
	private String urlImagen;

	@Column(name = "precio", nullable = false)
	private float precio;

	@Column(name = "destacado")
	private boolean destacado;

	
	
	@ManyToMany
	@JoinTable(
		name = "producto_atributo", 
		joinColumns = @JoinColumn(name = "producto_id"), 
		inverseJoinColumns = @JoinColumn(name = "atributo_id"))
    private List<Atributos> prodAtributos;
	
	public Producto() {}

	public Producto(int id, String descripcionCorta, String descripcionLarga, SubCategoria subCategoria, String urlImagen, String sku,
					float precio, boolean destacado, List<Atributos> prodAtributos) {
		super();
		this.id = id;
		this.descripcionCorta = descripcionCorta;
		this.descripcionLarga = descripcionLarga;
		this.subCategoria = subCategoria;
		this.sku = sku;
		this.urlImagen = urlImagen;
		this.precio = precio;
		this.destacado = destacado;
		this.prodAtributos = prodAtributos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcionCorta() {
		return descripcionCorta;
	}

	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}

	public String getDescripcionLarga() {
		return descripcionLarga;
	}

	public void setDescripcionLarga(String descripcionLarga) {
		this.descripcionLarga = descripcionLarga;
	}

	public SubCategoria getSubCategoria() {
		return subCategoria;
	}

	public void setSubCategoria(SubCategoria subCategoria) {
		this.subCategoria = subCategoria;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public boolean getDestacado() {
		return destacado;
	}

	public void setDestacado(boolean destacado) {
		this.destacado = destacado;
	}

	public List<Atributos> getProdAtributos() {
		return prodAtributos;
	}

	public void setProdAtributos(List<Atributos> prodAtributos) {
		this.prodAtributos = prodAtributos;
	}

	
}
