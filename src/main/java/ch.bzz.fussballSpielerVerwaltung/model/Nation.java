package ch.bzz.fussballSpielerVerwaltung.model;

/**
 * Klasse: Nation
 * M133: Fussballspieler-Verwaltung
 *
 * @author Nils Rothenbühler
 * @version 1.0
 * @date 13.03.2021
 */

public class Nation {

    private int natID;
    private String nat;
    private String imagePath;

    /**
     * Nation constructor
     * @param natID
     * @param nat
     * @param imagePath
     */
    public Nation(int natID, String nat, String imagePath){
        this.natID = natID;
        this.nat = nat;
        this.imagePath = imagePath;
    }

    /**
     * get id
     * @return natID
     */
    public int getNatID() {
        return natID;
    }

    /**
     * get Nation
     * @return nat
     */
    public String getNat() {
        return nat;
    }

}
