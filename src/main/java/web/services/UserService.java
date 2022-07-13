package web.services;

import java.util.List;
import java.util.Optional;
import web.model.User;

public interface UserService {

    void addUser(User user);

    List<User> getAllUsers();

    void deleteUserById(Long id);

    Optional<User> getUserById(Long id);

    void updateUserById(User user);
}
