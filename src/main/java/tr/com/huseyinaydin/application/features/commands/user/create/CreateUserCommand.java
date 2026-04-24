package tr.com.huseyinaydin.application.features.commands.user.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tr.com.huseyinaydin.infrastructure.mediator.Request;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserCommand implements Request<UUID> {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String userName;
    private String password;
}
