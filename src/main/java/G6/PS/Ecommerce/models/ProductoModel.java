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
	private boolean visible;
	private float descuento;
	private boolean promocion;
	
	private float precioTachado;

	public ProductoModel() {}

	public ProductoModel(int id, String descripcionCorta, String descripcionLarga, SubCategoriaModel subCategoria, String urlImagen, String sku,
					float precio, boolean destacado, boolean visible, float descuento) {
		super();
		this.id = id;
		this.descripcionCorta = descripcionCorta;
		this.descripcionLarga = descripcionLarga;
		this.subCategoria = subCategoria;
		this.sku = sku;
		this.urlImagen = urlImagen;
		this.precio = precio;
		this.destacado = destacado;
		this.visible = visible;
		this.descuento = descuento;
	}

	public ProductoModel(int id, String descripcionCorta, String descripcionLarga, SubCategoriaModel subCategoria, String urlImagen, String sku,
			float precio, boolean destacado, boolean visible, float descuento, boolean promocion) {
		super();
		this.id = id;
		this.descripcionCorta = descripcionCorta;
		this.descripcionLarga = descripcionLarga;
		this.subCategoria = subCategoria;
		this.sku = sku;
		this.urlImagen = urlImagen;
		this.precio = precio;
		this.destacado = destacado;
		this.visible = visible;
		this.descuento = descuento;
		this.promocion = promocion;
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

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public float getDescuento() {
		return descuento;
	}

	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}

	public float getPrecioTachado() {
		return precioTachado;
	}

	public void setPrecioTachado(float precioTachado) {
		this.precioTachado = precioTachado;
	}

	public boolean isPromocion() {
		return promocion;
	}

	public void setPromocion(boolean promocion) {
		this.promocion = promocion;
	}

	
}
