package web.dao;

import java.util.List;
import java.util.Optional;
import web.model.User;

public interface UserDao {

    List<User> getAllUsers();

    void addUser(User user);

    void deleteUser(Long id);

    Optional<User> getUserById(Long id);

    void updateUser(User user);
}
