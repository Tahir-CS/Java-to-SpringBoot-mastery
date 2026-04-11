import java.util.HashMap;

public class SpringSecurityPreview {
    
    public static void main(String[] args) {
        
        MyUserService userService = new MyUserService();
        
        // When a user tries to login with username "ali123"
        try {
            UserDetails user = userService.loadUserByUsername("ali123");
            System.out.println("Logged in: " + user);
        } catch (RuntimeException e) {
            System.out.println("Login failed: " + e.getMessage());
        }
    }
}

// Interface that Spring expects you to implement
interface UserDetailsService {
    UserDetails loadUserByUsername(String username) throws RuntimeException;
}

// Interface representing a logged-in user
interface UserDetails {
    String getUsername();
    String getPassword();
    String getRole();
}

// Your User entity class
class User implements UserDetails {
    private String username;
    private String password;
    private String role;
    
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    
    @Override
    public String getUsername() {
        return username;
    }
    
    @Override
    public String getPassword() {
        return password;
    }
    
    @Override
    public String getRole() {
        return role;
    }
    
    @Override
    public String toString() {
        return "User{" + username + ", role=" + role + "}";
    }
}

// YOUR implementation - This is where YOU tell Spring how to find users
class MyUserService implements UserDetailsService {
    
    // Your database (pretend this is MySQL, MongoDB, etc)
    private HashMap<String, User> database;
    
    public MyUserService() {
        database = new HashMap<>();
        database.put("ali123", new User("ali123", "password123", "STUDENT"));
        database.put("sara456", new User("sara456", "mypassword", "ADMIN"));
        database.put("ahmed789", new User("ahmed789", "secure123", "STUDENT"));
    }
    
    // SPRING CALLS THIS DURING LOGIN
    @Override
    public UserDetails loadUserByUsername(String username) throws RuntimeException {
        // Your job: fetch from database
        if (database.containsKey(username)) {
            return database.get(username);
        }
        
        // Return null or throw exception if not found
        throw new RuntimeException("User not found: " + username);
    }
}
