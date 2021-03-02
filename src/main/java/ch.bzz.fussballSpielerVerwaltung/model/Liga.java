package ch.bzz.fussballSpielerVerwaltung.model;

public class Liga {

    private int ligaID;
    private String liga;
    private String imagePath;

    public Liga(String liga, String imagePath){
        this.liga = liga;
        this.imagePath = imagePath;
        ligaID++;
    }

    public int getLigaID() {
        return ligaID;
    }

    public void setLigaID(int ligaID) {
        this.ligaID = ligaID;
    }

    public String getLiga() {
        return liga;
    }

    public void setLiga(String liga) {
        this.liga = liga;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
