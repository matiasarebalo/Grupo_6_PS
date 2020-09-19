package G6.PS.Ecommerce.models;

public class ProductoModel {
	
	private int id;
	private String descripcionCorta;
	private String descripcionLarga;
	private String talle;
	private String color;
	private SubCategoriaModel subCategoriaModel;
	private String sku;
	private String urlImagen;
	private float precio;
	private String destacado;
	//private List<String> comentarios; VALIDAR BIEN
	
	public ProductoModel() {}

	public ProductoModel(int id, String descripcionCorta, String descripcionLarga, String talle, String color,
			SubCategoriaModel subCategoriaModel, String sku, String urlImagen, float precio, String destacado) {
		super();
		this.id = id;
		this.descripcionCorta = descripcionCorta;
		this.descripcionLarga = descripcionLarga;
		this.talle = talle;
		this.color = color;
		this.subCategoriaModel = subCategoriaModel;
		this.sku = sku;
		this.urlImagen = urlImagen;
		this.precio = precio;
		this.destacado = destacado;
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

	public SubCategoriaModel getSubCategoriaModel() {
		return subCategoriaModel;
	}

	public void setSubCategoriaModel(SubCategoriaModel subCategoriaModel) {
		this.subCategoriaModel = subCategoriaModel;
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

	public String getDestacado() {
		return destacado;
	}

	public void setDestacado(String destacado) {
		this.destacado = destacado;
	}
}
