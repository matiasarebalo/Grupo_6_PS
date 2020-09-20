package G6.PS.Ecommerce.repositories;
import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import G6.PS.Ecommerce.entities.AtributoValor;

@Repository("atributoValorRepository")
public interface IAtributoValorRepository extends JpaRepository<AtributoValor, Serializable>{
    
    public abstract AtributoValor findById(int id);
}
