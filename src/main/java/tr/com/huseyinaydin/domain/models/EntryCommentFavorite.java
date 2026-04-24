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
@Table(name = "entry_comment_favorite")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EntryCommentFavorite extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "entry_comment_id")
    private EntryComment entryComment;

    @ManyToOne
    @JoinColumn(name = "created_user_id")
    private User createdUser;
}
