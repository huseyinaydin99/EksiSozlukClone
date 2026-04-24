package tr.com.huseyinaydin.domain.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;

@Data
@Entity
@Table(name = "entry")
@EqualsAndHashCode(callSuper = true)
public class Entry extends BaseEntity {
    private String subject;
    
    @Column(length = 5000)
    private String content;

    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private User createdBy;

    @OneToMany(mappedBy = "entry")
    private Collection<EntryComment> entryComments;
}
