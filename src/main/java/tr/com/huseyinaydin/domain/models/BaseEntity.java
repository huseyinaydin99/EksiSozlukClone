package tr.com.huseyinaydin.domain.models;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    private UUID id;

    private LocalDateTime createDate;

    @PrePersist
    public void prePersist() {
        if (id == null) {
            id = UUID.randomUUID();
        }
        if (createDate == null) {
            createDate = LocalDateTime.now();
        }
    }
}
