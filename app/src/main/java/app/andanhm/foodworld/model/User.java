package app.andanhm.foodworld.model;



/**
 * Defines the data structure for User objects.
 */

public class User {
    private String name;
    private String email;
    private String password;
    private boolean hasLoggedInWithPassword;


    /**
     * Required public constructor
     */
    public User() {
    }

    /**
     * Use this constructor to create new User.
     * Takes user name, email as params
     *
     * @param name Username of the logged user
     * @param email Email ID of the logged user
     */
    public User(String name, String email,String password) {
        this.name = name;
        this.email = email;
        this.password=password;
        this.hasLoggedInWithPassword = false;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHasLoggedInWithPassword(boolean hasLoggedInWithPassword) {
        this.hasLoggedInWithPassword = hasLoggedInWithPassword;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean isHasLoggedInWithPassword() {
        return hasLoggedInWithPassword;
    }
}
