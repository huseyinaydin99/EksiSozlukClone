package tr.com.huseyinaydin.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @Email
    @NotBlank
    private String emailAddress;

    @NotBlank
    private String password;
}
