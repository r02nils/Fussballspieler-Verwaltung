package ch.bzz.fussballSpielerVerwaltung.data;

import ch.bzz.fussballSpielerVerwaltung.model.Spieler;
import ch.bzz.fussballSpielerVerwaltung.model.User;
import ch.bzz.fussballSpielerVerwaltung.service.Config;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class UserData {
    private static final UserData instance = new UserData();
    private static Vector<User> userVector;
    private static int userC;
    private static User activeUser;

    private UserData(){
        userVector = new Vector<>();

        User user = new User(1, "admin", "12345678", 1);
        userVector = readUserJSON();
    }

    public static User findUser(String username, String password){
        User user = new User();
        Vector<User> userVector = readUserJSON();

        for (int i = 0; i < userVector.size(); i++) {
            if (userVector.get(i).getUsername().equals(username)&&userVector.get(i).getPassword().equals(password)){
                user = userVector.get(i);
            }
        }
        activeUser = user;
        return user;
    }

    public static void logout(){
        activeUser = null;
    }

    private static Vector<User> readUserJSON() {
        Vector<User> u = new Vector<>();
        User user = null;
        Gson gson = new Gson();

        try{
            BufferedReader reader = Files.newBufferedReader(Paths.get(Config.getProperty("userJSON")));
            String line = reader.readLine();

            String[] splits =  line.replace("[","").replace("]","")
                    .replace("},{", "}},{{").split("\\},\\{");
            ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(splits));
            for (int i = 0; i < arrayList.size(); i++) {
                user =
                        gson.fromJson(arrayList.get(i),
                                User.class);
                u.add(user);
            }
        }
        catch (Exception e){

        }
        getUserID();
        return u;
    }

    private static void writeJSON(){
        try{
            Gson gson = new Gson();

            Writer writer = new FileWriter(Config.getProperty("userJSON"));

            gson.toJson(userVector, writer);
            writer.close();
        }
        catch (Exception e){

        }
    }

    public static void getUserID(){
        int highest = 0;
        for (int i = 0; i < userVector.size(); i++) {
            if (highest < userVector.get(i).getUserID()){
                highest = userVector.get(i).getUserID();
            }
        }
        userC = highest;
    }

    public static void main(String[] args) {
        System.out.println("Test");
    }
}
