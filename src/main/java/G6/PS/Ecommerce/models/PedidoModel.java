package G6.PS.Ecommerce.models;

import G6.PS.Ecommerce.entities.Comentario;

public class PedidoModel {

	private int id;
	private ProductoModel producto;
	private ComentarioModel comentario;
	private String direccion;
	private double costo;
	private String codigoPromocion;
	private String metodoDePago;
	private Boolean aceptado;

	public PedidoModel() {
	}

	public PedidoModel(int id, ProductoModel producto, ComentarioModel comentario, String direccion, double costo,
			String codigoPromocion, String metodoDePago, Boolean aceptado) {
		super();
		this.id = id;
		this.producto = producto;
		this.comentario = comentario;
		this.direccion = direccion;
		this.costo = costo;
		this.codigoPromocion = codigoPromocion;
		this.metodoDePago = metodoDePago;
		this.aceptado = aceptado;
	}

	public PedidoModel(int id, ProductoModel producto, ComentarioModel comentario, String direccion, double costo,
			String codigoPromocion, String metodoDePago) {
		super();
		this.id = id;
		this.producto = producto;
		this.comentario = comentario;
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

	public ProductoModel getProducto() {
		return producto;
	}

	public void setProducto(ProductoModel producto) {
		this.producto = producto;
	}

	public ComentarioModel getComentario() {
		return comentario;
	}

	public void setComentario(ComentarioModel comentario) {
		this.comentario = comentario;
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
