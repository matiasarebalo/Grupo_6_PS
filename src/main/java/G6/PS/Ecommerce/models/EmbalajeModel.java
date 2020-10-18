package G6.PS.Ecommerce.models;

public class EmbalajeModel {
	private int id;
	private float peso;
	private float ancho;
	private float alto;
	private float profundidad;
	private float precio;
	
	public EmbalajeModel() {}

	public EmbalajeModel(int id, float peso, float ancho, float alto, float profundidad, float precio) {
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
	
}
