package me.yuri.oosedea.modelobjects;

import java.util.Objects;

public class User {

    private String user;
    private String password;
    private String token;
    private String firstName;
    private String lastName;

    public User () {}

    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user1 = (User) o;
        return Objects.equals(user, user1.user) &&
                Objects.equals(password, user1.password) &&
                Objects.equals(token, user1.token) &&
                Objects.equals(firstName, user1.firstName) &&
                Objects.equals(lastName, user1.lastName);
    }
}
