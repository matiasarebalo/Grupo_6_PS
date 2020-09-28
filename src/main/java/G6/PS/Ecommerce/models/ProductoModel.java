package G6.PS.Ecommerce.models;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class ProductoModel {
	
	private int id;
	private String descripcionCorta;
	private String descripcionLarga;
	private SubCategoriaModel subCategoria;
	private String sku;
	private String urlImagen;
	private float precio;
	private boolean destacado;
	private List<AtributosModel> prodAtributos;
	private MultipartFile file;

	
	public ProductoModel() {}

	public ProductoModel(int id, String descripcionCorta, String descripcionLarga, SubCategoriaModel subCategoria, String urlImagen, String sku,
					float precio, boolean destacado) {
		super();
		this.id = id;
		this.descripcionCorta = descripcionCorta;
		this.descripcionLarga = descripcionLarga;
		this.subCategoria = subCategoria;
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

	public boolean getDestacado() {
		return destacado;
	}

	public void setDestacado(boolean destacado) {
		this.destacado = destacado;
	}

	public SubCategoriaModel getSubCategoria() {
		return subCategoria;
	}

	public void setSubCategoria(SubCategoriaModel subCategoria) {
		this.subCategoria = subCategoria;
	}

	public List<AtributosModel> getProdAtributos() {
		return prodAtributos;
	}

	public void setProdAtributos(List<AtributosModel> prodAtributos) {
		this.prodAtributos = prodAtributos;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	
}
