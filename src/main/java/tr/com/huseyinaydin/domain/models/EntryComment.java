package tr.com.huseyinaydin.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "entry_comment")
public class EntryComment extends BaseEntity {
    @Column(length = 2000)
    private String content;

    @ManyToOne
    @JoinColumn(name = "entry_id")
    private Entry entry;

    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private User createdBy;

    @OneToMany(mappedBy = "entryComment")
    private java.util.Collection<EntryCommentVote> entryCommentVotes;

    @OneToMany(mappedBy = "entryComment")
    private java.util.Collection<EntryCommentFavorite> entryCommentFavorites;
}
