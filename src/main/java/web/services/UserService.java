package web.services;

import web.model.User;
import web.model.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void addUser(User user);
    List<User> getAllUsers();
    void deleteUserById(Long id);
    Optional<User> getUserById(Long id);
    void updateUserById(UserDto dto);

}
