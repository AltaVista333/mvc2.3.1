package web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.model.User;
import web.model.UserDto;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void deleteUserById(Long id) {
        userDao.deleteUser(id);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public void updateUserById(UserDto dto) {
        userDao.updateUserById(dto);
    }


}
