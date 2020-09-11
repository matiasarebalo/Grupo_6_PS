package G6.PS.Ecommerce.services.implementations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import G6.PS.Ecommerce.entities.UserRole;
import G6.PS.Ecommerce.repositories.IUserRepository;

@Service("userService")
public class UserService implements UserDetailsService {

	@Autowired
	@Qualifier("userRepository")
	private IUserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		G6.PS.Ecommerce.entities.User user = userRepository.findByUsernameAndFetchUserRolesEagerly(username);
		return (UserDetails) buildUser(user, buildGrantedAuthorities(user.getUserRoles()));
	}
	
	private User buildUser(G6.PS.Ecommerce.entities.User user, List<GrantedAuthority> grantedAuthorities) {
		return new User(user.getUsername(), user.getPassword(), user.isEnabled(),
						true, true, true, 
						grantedAuthorities);
	}
	
	private List<GrantedAuthority> buildGrantedAuthorities(Set<UserRole> userRoles) {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
		for(UserRole userRole: userRoles) {
			grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getRole()));
		}
		return new ArrayList<GrantedAuthority>(grantedAuthorities);
	}
	
	
	public G6.PS.Ecommerce.entities.User save(G6.PS.Ecommerce.entities.User user) {	
		return userRepository.save(user);
	}
	
	public boolean existe(G6.PS.Ecommerce.entities.User user) {
		G6.PS.Ecommerce.entities.User userAux = userRepository.findByUsername(user);
		if ( userAux == null) {
			return false;
		} else {
		  return true;	
		}
	}
		
		
}
	
