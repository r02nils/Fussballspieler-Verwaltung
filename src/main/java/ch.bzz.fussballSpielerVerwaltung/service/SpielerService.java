package ch.bzz.fussballSpielerVerwaltung.service;

import ch.bzz.fussballSpielerVerwaltung.data.DataHandler;
import ch.bzz.fussballSpielerVerwaltung.model.Spieler;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

@Path("spieler")
public class SpielerService {

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
}
