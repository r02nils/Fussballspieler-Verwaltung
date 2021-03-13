package ch.bzz.fussballSpielerVerwaltung.service;

import ch.bzz.fussballSpielerVerwaltung.data.DataHandler;
import ch.bzz.fussballSpielerVerwaltung.model.Spieler;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Vector;

/**
 * SpielerService
 * M133: Fussballspieler-Verwaltung
 *
 * @author Nils Rothenbühler
 * @version 1.0
 * @date 13.03.2021
 */
@Path("spieler")
public class SpielerService {

    /**
     * Get JSON of all Spieler
     * @return response
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response spieler(){
        Vector<Spieler> spieler = DataHandler.getSpielerVector();

        Response response = Response
                .status(200)
                .entity(spieler)
                .build();

        return response;
    }

    /**
     * Get JSON of all Spieler (sorted)
     * @return response
     */
    @GET
    @Path("sort")
    @Produces(MediaType.APPLICATION_JSON)
    public Response spielerSortbyName(){
        Vector<Spieler> spieler = DataHandler.getSpielerVectorSorted();

        Response response = Response
                .status(200)
                .entity(spieler)
                .build();

        return response;
    }

    /**
     * Get JSON of all Spieler that contains name
     * @return response
     */
    @GET
    @Path("readName")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readSpielerByName(
            @QueryParam("name") String name
    ) throws IOException {
        Vector<Spieler> spieler = null;
        spieler = DataHandler.readSpielerByName(name);

        Response response = Response
                .status(200)
                .entity(spieler)
                .build();

        return response;
    }

    /**
     * Get JSON of Spieler with id
     * @return response
     */
    @GET
    @Path("readID")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readSpielerByID(
            @QueryParam("id") int id
    ) throws IOException {
        Spieler spieler = null;
        spieler = DataHandler.readSpielerByID(id);

        Response response = Response
                .status(200)
                .entity(spieler)
                .build();

        return response;
    }

    /**
     * Get JSON of all Spieler that contains Nation-Name
     * @return response
     */
    @GET
    @Path("readNation")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readSpielerByNationName(
            @QueryParam("name") String name
    ) throws IOException {
        Vector <Spieler> spieler = null;
        spieler = DataHandler.readSpielerByNationName(name);

        Response response = Response
                .status(200)
                .entity(spieler)
                .build();

        return response;
    }

    /**
     * Get JSON of all Spieler that contains Team-Name
     * @return response
     */
    @GET
    @Path("readTeam")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readSpielerByTeamName(
            @QueryParam("name") String name
    ) throws IOException {
        Vector <Spieler> spieler = null;
        spieler = DataHandler.readSpielerByTeamName(name);

        Response response = Response
                .status(200)
                .entity(spieler)
                .build();

        return response;
    }

    /**
     * Get JSON of all Spieler that contains Position-Name
     * @return response
     */
    @GET
    @Path("readPosition")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readSpielerByPositionName(
            @QueryParam("name") String name
    ) throws IOException {
        Vector <Spieler> spieler = null;
        spieler = DataHandler.readSpielerByPositionName(name);

        Response response = Response
                .status(200)
                .entity(spieler)
                .build();

        return response;
    }
}
