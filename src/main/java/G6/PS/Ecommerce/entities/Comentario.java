package G6.PS.Ecommerce.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "comentario")
public class Comentario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "comentario")
	private String comentario;
	@Column(name = "fechaComentario")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaComentario;
	@OneToOne
	private Pedido pedido;
	private double valoracion;

	
	public Comentario() {}
	
	public Comentario(int id, String comentario, Date fechaComentario, Pedido pedido, double valoracion) {
		super();
		this.id = id;
		this.comentario = comentario;
		this.fechaComentario = fechaComentario;
		this.pedido = pedido;
		this.valoracion = valoracion;
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

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public double getValoracion() {
		return valoracion;
	}

	public void setValoracion(double valoracion) {
		this.valoracion = valoracion;
	}

	
}
