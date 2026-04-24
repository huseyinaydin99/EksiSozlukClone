package tr.com.huseyinaydin.domain.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "entry_vote")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EntryVote extends BaseEntity {
    @Enumerated(EnumType.ORDINAL)
    private VoteType voteType;

    @ManyToOne
    @JoinColumn(name = "entry_id")
    private Entry entry;

    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private User createdBy;
}
