package tr.com.huseyinaydin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tr.com.huseyinaydin.domain.models.User;
import tr.com.huseyinaydin.dto.request.LoginRequest;
import tr.com.huseyinaydin.dto.response.LoginResponse;
import tr.com.huseyinaydin.infrastructure.security.JwtService;
import tr.com.huseyinaydin.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmailAddress(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByEmailAddress(request.getEmailAddress())
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı!"));

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("userId", user.getId());

        var jwtToken = jwtService.generateToken(extraClaims, user.getEmailAddress());

        return LoginResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .userName(user.getUserName())
                .token(jwtToken)
                .build();
    }
}
