package G6.PS.Ecommerce.models;

import java.util.List;

public class AtributosModel {

    private int id;

    private String atributo;
    private ProductoModel producto;
    private AtributoValorModel atributoValor;

    public AtributosModel(){
        
    }

    public AtributosModel(int id, String atributo, AtributoValorModel atributoValor, ProductoModel producto) {
        super();
        this.id = id;
        this.atributo = atributo;
        this.atributoValor = atributoValor;
        this.setProducto(producto);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAtributo() {
        return atributo;
    }

    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }

    public AtributoValorModel getAtributoValor() {
        return atributoValor;
    }

    public void setAtributoValor(AtributoValorModel atributoValor) {
        this.atributoValor = atributoValor;
    }

	public ProductoModel getProducto() {
		return producto;
	}

	public void setProducto(ProductoModel producto) {
		this.producto = producto;
	}
    
}
