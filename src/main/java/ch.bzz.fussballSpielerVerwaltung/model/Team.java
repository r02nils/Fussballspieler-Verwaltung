package ch.bzz.fussballSpielerVerwaltung.model;

public class Team {

    private int teamID;
    private String team;
    private String imagePath;
    private Liga liga;

    public Team(String team, String imagePath, Liga liga){
        this.team = team;
        this.imagePath = imagePath;
        this.liga = liga;
        teamID++;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Liga getLiga() {
        return liga;
    }

    public void setLiga(Liga liga) {
        this.liga = liga;
    }
}
