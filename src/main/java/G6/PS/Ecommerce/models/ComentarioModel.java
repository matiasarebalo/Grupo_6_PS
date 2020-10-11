package G6.PS.Ecommerce.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ComentarioModel {
	private int id;
	private String comentario;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaComentario;
	
	public ComentarioModel() {}
	
	public ComentarioModel(int id, String comentario, Date fechaComentario) {
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
