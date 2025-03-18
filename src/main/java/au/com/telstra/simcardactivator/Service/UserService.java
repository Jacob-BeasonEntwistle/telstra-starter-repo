package au.com.telstra.simcardactivator.Service;

import au.com.telstra.simcardactivator.API.Model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class UserService {

    private final List<User> userList;
    private final RestTemplate restTemplate;

    public UserService() {
        this.restTemplate = new RestTemplate();
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

    public boolean activateSim(String iccid){
        String url = "http://localhost:8444/actuate";

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("iccid", iccid);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(url, requestEntity, Map.class);

        if (response.getBody() != null && response.getBody().containsKey("success")) {
            return (Boolean) response.getBody().get("success");
        }
        return false;
    }
}
