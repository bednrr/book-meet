public class User {
    private String userID;
    private String login;
    private String password;
    private Role role; // teraz enum

    public User(String userID, String login, String password, Role role) {
        this.userID = userID;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public String getUserID() { return userID; }
    public String getLogin() { return login; }
    public String getPassword() { return password; }
    public Role getRole() { return role; }

    @Override
    public String toString() {
        return userID + "," + login + "," + password + "," + role.name();
    }
}
