package tr.com.huseyinaydin.application.features.commands.user.password;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tr.com.huseyinaydin.infrastructure.mediator.Request;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeUserPasswordCommand implements Request<Boolean> {
    private UUID userId;
    private String oldPassword;
    private String newPassword;
    private String confirmNewPassword;
}
