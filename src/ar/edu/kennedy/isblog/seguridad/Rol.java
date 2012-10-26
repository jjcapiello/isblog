package ar.edu.kennedy.isblog.seguridad;

import org.springframework.security.core.GrantedAuthority;

public enum Rol implements GrantedAuthority {
	ADMIN (0),
    USER (1);

	private final int bit;
	 
	Rol(int bit) {
        this.bit = bit;
    }

	@Override
	public String getAuthority() {
		return toString();
	}

}
