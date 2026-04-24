package tr.com.huseyinaydin.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tr.com.huseyinaydin.application.dto.auth.AuthenticationResponse;
import tr.com.huseyinaydin.application.dto.auth.LoginRequest;
import tr.com.huseyinaydin.application.dto.auth.RegisterRequest;
import tr.com.huseyinaydin.domain.models.User;
import tr.com.huseyinaydin.infrastructure.exceptions.BusinessException;
import tr.com.huseyinaydin.infrastructure.security.JwtService;
import tr.com.huseyinaydin.repository.UserRepository;
import tr.com.huseyinaydin.service.interfaces.AuthService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        if (userRepository.findByUserName(request.getUserName()).isPresent()) {
            throw new BusinessException("Kullanıcı adı zaten kullanımda");
        }
        if (userRepository.findByEmailAddress(request.getEmailAddress()).isPresent()) {
            throw new BusinessException("E-posta adresi zaten kullanımda");
        }

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmailAddress(request.getEmailAddress());
        user.setUserName(request.getUserName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("USER");
        user.setEmailConfirmed(false);

        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .userName(user.getUsername())
                .build();
    }

    @Override
    public AuthenticationResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUserName(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByUserName(request.getUserName())
                .orElseThrow(() -> new BusinessException("Kullanıcı bulunamadı"));

        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .userName(user.getUsername())
                .build();
    }
}
