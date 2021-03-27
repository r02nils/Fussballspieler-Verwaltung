package ch.bzz.fussballSpielerVerwaltung.data;

import ch.bzz.fussballSpielerVerwaltung.model.Liga;
import ch.bzz.fussballSpielerVerwaltung.model.Nation;
import ch.bzz.fussballSpielerVerwaltung.model.Position;
import ch.bzz.fussballSpielerVerwaltung.model.Spieler;
import ch.bzz.fussballSpielerVerwaltung.model.Team;
import ch.bzz.fussballSpielerVerwaltung.service.Config;
import com.google.gson.*;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * data handler for reading and writing json files
 * M133: Fussballspieler-Verwaltung
 *
 * @author Nils Rothenbühler
 * @version 1.0
 * @date 13.03.2021
 */

public class DataHandler{
    private static final DataHandler instance = new DataHandler();
    private static Vector<Spieler> spielerVector;
    private static Vector<Team> teamVector;
    private static Vector<Liga> ligaVector;
    private static Vector<Position> posVector;
    private static Vector<Nation> natVector;

    private static int spielerC;
    private static int nationC;
    private static int positionC;
    private static int teamC;
    private static int ligaC;

    public static int getSpielerC() {
        return spielerC;
    }

    public static int getNationC() {
        return nationC;
    }

    public static int getPositionC() {
        return positionC;
    }

    public static int getTeamC() {
        return teamC;
    }

    public static int getLigaC() {
        return ligaC;
    }

    /**
     * default constructor: defeat instantiation
     */
    private DataHandler() {
        spielerVector = new Vector<>();
        teamVector = new Vector<>();
        ligaVector = new Vector<>();
        posVector = new Vector<>();
        natVector = new Vector<>();

        spielerVector = readJSON();
        natVector = readNationJSON();
    }

    /**
     * reads a single spieler object by id
     * @param id
     * @return spieler-object
     */
    public static Spieler readSpielerByID(int id) {
        Spieler spieler = new Spieler();
        for (int i = 0; i < spielerVector.size(); i++) {
            if(spielerVector.get(i).getSpielerID() == id){
                spieler = spielerVector.get(i);
                break;
            }
        }
        return spieler;
    }

