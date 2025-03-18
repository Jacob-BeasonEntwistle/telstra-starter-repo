package au.com.telstra.simcardactivator.API.Controller;

import au.com.telstra.simcardactivator.API.Model.User;
import au.com.telstra.simcardactivator.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/user")
    public User getUser(@RequestParam String iccid){
        Optional<User> user = userService.getUser(iccid);
        return (User) user.orElse(null);
    }

    @PostMapping("/newUser")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }
}
