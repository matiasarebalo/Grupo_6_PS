package G6.PS.Ecommerce.models;

import java.util.List;

public class SubCategoriaModel {
	
	private int id;
	private String subCategoria;
	private List<ProductoModel> productoModel;
	private CategoriaModel categoria;

	public SubCategoriaModel() {}
	
	public SubCategoriaModel(String subCategoria) {
		this.subCategoria = subCategoria;
	}
	
	public SubCategoriaModel(int id, String subCategoria, CategoriaModel categoria) {
		super();
		this.id = id;
		this.subCategoria = subCategoria;
		this.categoria = categoria;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubCategoria() {
		return subCategoria;
	}

	public void setSubCategoria(String subCategoria) {
		this.subCategoria = subCategoria;
	}

	public List<ProductoModel> getProductoModel() {
		return productoModel;
	}

	public void setProductoModel(List<ProductoModel> productoModel) {
		this.productoModel = productoModel;
	}

	public CategoriaModel getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaModel categoria) {
		this.categoria = categoria;
	}
	
	

}
