package ch.bzz.fussballSpielerVerwaltung.model;

public class Position {

    private int posID;
    private String pos;

    public Position(String pos){
        this.pos = pos;
        posID++;
    }

    public int getPosID() {
        return posID;
    }

    public void setPosID(int posID) {
        this.posID = posID;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }
}
