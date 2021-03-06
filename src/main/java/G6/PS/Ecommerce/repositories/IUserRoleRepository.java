package G6.PS.Ecommerce.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import G6.PS.Ecommerce.entities.UserRole;

@Repository("userRoleRepository")
public interface IUserRoleRepository extends JpaRepository<UserRole, Serializable>{

	@SuppressWarnings("unchecked")
	public UserRole save(UserRole role);

}
