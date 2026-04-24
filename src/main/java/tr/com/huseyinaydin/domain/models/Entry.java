package tr.com.huseyinaydin.domain.models;

import jakarta.persistence.*;
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
@Table(name = "entry")
public class Entry extends BaseEntity {
    private String subject;

    @Column(length = 5000)
    private String content;

    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private User createdBy;

    @OneToMany(mappedBy = "entry")
    private Collection<EntryComment> entryComments;

    @OneToMany(mappedBy = "entry")
    private Collection<EntryVote> entryVotes;

    @OneToMany(mappedBy = "entry")
    private Collection<EntryFavorite> entryFavorites;
}
