package tr.com.huseyinaydin.domain.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String userName;
    private String password;
    private boolean emailConfirmed;

    @OneToMany(mappedBy = "createdBy")
    private Collection<Entry> entries;

    @OneToMany(mappedBy = "createdBy")
    private Collection<EntryComment> entryComments;

    @OneToMany(mappedBy = "createdUser")
    private Collection<EntryFavorite> entryFavorites;

    @OneToMany(mappedBy = "createdUser")
    private Collection<EntryCommentFavorite> entryCommentFavorites;
}
