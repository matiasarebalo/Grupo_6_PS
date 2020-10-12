package G6.PS.Ecommerce.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ComentarioModel {
	private int id;
	private String comentario;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaComentario;
	private PedidoModel pedido;

	public ComentarioModel() {}
	
	public ComentarioModel(int id, String comentario, Date fechaComentario, PedidoModel pedido) {
		super();
		this.id = id;
		this.comentario = comentario;
		this.fechaComentario = fechaComentario;
		this.pedido = pedido;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public Date getFechaComentario() {
		return fechaComentario;
	}
	public void setFechaComentario(Date fechaComentario) {
		this.fechaComentario = fechaComentario;
	}

	public PedidoModel getPedido() {
		return pedido;
	}

	public void setPedido(PedidoModel pedido) {
		this.pedido = pedido;
	}

}
