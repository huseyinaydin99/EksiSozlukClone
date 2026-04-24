package tr.com.huseyinaydin.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String userName;
    private String password;
    private String role; // USER, ADMIN vs.
    private boolean emailConfirmed;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role != null ? role : "USER"));
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @OneToMany(mappedBy = "createdBy")
    private Collection<Entry> entries;

    @OneToMany(mappedBy = "createdBy")
    private Collection<EntryComment> entryComments;

    @OneToMany(mappedBy = "createdUser")
    private Collection<EntryFavorite> entryFavorites;

    @OneToMany(mappedBy = "createdUser")
    private Collection<EntryCommentFavorite> entryCommentFavorites;
}
