package web.dao;

import web.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class UserDAOImpl implements UserDao{

    private static final AtomicInteger AUTO_ID = new AtomicInteger(0);
    private static Map<Integer, User> userMap = new HashMap<>();

    static {
        User us1 = new User("vania", "petrov");
        us1.setId(1);
        User us2 = new User("petya", "ivanov");
        us2.setId(2);
        User us3 = new User("masha", "fedotova");
        us3.setId(3);
        User us4 = new User("kira", "bushueva");
        userMap.putIfAbsent(1, us1);
        userMap.putIfAbsent(2, us2);
        userMap.putIfAbsent(3, us3);
        userMap.putIfAbsent(4, us4);
    }


    @Override
    public List<User> allUsers() {
        return new ArrayList<>(userMap.values());
    }

    @Override
    public void add(User user) {
        user.setId(4 + AUTO_ID.getAndIncrement());
        userMap.put(user.getId(), user);
    }

    @Override
    public void delete(int id) {

        userMap.remove(id);
    }

    @Override
    public void edit(User updatedUser, int id) {
        //userMap.put(user.getId(), user);
        User forUpdating = getById(id);
        forUpdating.setFirstName(updatedUser.getFirstName());
        forUpdating.setLastName(updatedUser.getLastName());
        userMap.put(id, forUpdating);
    }

    @Override
    public User getById(int id) {
        return userMap.get(id);
    }
}
