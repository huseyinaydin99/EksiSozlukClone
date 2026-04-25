package tr.com.huseyinaydin.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tr.com.huseyinaydin.application.dto.UserDetailDto;
import tr.com.huseyinaydin.domain.models.User;

@Mapper(componentModel = "spring", uses = {EntryMapper.class})
public interface UserMapper {
    UserDetailDto toDetailDto(User user);
}
