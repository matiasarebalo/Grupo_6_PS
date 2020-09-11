package G6.PS.Ecommerce.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import G6.PS.Ecommerce.entities.User;

@Repository("userRepository")
public interface IUserRepository extends JpaRepository<User, Serializable> {

	@Query("SELECT u FROM User u JOIN FETCH u.userRoles WHERE u.username = (:username)")
	public abstract User findByUsernameAndFetchUserRolesEagerly(@Param("username") String username);

	public User save(org.springframework.security.core.userdetails.User user);

	public abstract User findByUsername(User user);	
	
}
