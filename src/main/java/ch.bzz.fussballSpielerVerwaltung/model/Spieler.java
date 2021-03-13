package ch.bzz.fussballSpielerVerwaltung.model;

/**
 * Klasse: Spieler
 * M133: Fussballspieler-Verwaltung
 *
 * @author Nils Rothenbühler
 * @version 1.0
 * @date 13.03.2021
 */
public class Spieler implements Comparable<Spieler>{

    private int spielerID;
    private String name;
    private Team team;
    private Position pos;
    private Nation nat;
    private String imagePath;


    /**
     * Spieler constructor
     * @param spielerID
     * @param name
     * @param team
     * @param nat
     * @param pos
     * @param imagePath
     */
    public Spieler(int spielerID, String name, Team team, Nation nat, Position pos, String imagePath){
        this.spielerID = spielerID;
        this.name = name;
        this.team = team;
        this.nat = nat;
        this.pos = pos;
        this.nat = nat;
        this.imagePath = imagePath;
    }

    /**
     * Spieler constructor (default)
     */
    public Spieler(){

    }

    /**
     * get SpielerID
     * @return spielerID
     */
    public int getSpielerID() {
        return spielerID;
    }

    /**
     * get Name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * get Team
     * @return team
     */
    public Team getTeam() {
        return team;
    }

    /**
     * get Position
     * @return pos
     */
    public Position getPos() {
        return pos;
    }

    /**
     * get Nation
     * @return nat
     */
    public Nation getNat() {
        return nat;
    }

    /**
     * toString Method
     * @return name
     */
    public String toString(){
        return name;
    }

    /**
     * sort Spieler by Name
     * @param spieler
     * @return
     */
    @Override
    public int compareTo(Spieler spieler) {
        return this.getName().compareTo(spieler.getName());
    }
}
