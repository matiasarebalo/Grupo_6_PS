package G6.PS.Ecommerce.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	@Column(name = "talle", nullable = false)
	private String talle;
	@Column(name = "color", nullable = false)
	private String color;
	@JsonManagedReference
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private SubCategoria subCategoria;
	@Column(name = "sku", nullable = false)
	private String sku;
	@Column(name = "urlImagen", nullable = false)
	private String urlImagen;
	@Column(name = "precio", nullable = false)
	private float precio;
	@Column(name = "valoracion")
	private int valoracion;
	
	public Producto() {}

	public Producto(int id, String descripcionCorta, String descripcionLarga, String talle, String color,
			SubCategoria subCategoria, String sku, String urlImagen, float precio, int valoracion) {
		super();
		this.id = id;
		this.descripcionCorta = descripcionCorta;
		this.descripcionLarga = descripcionLarga;
		this.talle = talle;
		this.color = color;
		this.subCategoria = subCategoria;
		this.sku = sku;
		this.urlImagen = urlImagen;
		this.precio = precio;
		this.valoracion = valoracion;
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

	public String getTalle() {
		return talle;
	}

	public void setTalle(String talle) {
		this.talle = talle;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
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

	public int getValoracion() {
		return valoracion;
	}

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}
}
