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


@Entity
@Table(name = "pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private Producto producto;
	@Column(name = "direccion")
	private String direccion;
	@Column(name = "costo")
	private double costo;
	@Column(name = "codigopromocion")
	private String codigoPromocion;
	@Column(name = "metododepago")
	private String metodoDePago;
	@Column(name = "aceptado")
	private Boolean aceptado;

	public Pedido() {
		super();
	}
	
	public Pedido(int id, Producto producto, String direccion, double costo, String codigoPromocion, String metodoDePago) {
		super();
		this.id = id;
		this.producto = producto;
		this.direccion = direccion;
		this.costo = costo;
		this.codigoPromocion = codigoPromocion;
		this.metodoDePago = metodoDePago;
		this.aceptado = false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public String getCodigoPromocion() {
		return codigoPromocion;
	}

	public void setCodigoPromocion(String codigoPromocion) {
		this.codigoPromocion = codigoPromocion;
	}

	public String getMetodoDePago() {
		return metodoDePago;
	}

	public void setMetodoDePago(String metodoDePago) {
		this.metodoDePago = metodoDePago;
	}

	public Boolean getAceptado() {
		return aceptado;
	}

	public void setAceptado(Boolean aceptado) {
		this.aceptado = aceptado;
	}
	
}
