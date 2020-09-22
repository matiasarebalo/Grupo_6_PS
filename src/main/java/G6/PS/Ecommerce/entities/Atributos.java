package G6.PS.Ecommerce.entities;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "atributos")
public class Atributos {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "atributo", nullable = true, length = 40)
    private String atributo;

    
    @JsonManagedReference
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private Producto producto;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "atributoValor_id", referencedColumnName = "id")
    private AtributoValor  atributoValor;
    
    public Atributos(){
        
    }

    public Atributos(int id, String atributo, AtributoValor atributoValor,Producto producto) {
        super();
        this.id = id;
        this.atributo = atributo;
        this.atributoValor = atributoValor;
        this.producto = producto;
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

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public AtributoValor getAtributoValor() {
        return atributoValor;
    }

    public void setAtributoValor(AtributoValor atributoValor) {
        this.atributoValor = atributoValor;
    }

    
    
}
