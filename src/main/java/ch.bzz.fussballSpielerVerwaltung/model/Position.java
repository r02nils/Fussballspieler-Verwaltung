package ch.bzz.fussballSpielerVerwaltung.model;

/**
 * Klasse: Position
 * M133: Fussballspieler-Verwaltung
 *
 * @author Nils Rothenbühler
 * @version 1.0
 * @date 13.03.2021
 */
public class Position {

    private int posID;
    private String pos;

    /**
     * Position constructor
     * @param posID
     * @param pos
     */
    public Position(int posID, String pos){
        this.posID = posID;
        this.pos = pos;
    }

    /**
     * get id
     * @return posID
     */
    public int getPosID() {
        return posID;
    }

    /**
     * get Position
     * @return pos
     */
    public String getPos() {
        return pos;
    }

}
