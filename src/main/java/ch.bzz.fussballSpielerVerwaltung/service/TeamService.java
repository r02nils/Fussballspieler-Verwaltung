package ch.bzz.fussballSpielerVerwaltung.service;

import ch.bzz.fussballSpielerVerwaltung.data.DataHandler;
import ch.bzz.fussballSpielerVerwaltung.model.Liga;
import ch.bzz.fussballSpielerVerwaltung.model.Nation;
import ch.bzz.fussballSpielerVerwaltung.model.Team;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.crypto.Data;
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

    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response create(
            @FormParam("name")
            @NotEmpty
            @Pattern(regexp="^[a-zA-Z ]+$")
            @Size(min=2, max=40)
                    String name,
            @FormParam("liga")
            @NotEmpty
            @Pattern(regexp="^[a-zA-Z0-9 ]+$")
            @Size(min=2, max=40)
                    String ligaName
    ){
        DataHandler.getTeamID();
        DataHandler.getLigaID();

        Liga liga = new Liga(DataHandler.getLigaC()+1, ligaName, "1.png");

        for (int i = 0; i < DataHandler.getLigaVector().size(); i++) {
            if(DataHandler.getLigaVector().get(i).getLiga().equals(liga.getLiga())){
                liga = DataHandler.getLigaVector().get(i);
            }
        }

        Team team = new Team(DataHandler.getTeamC()+1, name, "1.png", liga);

        DataHandler.saveLiga(liga);
        DataHandler.saveTeam(team);

        Response response = Response
                .status(200)
                .entity("")
                .build();

        return response;
    }
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response delete(
            @QueryParam("id") int id
    ){

        DataHandler.deleteTeam(id);

        Response response = Response
                .status(200)
                .entity("")
                .build();

        return response;
    }

    @POST
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response update(
            @FormParam("id")int id,
            @FormParam("name")String name,
            @FormParam("liga")String ligaName
    ){

        DataHandler.getTeamID();
        DataHandler.getLigaID();

        Liga liga = new Liga(DataHandler.getLigaC()+1, ligaName, "1.png");

        for (int i = 0; i < DataHandler.getLigaVector().size(); i++) {
            if(DataHandler.getLigaVector().get(i).getLiga().equals(liga.getLiga())){
                liga = DataHandler.getLigaVector().get(i);
            }
        }

        Team team = new Team(DataHandler.getTeamC()+1, name, "1.png", liga);

        DataHandler.updateTeam(team);

        Response response = Response
                .status(200)
                .entity("")
                .build();

        return response;
    }

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

        int httpStatus;

        try {
            team = DataHandler.readTeamByID(id);
            if (team.getTeam() != null) {
                httpStatus = 200;
            } else {
                httpStatus = 404;
            }
        } catch (IllegalArgumentException argEx) {
            httpStatus = 400;
        }

        Response response = Response
                .status(httpStatus)
                .entity(team)
                .build();

        return response;
    }
}
