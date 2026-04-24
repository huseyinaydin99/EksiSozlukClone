package tr.com.huseyinaydin.application.features.commands.user.create;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import tr.com.huseyinaydin.domain.models.User;
import tr.com.huseyinaydin.infrastructure.exceptions.BusinessException;
import tr.com.huseyinaydin.infrastructure.mediator.RequestHandler;
import tr.com.huseyinaydin.repository.UserRepository;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CreateUserCommandHandler implements RequestHandler<CreateUserCommand, UUID> {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UUID handle(CreateUserCommand request) {
        if (userRepository.findByEmailAddress(request.getEmailAddress()).isPresent()) {
            throw new BusinessException("User already exists with this email!");
        }
        
        if (userRepository.findByUserName(request.getUserName()).isPresent()) {
            throw new BusinessException("User already exists with this username!");
        }

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmailAddress(request.getEmailAddress());
        user.setUserName(request.getUserName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("USER");
        user.setEmailConfirmed(false);

        User savedUser = userRepository.save(user);
        return savedUser.getId();
    }
}
