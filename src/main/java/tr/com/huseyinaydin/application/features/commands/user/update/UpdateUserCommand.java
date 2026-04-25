package tr.com.huseyinaydin.application.features.commands.user.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tr.com.huseyinaydin.infrastructure.mediator.Request;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserCommand implements Request<Boolean> {
    private UUID id;
    private String userName;
    private String firstName;
    private String lastName;
    private String emailAddress;
}
