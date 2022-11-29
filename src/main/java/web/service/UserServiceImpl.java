package web.service;

import web.dao.UserDAOImpl;
import web.dao.UserDao;
import web.model.User;

import java.util.List;

public class UserServiceImpl implements UserService{

    private UserDao userDao = new UserDAOImpl();

    @Override
    public List<User> allUsers() {
        return userDao.allUsers();
    }

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public void delete(int id) {
        userDao.delete(id);
    }

    @Override
    public void edit(User user, int id) {
        userDao.edit(user, id);
    }

    @Override
    public User getById(int id) {
        return userDao.getById(id);
    }
}
