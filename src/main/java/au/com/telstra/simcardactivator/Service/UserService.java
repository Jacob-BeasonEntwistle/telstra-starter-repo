package au.com.telstra.simcardactivator.Service;

import au.com.telstra.simcardactivator.API.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final List<User> userList;

    public UserService() {
        userList = new ArrayList<>();

        User user1 = new User("macaroni", "frank@gmail.com");
        User user2 = new User("stationary", "bobbert@gmail.com");
        User user3 = new User("greenhouse", "simeon@gmail.com");

        userList.addAll(Arrays.asList(user1, user2, user3));
    }

    public Optional<User> getUser(String iccid) {
        Optional<User> optional = Optional.empty();
        for (User user: userList) {
            if (iccid.equals(user.getIccid())){
                optional = Optional.of(user);
                return optional;
            }
        }
        return optional;
    }

    public User addUser(User user){
        userList.add(user);
        return user;
    }
}
