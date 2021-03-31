package ch.bzz.fussballSpielerVerwaltung.model;

public class User {

    private int userID;
    private String username;
    private String password;
    private int role;

    public User(int userID, String username, String password, int role){
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

    public int getRole() {
        return role;
    }
}
