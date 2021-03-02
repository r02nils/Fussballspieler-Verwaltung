package ch.bzz.fussballSpielerVerwaltung.data;

import ch.bzz.fussballSpielerVerwaltung.model.Liga;
import ch.bzz.fussballSpielerVerwaltung.model.Nation;
import ch.bzz.fussballSpielerVerwaltung.model.Position;
import ch.bzz.fussballSpielerVerwaltung.model.Spieler;
import ch.bzz.fussballSpielerVerwaltung.model.Team;
import ch.bzz.fussballSpielerVerwaltung.service.Config;
import com.fasterxml.jackson.databind.ObjectMapper;
import sun.java2d.pipe.SpanClipRenderer;

import java.awt.print.Book;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * data handler for reading and writing the csv files
 * <p>
 * M133: Bookshelf
 *
 * @author Marcel Suter
 */

public class DataHandler {
    private static final DataHandler instance = new DataHandler();
    private static Map<String, Spieler> spielerMap;
    private static Map<String, Team> teamMap;
    private static Map<String, Liga> ligaMap;
    private static Map<String, Position> posMap;
    private static Map<String, Nation> natMap;

    /**
     * default constructor: defeat instantiation
     */
    private DataHandler() {
        spielerMap = new HashMap<>();
        teamMap = new HashMap<>();
        ligaMap = new HashMap<>();
        posMap = new HashMap<>();
        natMap = new HashMap<>();

        Nation nat1 = new Nation("Spain", "372178.png");
        Liga liga1 = new Liga("LaLiga", "1324.png");
        Team team1 = new Team("Real Madrid", "234.png", liga1);
        Position pos1 = new Position("LF");
        Spieler spieler1 = new Spieler("Marco Asensio", team1, nat1, pos1, "1.png");
        spielerMap.put("Spieler1", spieler1);
        //readJSON();
    }

    /**
     * reads a single book identified by its uuid
     * @param spielerID  the identifier
     * @return book-object
     */
    public static Spieler readSpieler(int spielerID) {
        Spieler spieler = new Spieler();
        if (getSpielerMap().containsKey(spielerID)) {
            spieler = getSpielerMap().get(spielerID);
        }
        return spieler;
    }

    /**
     * saves a book
     * @param spieler the book to be saved
     */
    public static void saveSpieler(Spieler spieler) {
        getSpielerMap().put(String.valueOf(spieler.getSpielerID()), spieler);
        writeJSON();
    }

    /**
     * gets the bookMap
     * @return the bookMap
     */
    public static Map<String, Spieler> getSpielerMap() {
        return spielerMap;
    }

    /**
     * gets the publisherMap
     * @return the publisherMap
     */

    /**
     * reads the books and publishers
     */
    private static void readJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(Config.getProperty("spielerJSON")));
            ObjectMapper objectMapper = new ObjectMapper();
            Spieler[] spieler = objectMapper.readValue(jsonData, Spieler[].class);
            /*for (Spieler spieler1 : spieler) {
                String publisherUUID = spieler1.getPublisher().getPublisherUUID();
                Publisher publisher;
                if (getPublisherMap().containsKey(publisherUUID)) {
                    publisher = getPublisherMap().get(publisherUUID);
                } else {
                    publisher = book.getPublisher();
                    getPublisherMap().put(publisherUUID, publisher);
                }
                book.setPublisher(publisher);
                getBookMap().put(book.getBookUUID(), book);

            }*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * write the books and publishers
     *
     */
    private static void writeJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        Writer writer;
        FileOutputStream fileOutputStream = null;

        String bookPath = Config.getProperty("spielerJSON");
        try {
            fileOutputStream = new FileOutputStream(bookPath);
            writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectMapper.writeValue(writer, getSpielerMap().values());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
