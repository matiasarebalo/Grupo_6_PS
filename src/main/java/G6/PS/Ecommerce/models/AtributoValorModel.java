package G6.PS.Ecommerce.models;

public class AtributoValorModel {

    private int id;
    private String valor;
    private AtributosModel atributo;
    
    public AtributoValorModel(){}

    public AtributoValorModel(int id, String valor) {
        this.id = id;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public AtributosModel getAtributo() {
        return atributo;
    }

    public void setAtributo(AtributosModel atributo) {
        this.atributo = atributo;
    }

    
}