    /**
     * reads a Collections of spieler by Name
     * @param name
     * @return spieler-vector
     */
    public static Vector<Spieler> readSpielerByName(String name){
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

    /**
     * reads a Collections of spieler by Team Name
     * @param name
     * @return spieler-vector
     */
    public static Vector<Spieler> readSpielerByTeamName(String name){
        Vector<Spieler> spieler = new Vector<>();
        name = name.toLowerCase();
        for (int i = 0; i < spielerVector.size(); i++) {
            String spielerName = spielerVector.get(i).getTeam().getTeam().toLowerCase();
            if(spielerName.contains(name)){
                spieler.add(spielerVector.get(i));
            }
        }
        return spieler;
    }

    /**
     * reads a Collections of spieler by Nation Name
     * @param name
     * @return spieler-vector
     */
    public static Vector<Spieler> readSpielerByNationName(String name){
        Vector<Spieler> spieler = new Vector<>();
        name = name.toLowerCase();
        for (int i = 0; i < spielerVector.size(); i++) {
            String spielerName = spielerVector.get(i).getNat().getNat().toLowerCase();
            if(spielerName.contains(name)){
                spieler.add(spielerVector.get(i));
            }
        }
        return spieler;
    }

    /**
     * reads a Collections of spieler by Position Name
     * @param name
     * @return spieler-vector
     */
    public static Vector<Spieler> readSpielerByPositionName(String name){
        Vector<Spieler> spieler = new Vector<>();
        name = name.toLowerCase();
        for (int i = 0; i < spielerVector.size(); i++) {
            String posName = spielerVector.get(i).getPos().getPos().toLowerCase();
            if(posName.contains(name)){
                spieler.add(spielerVector.get(i));
            }
        }
        return spieler;
    }

    /**
     * reads a Collections of Nation by Nation Name
     * @param name
     * @return nation-vector
     */
    public static Vector<Nation> readNationByName(String name){
        Vector<Nation> nation = new Vector<>();
        name = name.toLowerCase();
        for (int i = 0; i < natVector.size(); i++) {
            String spielerName = natVector.get(i).getNat().toLowerCase();
            if(spielerName.contains(name)){
                nation.add(natVector.get(i));
            }
        }
        return nation;
    }

    /**
     * reads a Collections of Nation by ID
     * @param id
     * @return nation-vector
     */
    public static Nation readNationByID(int id) {
        Nation nation = new Nation();
        for (int i = 0; i < natVector.size(); i++) {
            if(natVector.get(i).getNatID() == id){
                nation = natVector.get(i);
                break;
            }
        }
        return nation;
    }

    /**
     * reads a Collections of Position by name
     * @param name
     * @return position-vector
     */
    public static Vector<Position> readPositionByName(String name){
        Vector<Position> position = new Vector<>();
        name = name.toLowerCase();
        for (int i = 0; i < posVector.size(); i++) {
            String spielerName = posVector.get(i).getPos().toLowerCase();
            if(spielerName.contains(name)){
                position.add(posVector.get(i));
            }
        }
        return position;
    }

    /**
     * reads a Collections of Position by id
     * @param id
     * @return position-vector
     */
    public static Position readPositionByID(int id) {
        Position position = new Position();
        for (int i = 0; i < posVector.size(); i++) {
            if(posVector.get(i).getPosID() == id){
                position = posVector.get(i);
                break;
            }
        }
        return position;
    }

    /**
     * reads a Collections of Team by name
     * @param name
     * @return team-vector
     */
    public static Vector<Team> readTeamByName(String name){
        Vector<Team> team = new Vector<>();
        name = name.toLowerCase();
        for (int i = 0; i < teamVector.size(); i++) {
            String teamName = teamVector.get(i).getTeam().toLowerCase();
            if(teamName.contains(name)){
                team.add(teamVector.get(i));
            }
        }
        return team;
    }

    /**
     * reads a Collections of Team by id
     * @param id
     * @return team-vector
     */
    public static Team readTeamByID(int id) {
        Team team = new Team();
        for (int i = 0; i < teamVector.size(); i++) {
            if(teamVector.get(i).getTeamID() == id){
                team = teamVector.get(i);
                break;
            }
        }
        return team;
    }

    /**
     * reads a Collections of Liga by name
     * @param name
     * @return liga-vector
     */
    public static Vector<Liga> readLigaByName(String name){
        Vector<Liga> liga = new Vector<>();
        name = name.toLowerCase();
        for (int i = 0; i < ligaVector.size(); i++) {
            String teamName = ligaVector.get(i).getLiga().toLowerCase();
            if(teamName.contains(name)){
                liga.add(ligaVector.get(i));
            }
        }
        return liga;
    }

    /**
     * reads a Collections of Liga by id
     * @param id
     * @return liga-vector
     */
    public static Liga readLigaByID(int id) {
        Liga liga = new Liga();
        for (int i = 0; i < ligaVector.size(); i++) {
            if(ligaVector.get(i).getLigaID() == id){
                liga = ligaVector.get(i);
                break;
            }
        }
        return liga;
    }

    /**
     * saves a spieler
     * @param spieler
     */
    public static void saveSpieler(Spieler spieler) {
        getSpielerVector().add(spieler);
        writeJSON();
    }

    public static void saveNation(Nation nation) {
        getNatVector().add(nation);
        writeNationJSON();
        natVector = readNationJSON();
    }

    /**
     * gets the spielervector
     * @return spielerVector
     */
    public static Vector<Spieler> getSpielerVector() {
        return spielerVector;
    }

    public static Vector<Spieler> getSpielerVectorSorted() {
        Vector<Spieler> sorted = new Vector<>();
        sorted = spielerVector;
        Collections.sort(sorted);
        return sorted;
    }

    /**
     * gets the spielervector
     * @return spielerVector
     */
    public static Vector<Nation> getNatVector() {
        return natVector;
    }

    /**
     * gets the posVector
     * @return posVector
     */
    public static Vector<Position> getPosVector() {
        return posVector;
    }

    /**
     * gets the teamVector
     * @return teamVector
     */
    public static Vector<Team> getTeamVector() {
        return teamVector;
    }

    /**
     * gets the ligaVector
     * @return ligaVector
     */
    public static Vector<Liga> getLigaVector() {
        return ligaVector;
    }

    /**
     * reads the spieler from JSON
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
            }
        }
        catch (Exception e){

        }
        getAllIDs();
        return s;
    }

    private static Vector<Nation> readNationJSON(){
        Vector<Nation> s = new Vector<>();
        Nation nation= null;
        Gson gson = new Gson();
        try{
            BufferedReader reader = Files.newBufferedReader(Paths.get(Config.getProperty("nationJSON")));
            String line = reader.readLine();

            String[] splits =  line.replace("[","").replace("]","")
                    .replace("},{", "}},{{").split("\\},\\{");
            ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(splits));
            for (int i = 0; i < arrayList.size(); i++) {
                nation =
                        gson.fromJson(arrayList.get(i),
                                Nation.class);

                s.add(nation);
            }
        }
        catch (Exception e){

        }
        getAllIDs();
        return s;
    }

    /**
     * write spieler into JSON file
     */
    private static void writeJSON(){
        try{
            Gson gson = new Gson();

            Writer writer = new FileWriter(Config.getProperty("spielerJSON"));

            gson.toJson(spielerVector, writer);
            writer.close();
        }
        catch (Exception e){

        }
    }

    private static void writeNationJSON(){
        try{
            Gson gson = new Gson();

            Writer writer = new FileWriter(Config.getProperty("nationJSON"));

            gson.toJson(natVector, writer);
            writer.close();
        }
        catch (Exception e){

        }
    }

    /**
     * gets the highest id from Spieler
     */
    public static void getSpielerID(){
        int highest = 0;
        for (int i = 0; i < spielerVector.size(); i++) {
            if (highest < spielerVector.get(i).getSpielerID()){
                highest = spielerVector.get(i).getSpielerID();
            }
        }
        spielerC = highest;
    }

    /**
     * gets the highest id from Nation
     */
    public static void getNationID(){
        int highest = 0;
        for (int i = 0; i < natVector.size(); i++) {
            if (highest < natVector.get(i).getNatID()){
                highest = natVector.get(i).getNatID();
            }
        }
        nationC = highest;
    }

    /**
     * gets the highest id from Team
     */
    public static void getTeamID(){
        int highest = 0;
        for (int i = 0; i < teamVector.size(); i++) {
            if (highest < teamVector.get(i).getTeamID()){
                highest = teamVector.get(i).getTeamID();
            }
        }
        teamC = highest;
    }

    /**
     * gets the highest id from Position
     */
    public static void getPositionID(){
        int highest = 0;
        for (int i = 0; i < posVector.size(); i++) {
            if (highest < posVector.get(i).getPosID()){
                highest = posVector.get(i).getPosID();
            }
        }
        positionC = highest;
    }

    /**
     * gets the highest id from Liga
     */
    public static void getLigaID(){
        int highest = 0;
        for (int i = 0; i < ligaVector.size(); i++) {
            if (highest < ligaVector.get(i).getLigaID()){
                highest = ligaVector.get(i).getLigaID();
            }
        }
        ligaC = highest;
    }

    /**
     * reads Nations from JSON without Duplicates
     * @param spieler
     */
    public static void readNations(Spieler spieler){
        int check = 0;

        for (int j = 0; j < natVector.size(); j++) {
            if (natVector.get(j).getNat().contains(spieler.getNat().getNat())) {
                check = 1;
            }
        }
        if(check == 0){
            natVector.add(spieler.getNat());
        }
    }

    /**
     * read Teams from JSON without Duplicates
     * @param spieler
     */
    public static void readTeams(Spieler spieler){
        int check = 0;

        for (int j = 0; j < teamVector.size(); j++) {
            if (teamVector.get(j).getTeam().contains(spieler.getTeam().getTeam())) {
                check = 1;
            }
        }
        if(check == 0){
            teamVector.add(spieler.getTeam());
        }
    }

    /**
     * read Ligas from JSON without Duplicates
     * @param spieler
     */
    public static void readLigas(Spieler spieler){
        int check = 0;

        for (int j = 0; j < ligaVector.size(); j++) {
            if (ligaVector.get(j).getLiga().contains(spieler.getTeam().getLiga().getLiga())) {
                check = 1;
            }
        }
        if(check == 0){
            ligaVector.add(spieler.getTeam().getLiga());
        }
    }

    /**
     * read Positions from JSON without Duplicates
     * @param spieler
     */
    public static void readPositions(Spieler spieler){
        int check = 0;

        for (int j = 0; j < posVector.size(); j++) {
            if (posVector.get(j).getPos().contains(spieler.getPos().getPos())) {
                check = 1;
            }
        }
        if(check == 0){
            posVector.add(spieler.getPos());
        }
    }

    /**
     * run all 4 read Methods
     */
    public static void getAllreadMethods(Spieler spieler){
        readNations(spieler);
        readTeams(spieler);
        readLigas(spieler);
        readPositions(spieler);
    }

    /**
     * run all 5 getID Methods
     */
    public static void getAllIDs(){
        getSpielerID();
        getNationID();
        getTeamID();
        getPositionID();
        getLigaID();
    }

    public static void main(String[] args) {
        writeJSON();
    }
}
