package G6.PS.Ecommerce.models;

public class Login {

	private String usuario;
	private String password;
	
	public Login() {}
			
	public Login(String usuario, String password) {		
		this.usuario = usuario;
		this.password = password;
	}
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
		
	
}
