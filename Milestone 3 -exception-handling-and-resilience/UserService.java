import java.util.HashMap;
import java.util.Map;

public class UserService {
    private final Map<String, String> users = new HashMap<>();

    public UserService() {
        users.put("ali123", "Ali Ahmed");
        users.put("sara456", "Sara Khan");
    }

    public String findUser(String username) {
        String user = users.get(username);

        if (user == null) {
            // Throw custom exception when business rule fails.
            throw new UserNotFoundException("No user found with username: " + username);
        }

        return user;
    }
}
