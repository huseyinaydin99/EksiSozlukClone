package tr.com.huseyinaydin.application.features.commands.user.update;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tr.com.huseyinaydin.domain.models.User;
import tr.com.huseyinaydin.infrastructure.exceptions.BusinessException;
import tr.com.huseyinaydin.infrastructure.mediator.RequestHandler;
import tr.com.huseyinaydin.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class UpdateUserCommandHandler implements RequestHandler<UpdateUserCommand, Boolean> {
    private final UserRepository userRepository;

    @Override
    public Boolean handle(UpdateUserCommand request) {
        User user = userRepository.findById(request.getId())
                .orElseThrow(() -> new BusinessException("Kullanıcı bulunamadı."));

        user.setUserName(request.getUserName());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmailAddress(request.getEmailAddress());

        userRepository.save(user);
        return true;
    }
}
