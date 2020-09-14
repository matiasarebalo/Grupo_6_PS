package G6.PS.Ecommerce.models;

import java.util.List;

public class CategoriaModel {
	
	private int id;
	private String categoria;
	private List<SubCategoriaModel> subcategoria;
	
	
	public CategoriaModel() {	}
	
	public CategoriaModel(int id, String categoria) {
		super();
		this.id = id;
		this.categoria = categoria;
	}
	
	public CategoriaModel(int id, String categoria, List<SubCategoriaModel> subcategoria) {
		super();
		this.id = id;
		this.categoria = categoria;
		this.subcategoria = subcategoria;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public List<SubCategoriaModel> getSubcategoria() {
		return subcategoria;
	}
	public void setSubcategoria(List<SubCategoriaModel> subcategoria) {
		this.subcategoria = subcategoria;
	}
	
	
	

}
