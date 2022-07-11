package web.dao;

import web.model.User;
import web.model.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<User> getAllUsers();

    void addUser(User user);

    void deleteUser(Long id);

    Optional<User> getUserById(Long id);

    void updateUserById(UserDto dto);
}
