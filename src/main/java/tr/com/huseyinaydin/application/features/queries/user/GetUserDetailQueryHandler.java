package tr.com.huseyinaydin.application.features.queries.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tr.com.huseyinaydin.application.dto.UserDetailDto;
import tr.com.huseyinaydin.application.mapper.UserMapper;
import tr.com.huseyinaydin.domain.models.User;
import tr.com.huseyinaydin.infrastructure.exceptions.BusinessException;
import tr.com.huseyinaydin.infrastructure.mediator.RequestHandler;
import tr.com.huseyinaydin.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class GetUserDetailQueryHandler implements RequestHandler<GetUserDetailQuery, UserDetailDto> {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDetailDto handle(GetUserDetailQuery request) {
        User user = userRepository.findByUserName(request.getUserName())
                .orElseThrow(() -> new BusinessException("Kullanıcı bulunamadı."));
        return userMapper.toDetailDto(user);
    }
}
