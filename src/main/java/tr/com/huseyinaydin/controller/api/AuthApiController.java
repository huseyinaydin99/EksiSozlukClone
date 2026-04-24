package tr.com.huseyinaydin.controller.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.huseyinaydin.application.dto.auth.AuthenticationResponse;
import tr.com.huseyinaydin.application.dto.auth.LoginRequest;
import tr.com.huseyinaydin.application.dto.auth.RegisterRequest;
import tr.com.huseyinaydin.controller.base.BaseApiController;

@RequestMapping("/api/v1/auth")
public class AuthApiController extends BaseApiController {

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
