package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;
import org.hibernate.Session;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;

    UserDAOImpl(){}

    @Override
    public List<User> allUsers() {
        List<User> userList = entityManager.createQuery("from User", User.class).getResultList();//работ только с hql, с скл не работ?
        System.out.println(userList.size());
        return userList;
    }

    @Override
    public void add(User user) {
        Session session = entityManager.unwrap(Session.class);
        session.save(user);
    }

    @Override
    public void delete(int id) {
        Session session = entityManager.unwrap(Session.class);
        User delUser = session.get(User.class, id);
        session.delete(delUser);

        /*или запросом hql с динамич подстановк парам:
        Query query = entityManager.createQuery("delete from User where id = :usrId");
        query.setParameter("usrId", id).executeUpdate();
        */
    }

    @Override
    public void edit(User updatedUser, int id) {
        Session session = entityManager.unwrap(Session.class);
        session.update(updatedUser);
    }

    @Override
    public User getById(int id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(User.class, id);
    }
}
