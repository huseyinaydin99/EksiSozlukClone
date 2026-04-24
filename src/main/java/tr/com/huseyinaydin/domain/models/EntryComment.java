package tr.com.huseyinaydin.domain.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "entry_comment")
@EqualsAndHashCode(callSuper = true)
public class EntryComment extends BaseEntity {
    @Column(length = 2000)
    private String content;

    @ManyToOne
    @JoinColumn(name = "entry_id")
    private Entry entry;

    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private User createdBy;
}
