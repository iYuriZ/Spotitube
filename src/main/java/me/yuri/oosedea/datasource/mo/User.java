package me.yuri.oosedea.datasource.mo;

public class User {

    private String user;
    private String password;
    private String token;
    private String firstName;
    private String lastName;

    public User () { }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) { this.user = user; }
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String firstName) { this.lastName = lastName; }
}
