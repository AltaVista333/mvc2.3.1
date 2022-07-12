/* (C)2022 */
package web.dao;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<User> getAllUsers() {
        return em.createQuery("Select u from User u", User.class)
            .getResultList();
    }

    @Override
    @Transactional
    public void addUser(User user) {
        em.persist(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        User user = em.find(User.class, id);
        em.remove(user);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return Optional.ofNullable(em.find(User.class, id));
    }

    @Override
    @Transactional
    public void updateUserById(User user) {
        Optional.ofNullable(em.find(User.class, user.getId()))
            .ifPresent(x -> em.merge(x));
    }
}
