package G6.PS.Ecommerce.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "embalaje")
public class Embalaje {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "peso")
	private float peso;
	@Column(name = "ancho")
	private float ancho;
	@Column(name = "alto")
	private float alto;
	@Column(name = "profundidad")
	private float profundidad;
	@Column(name = "precio")
	private float precio;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "embalaje")
	private List<Pedido> pedidos;
	
	public Embalaje() {
	}

	public Embalaje(int id, float peso, float ancho, float alto, float profundidad, float precio) {
		super();
		this.id = id;
		this.peso = peso;
		this.ancho = ancho;
		this.alto = alto;
		this.profundidad = profundidad;
		this.precio = precio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public float getAncho() {
		return ancho;
	}

	public void setAncho(float ancho) {
		this.ancho = ancho;
	}

	public float getAlto() {
		return alto;
	}

	public void setAlto(float alto) {
		this.alto = alto;
	}

	public float getProfundidad() {
		return profundidad;
	}

	public void setProfundidad(float profundidad) {
		this.profundidad = profundidad;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
}
