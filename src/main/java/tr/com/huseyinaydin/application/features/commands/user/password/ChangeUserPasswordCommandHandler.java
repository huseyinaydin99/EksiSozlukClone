package tr.com.huseyinaydin.application.features.commands.user.password;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import tr.com.huseyinaydin.domain.models.User;
import tr.com.huseyinaydin.infrastructure.exceptions.BusinessException;
import tr.com.huseyinaydin.infrastructure.mediator.RequestHandler;
import tr.com.huseyinaydin.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class ChangeUserPasswordCommandHandler implements RequestHandler<ChangeUserPasswordCommand, Boolean> {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Boolean handle(ChangeUserPasswordCommand request) {
        if (request.getNewPassword() == null || request.getNewPassword().length() < 6) {
            throw new BusinessException("Yeni şifre en az 6 karakter olmalıdır.");
        }

        if (!request.getNewPassword().equals(request.getConfirmNewPassword())) {
            throw new BusinessException("Yeni şifreler birbiriyle uyuşmuyor.");
        }

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new BusinessException("Kullanıcı bulunamadı."));

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new BusinessException("Eski şifre hatalı.");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
        return true;
    }
}
