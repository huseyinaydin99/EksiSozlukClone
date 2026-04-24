package tr.com.huseyinaydin.service.interfaces;

import tr.com.huseyinaydin.domain.models.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User create(User user);
    User update(User user);
    void delete(UUID id);
    User getById(UUID id);
    User getByUserName(String userName);
    List<User> getAll();
}
