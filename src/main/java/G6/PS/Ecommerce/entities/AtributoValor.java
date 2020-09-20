package G6.PS.Ecommerce.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name = "atributoValor")
public class AtributoValor {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "valor", nullable = false, length = 40)
    private String valor;

    @OneToOne(mappedBy = "atributoValor")
	private Atributos atributo;

    public AtributoValor(){}

    public AtributoValor(int id, String valor ) {
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

    public Atributos getAtributo() {
        return atributo;
    }

    public void setAtributo(Atributos atributo) {
        this.atributo = atributo;
    }


}
