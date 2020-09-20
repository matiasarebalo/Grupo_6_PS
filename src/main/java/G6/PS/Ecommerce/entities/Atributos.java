package G6.PS.Ecommerce.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "atributos")
public class Atributos {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "atributo", nullable = false, length = 40)
    private String atributo;

    @ManyToMany(mappedBy = "prodAtributos")
    List<Producto> productos;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "atributoValor_id", referencedColumnName = "id")
    private AtributoValor  atributoValor;
    
    public Atributos(){
        
    }

    public Atributos(int id, String atributo, AtributoValor atributoValor) {
        super();
        this.id = id;
        this.atributo = atributo;
        this.atributoValor = atributoValor;
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

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public AtributoValor getAtributoValor() {
        return atributoValor;
    }

    public void setAtributoValor(AtributoValor atributoValor) {
        this.atributoValor = atributoValor;
    }

    
    
}
