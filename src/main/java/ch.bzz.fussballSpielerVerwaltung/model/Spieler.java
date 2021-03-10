package ch.bzz.fussballSpielerVerwaltung.model;

public class Spieler {

    private int spielerID;
    private String name;
    private Team team;
    private Position pos;
    private Nation nat;
    private String imagePath;

    public Spieler(String name, Team team, Nation nat, Position pos, String imagePath){
        this.name = name;
        this.team = team;
        this.nat = nat;
        this.pos = pos;
        this.nat = nat;
        this.imagePath = imagePath;
        spielerID++;
    }

    public Spieler(){

    }

    public int getSpielerID() {
        return spielerID;
    }

    public void setSpielerID(int spielerID) {
        spielerID = spielerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public Nation getNat() {
        return nat;
    }

    public void setNat(Nation nat) {
        this.nat = nat;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        imagePath = imagePath;
    }

    public String toString(){
        return name;
    }
}
