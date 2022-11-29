package web.service;

import web.model.User;

import java.util.List;

public interface UserService {

    List<User> allUsers();
    void add(User user);
    void delete(int id);
    void edit(User user, int id);
    User getById(int id);

}
