package G6.PS.Ecommerce.models;


public class AtributosModel {

    private int id;

    private String atributo;
    
    private String valor;
    
    private ProductoModel producto;
   
    public AtributosModel(){
        
    }

    public AtributosModel(int id, String atributo, String valor, ProductoModel producto) {
        super();
        this.id = id;
        this.atributo = atributo;
        this.valor = valor;
        this.producto=producto;
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

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

	public ProductoModel getProducto() {
		return producto;
	}

	public void setProducto(ProductoModel producto) {
		this.producto = producto;
	}
    
}
