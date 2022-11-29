package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class UserDAOImpl implements UserDao{
    

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
