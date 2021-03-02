package ch.bzz.fussballSpielerVerwaltung.model;

public class Nation {

    private int natID;
    private String nat;
    private String imagePath;

    public Nation(String nat, String imagePath){
        this.nat = nat;
        this.imagePath = imagePath;
        natID++;
    }

    public int getNatID() {
        return natID;
    }

    public void setNatID(int natID) {
        this.natID = natID;
    }

    public String getNat() {
        return nat;
    }

    public void setNat(String nat) {
        this.nat = nat;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
