package tr.com.huseyinaydin.domain.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "email_confirmation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailConfirmation extends BaseEntity {
    private String oldEmailAddress;
    private String newEmailAddress;
}
