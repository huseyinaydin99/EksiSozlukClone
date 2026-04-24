package tr.com.huseyinaydin.controller.api;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.huseyinaydin.application.dto.auth.AuthenticationResponse;
import tr.com.huseyinaydin.application.dto.auth.LoginRequest;
import tr.com.huseyinaydin.application.dto.auth.RegisterRequest;
import tr.com.huseyinaydin.controller.base.BaseApiController;

@RestController
@RequestMapping("/api/users")
public class UserController extends BaseApiController {

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }
}
