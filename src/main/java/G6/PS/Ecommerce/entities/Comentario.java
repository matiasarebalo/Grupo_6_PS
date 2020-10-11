package G6.PS.Ecommerce.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	public Comentario() {}
	
	public Comentario(int id, String comentario, Date fechaComentario) {
		super();
		this.id = id;
		this.comentario = comentario;
		this.fechaComentario = fechaComentario;
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
}
