package ch.bzz.fussballSpielerVerwaltung.model;

/**
 * Klasse: Liga
 * M133: Fussballspieler-Verwaltung
 *
 * @author Nils Rothenbühler
 * @version 1.0
 * @date 13.03.2021
 */

public class Liga {

    private int ligaID;
    private String liga;
    private String imagePath;

    /**
     * Liga constructor
     * @param ligaID
     * @param liga
     * @param imagePath
     */
    public Liga(int ligaID, String liga, String imagePath){
        this.ligaID = ligaID;
        this.liga = liga;
        this.imagePath = imagePath;
        ligaID++;
    }

    /**
     * get id
     * @return ligaID
     */
    public int getLigaID() {
        return ligaID;
    }

    /**
     * get Liga
     * @return liga
     */
    public String getLiga() {
        return liga;
    }
}
