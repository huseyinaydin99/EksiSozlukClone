package tr.com.huseyinaydin.application.features.queries.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tr.com.huseyinaydin.application.dto.UserDetailDto;
import tr.com.huseyinaydin.infrastructure.mediator.Request;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserDetailQuery implements Request<UserDetailDto> {
    private String userName;
}
