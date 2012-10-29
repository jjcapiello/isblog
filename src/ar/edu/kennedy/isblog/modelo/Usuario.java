package ar.edu.kennedy.isblog.modelo;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;

import ar.edu.kennedy.isblog.seguridad.Rol;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Usuario {
    private final String userId;
    private final String email;
    private final String nickname;
    private final String forename;
    private final String surname;
    private final Set<Rol> authorities;
    private final boolean enabled;

    /**
     * Pre-registration constructor.
     *
     * Assigns the user the "NEW_USER" role only.
     */
    public Usuario(String userId, String nickname, String email) {
        this.userId = userId;
        this.nickname = nickname;
        this.authorities = EnumSet.of(Rol.USER);
        this.forename = null;
        this.surname = null;
        this.email = email;
        this.enabled = true;
    }

    /**
     * Post-registration constructor
     */
    public Usuario(String userId, String nickname, String email, String forename, String surname, Set<Rol> authorities, boolean enabled) {
        this.userId = userId;
        this.nickname = nickname;
        this.email = email;
        this.authorities = authorities;
        this.forename = forename;
        this.surname = surname;
        this.enabled= enabled;
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

    public String getForename() {
        return forename;
    }

    public String getSurname() {
        return surname;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Collection<Rol> getAuthorities() {
        return authorities;
    }

    @Override
    public String toString() {
        return "GaeUser{" +
                "userId='" + userId + '\'' +
                ", nickname='" + nickname + '\'' +
                ", forename='" + forename + '\'' +
                ", surname='" + surname + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}