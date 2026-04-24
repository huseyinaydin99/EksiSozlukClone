package tr.com.huseyinaydin.domain.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "entry_favorite")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EntryFavorite extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "entry_id")
    private Entry entry;

    @ManyToOne
    @JoinColumn(name = "created_user_id")
    private User createdUser;
}
