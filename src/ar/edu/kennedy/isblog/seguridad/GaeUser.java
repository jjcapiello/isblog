package ar.edu.kennedy.isblog.seguridad;

import java.io.Serializable;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;

public class GaeUser implements Serializable {
    private final String userId;
    private final String email;
    private final String nickname;
    private final String nombre;
    
    // Roles 
    private final Set<AppRole> authorities;
    
    private final boolean habilitado;

    /**
     * Pre-registration 
     */
    public GaeUser(String userId, String nickname, String email) {
        this.userId = userId;
        this.nickname = nickname;
        this.authorities = EnumSet.of(AppRole.NUEVO_USER);
        this.nombre = null;
        this.email = email;
        this.habilitado = true;
    }

    /**
     * Post-registration
     */
    public GaeUser(String userId, String nickname, String email, String nombre, Set<AppRole> authorities, boolean enabled) {
        this.userId = userId;
        this.nickname = nickname;
        this.email = email;
        this.authorities = authorities;
        this.nombre = nombre;
        this.habilitado= enabled;
    }

    public String getUserId() {
        return userId;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getNombre() {
        return nombre;
    }


    public boolean isHabilitado() {
        return habilitado;
    }

    public Collection<AppRole> getAuthorities() {
        return authorities;
    }

    @Override
    public String toString() {
        return "GaeUser{" +
                "userId='" + userId + '\'' +
                ", nickname='" + nickname + '\'' +
                ", nombre='" + nombre + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}