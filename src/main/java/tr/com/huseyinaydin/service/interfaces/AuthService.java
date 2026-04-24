package tr.com.huseyinaydin.service.interfaces;

import tr.com.huseyinaydin.application.dto.auth.AuthenticationResponse;
import tr.com.huseyinaydin.application.dto.auth.LoginRequest;
import tr.com.huseyinaydin.application.dto.auth.RegisterRequest;

public interface AuthService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse login(LoginRequest request);
}
