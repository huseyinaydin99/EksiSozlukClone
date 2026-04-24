package tr.com.huseyinaydin.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.huseyinaydin.domain.models.User;
import tr.com.huseyinaydin.infrastructure.exceptions.BusinessException;
import tr.com.huseyinaydin.infrastructure.exceptions.NotFoundException;
import tr.com.huseyinaydin.repository.UserRepository;
import tr.com.huseyinaydin.service.interfaces.UserService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public User create(User user) {
        if (userRepository.findByEmailAddress(user.getEmailAddress()).isPresent()) {
            throw new BusinessException("User with this email already exists.");
        }
        if (userRepository.findByUserName(user.getUsername()).isPresent()) {
            throw new BusinessException("User with this username already exists.");
        }
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User update(User user) {
        if (!userRepository.existsById(user.getId())) {
            throw new NotFoundException("User not found.");
        }
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("User not found.");
        }
        userRepository.deleteById(id);
    }

    @Override
    public User getById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));
    }

    @Override
    public User getByUserName(String userName) {
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> new NotFoundException("User not found with username: " + userName));
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
