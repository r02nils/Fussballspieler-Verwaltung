package ch.bzz.fussballSpielerVerwaltung.model;

import java.security.SecureRandom;

public class User {

    private int userID;
    private String username;
    private String password;
    private String role;

    public User(int userID, String username, String password, String role){
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(){

    }

    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
