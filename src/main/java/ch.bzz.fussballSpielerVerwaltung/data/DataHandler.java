package ch.bzz.fussballSpielerVerwaltung.data;

import ch.bzz.fussballSpielerVerwaltung.model.Liga;
import ch.bzz.fussballSpielerVerwaltung.model.Nation;
import ch.bzz.fussballSpielerVerwaltung.model.Position;
import ch.bzz.fussballSpielerVerwaltung.model.Spieler;
import ch.bzz.fussballSpielerVerwaltung.model.Team;
import ch.bzz.fussballSpielerVerwaltung.service.Config;
import com.google.gson.*;


import java.io.*;;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * data handler for reading and writing json files
 * <p>
 * M133: Fussballspieler-Verwaltung
 *
 * @author Nils Rothenbühler
 */

public class DataHandler {
    private static final DataHandler instance = new DataHandler();
    private static Vector<Spieler> spielerVector;
    private static Vector<Team> teamMap;
    private static Vector<Liga> ligaMap;
    private static Vector<Position> posMap;
    private static Vector<Nation> natMap;

    /**
     * default constructor: defeat instantiation
     */
    private DataHandler() {
        spielerVector = new Vector<>();
        teamMap = new Vector<>();
        ligaMap = new Vector<>();
        posMap = new Vector<>();
        natMap = new Vector<>();

        spielerVector = readJSON();
    }

    /**
     * reads a single book identified by its uuid
     * @param spielerID  the identifier
     * @return book-object
     */
    public static Spieler readSpielerByID(int spielerID) {
        Spieler spieler = new Spieler();
        for (int i = 0; i < spielerVector.size(); i++) {
            if(spielerVector.get(i).getSpielerID() == spielerID){
                spieler = spielerVector.get(i);
                break;
            }
        }
        return spieler;
    }

    public static Vector<Spieler> readSpielerByName(String name){
        readJSON();
        Vector<Spieler> spieler = new Vector<>();
        name = name.toLowerCase();
        for (int i = 0; i < spielerVector.size(); i++) {
            String spielerName = spielerVector.get(i).getName().toLowerCase();
            if(spielerName.contains(name)){
                spieler.add(spielerVector.get(i));
            }
        }
        return spieler;
    }

    public static Vector<Spieler> readSpielerByTeamName(String team){
        readJSON();
        Vector<Spieler> spieler = new Vector<>();
        team = team.toLowerCase();
        for (int i = 0; i < spielerVector.size(); i++) {
            String spielerName = spielerVector.get(i).getTeam().getTeam().toLowerCase();
            if(spielerName.contains(team)){
                spieler.add(spielerVector.get(i));
            }
        }
        return spieler;
    }

    /**
     * saves a book
     * @param spieler the book to be saved
     */
    public static void saveSpieler(Spieler spieler) {
        getSpielerVector().add(spieler.getSpielerID(), spieler);
        writeJSON();
    }

    /**
     * gets the spielervector
     * @return the bookMap
     */
    public static Vector<Spieler> getSpielerVector() {
        return spielerVector;
    }

    /**
     * reads the books and publishers
     */
    private static Vector<Spieler> readJSON(){
        Vector<Spieler> s = new Vector<>();
        Spieler spieler= new Spieler();
        Gson gson = new Gson();
        try{
            BufferedReader reader = Files.newBufferedReader(Paths.get(Config.getProperty("spielerJSON")));
            String line = reader.readLine();

            String[] splits =  line.replace("[","").replace("]","")
                    .replace("},{", "}},{{").split("\\},\\{");
            ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(splits));
            for (int i = 0; i < arrayList.size(); i++) {
                spieler =
                        gson.fromJson(arrayList.get(i),
                                Spieler.class);

                s.add(spieler);
                System.out.println(spieler);
            }
        }
        catch (Exception e){

        }
         return s;
    }

    /**
     * write spieler
     *
     */
    private static void writeJSON(){
        try{
            spielerVector = readJSON();
            Gson gson = new Gson();
            Writer writer = new FileWriter(Config.getProperty("spielerJSON"));

            Nation n = new Nation("Spanien", "1.png");
            Liga l = new Liga("LaLiga", "1.png");
            Team t = new Team("Real Madrid", "1.png", l);
            Position p = new Position("LF");
            Spieler s = new Spieler("Marco Asensio", t,n,p,"1.png");

            spielerVector.add(s);

            gson.toJson(spielerVector, writer);
            writer.close();
        }
        catch (Exception e){

        }
    }
    public static void main(String[] args){
        writeJSON();
    }
}
