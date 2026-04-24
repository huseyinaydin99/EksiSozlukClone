package tr.com.huseyinaydin.domain.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;

@Data
@Entity
@Table(name = "user")
@EqualsAndHashCode(callSuper = true)
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
}
