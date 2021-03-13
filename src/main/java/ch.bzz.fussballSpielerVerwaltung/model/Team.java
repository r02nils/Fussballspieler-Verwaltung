package ch.bzz.fussballSpielerVerwaltung.model;

/**
 * Klasse: Team
 * M133: Fussballspieler-Verwaltung
 *
 * @author Nils Rothenbühler
 * @version 1.0
 * @date 13.03.2021
 */
public class Team {

    private int teamID;
    private String team;
    private String imagePath;
    private Liga liga;

    /**
     * Team constructor
     * @param teamID
     * @param team
     * @param imagePath
     * @param liga
     */
    public Team(int teamID, String team, String imagePath, Liga liga){
        this.teamID = teamID;
        this.team = team;
        this.imagePath = imagePath;
        this.liga = liga;
    }

    public Team(){

    }

    /**
     * get ID
     * @return teamID
     */
    public int getTeamID() {
        return teamID;
    }

    /**
     * get Team
     * @return team
     */
    public String getTeam() {
        return team;
    }

    /**
     * get Liga
     * @return liga
     */
    public Liga getLiga() {
        return liga;
    }

}
