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
import java.util.Base64;
import java.util.Vector;

public class UserData {
    private static final UserData instance = new UserData();
    private static Vector<User> userVector;
    private static int userC;
    private static User activeUser;

    private UserData(){
        userVector = new Vector<>();

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
        for (int i = 0; i < u.size(); i++) {
            String name = u.get(i).getUsername();
            String password = u.get(i).getPassword();
            String role = u.get(i).getRole();

            u.get(i).setUsername(decode(name));
            u.get(i).setPassword(decode(password));
            u.get(i).setRole(decode(role));
        }

        getUserID();
        return u;
    }

    private static void writeJSON(){
        try{
            for (int i = 0; i < userVector.size(); i++) {
                String name = userVector.get(i).getUsername();
                String password = userVector.get(i).getPassword();
                String role = userVector.get(i).getRole();

                userVector.get(i).setUsername(encode(name));
                userVector.get(i).setPassword(encode(password));
                userVector.get(i).setRole(encode(role));
            }
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

    public static String encode(String text){
        String encodedString = Base64.getEncoder().encodeToString(text.getBytes());
        return encodedString;
    }

    public static String decode(String text){
        byte[] decodedBytes = Base64.getDecoder().decode(text);
        String decodedString = new String(decodedBytes);
        return decodedString;
    }

    public static void main(String[] args) {
        User user = new User(userC,"test", "test", "read");
        userVector.add(user);
        writeJSON();
    }
}
