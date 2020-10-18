package G6.PS.Ecommerce.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import G6.PS.Ecommerce.entities.Embalaje;

@Repository("embalajeRepository")
public interface IEmbalajeRepository extends  JpaRepository<Embalaje, Serializable> {
	public abstract Embalaje findById(int id);
}
