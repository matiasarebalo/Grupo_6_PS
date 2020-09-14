package G6.PS.Ecommerce.models;

import java.util.List;

public class SubCategoriaModel {
	
	private int id;
	private String subCategoria;
	private List<ProductoModel> productoModel;
	

	public SubCategoriaModel() {}
	
	public SubCategoriaModel(int id, String subCategoria) {
		super();
		this.id = id;
		this.subCategoria = subCategoria;
	}
	
	
	public SubCategoriaModel(int id, String subCategoria, List<ProductoModel> productoModel) {
		super();
		this.id = id;
		this.subCategoria = subCategoria;
		this.productoModel = productoModel;
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
	
	

}
