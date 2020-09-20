package G6.PS.Ecommerce.repositories;
import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import G6.PS.Ecommerce.entities.Atributos;

@Repository("atributosRepository")
public interface IAtributosRepository extends JpaRepository<Atributos, Serializable>{
    
    public abstract Atributos findById(int id);
}
