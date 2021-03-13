package ch.bzz.fussballSpielerVerwaltung.service;

import ch.bzz.fussballSpielerVerwaltung.data.DataHandler;
import ch.bzz.fussballSpielerVerwaltung.model.Team;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Vector;

/**
 * TeamService
 * M133: Fussballspieler-Verwaltung
 *
 * @author Nils Rothenbühler
 * @version 1.0
 * @date 13.03.2021
 */
@Path("team")
public class TeamService {

    /**
     * Get JSON of all Teams
     * @return response
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response team(){
        Vector<Team> team = DataHandler.getTeamVector();

        Response response = Response
                .status(200)
                .entity(team)
                .build();

        return response;
    }

    /**
     * Get JSON of all Teams that contains name
     * @return response
     */
    @GET
    @Path("readName")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readTeamByName(
            @QueryParam("name") String name
    ) throws IOException {
        Vector<Team> team = null;
        team = DataHandler.readTeamByName(name);

        Response response = Response
                .status(200)
                .entity(team)
                .build();

        return response;
    }

    /**
     * Get JSON of Teams with id
     * @return response
     */
    @GET
    @Path("readID")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readTeamByID(
            @QueryParam("id") int id
    ) throws IOException {
        Team team = null;
        team = DataHandler.readTeamByID(id);

        Response response = Response
                .status(200)
                .entity(team)
                .build();

        return response;
    }
}
