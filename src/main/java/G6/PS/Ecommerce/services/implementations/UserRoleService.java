package G6.PS.Ecommerce.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import G6.PS.Ecommerce.entities.UserRole;
import G6.PS.Ecommerce.repositories.IUserRoleRepository;
import G6.PS.Ecommerce.services.IUserRoleService;

@Service("userRoleService")
public class UserRoleService implements  IUserRoleService{

	@Autowired
	@Qualifier("userRoleRepository")
	private IUserRoleRepository userRoleRepository;	
	
	public UserRole insertOrUpdate(UserRole role) {		
		UserRole roleAux = userRoleRepository.save(role);
		return roleAux;
	}
	
		
	
	
}
