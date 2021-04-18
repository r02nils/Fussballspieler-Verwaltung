package ch.bzz.fussballSpielerVerwaltung.service;

import ch.bzz.fussballSpielerVerwaltung.data.DataHandler;
import ch.bzz.fussballSpielerVerwaltung.model.Spieler;
import ch.bzz.fussballSpielerVerwaltung.model.Nation;
import ch.bzz.fussballSpielerVerwaltung.model.Team;
import ch.bzz.fussballSpielerVerwaltung.model.Position;
import ch.bzz.fussballSpielerVerwaltung.model.Liga;


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
 * SpielerService
 * M133: Fussballspieler-Verwaltung
 *
 * @author Nils Rothenbühler
 * @version 1.0
 * @date 13.03.2021
 */
@Path("spieler")
public class SpielerService {

    @POST
    @Path("create")
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(
            @FormParam("name")
            @NotEmpty
            @Pattern(regexp="^[a-zA-Z ]+$")
            @Size(min=2, max=40)
                    String name,

            @FormParam("nation")
            @NotEmpty
            @Pattern(regexp="^[a-zA-Z ]+$")
            @Size(min=2, max=40)
                    String nation,

            @FormParam("team")
            @NotEmpty
            @Pattern(regexp="^[a-zA-Z ]+$")
            @Size(min=2, max=40)
                    String team,

            @FormParam("liga")
            @NotEmpty
            @Pattern(regexp="^[a-zA-Z0-9 ]+$")
            @Size(min=2, max=40)
                    String liga,

            @FormParam("position")
            @NotEmpty
            @Pattern(regexp="^[a-zA-Z]+$")
            @Size(min=2, max=3)
                    String position,
            @CookieParam("userRole") String userRole
    ){

        int httpStatus = 200;
        if(userRole == null || userRole.equals("guest") || userRole.equals("read")){
            httpStatus = 403;
        }
        else if(userRole.equals("admin")){
            httpStatus = 200;

            DataHandler.getSpielerID();
            DataHandler.getNationID();
            DataHandler.getTeamID();
            DataHandler.getLigaVector();
            DataHandler.getPositionID();

            Nation n = new Nation(DataHandler.getNationC()+1,nation,"1.png");
            for (int i = 0; i < DataHandler.getNatVector().size(); i++) {
                if(DataHandler.getNatVector().get(i).getNat().equals(n.getNat())){
                    n = DataHandler.getNatVector().get(i);
                }
            }

            Liga l = new Liga(DataHandler.getLigaC()+1,liga, "1.png");
            for (int i = 0; i < DataHandler.getLigaVector().size(); i++) {
                if(DataHandler.getLigaVector().get(i).getLiga().equals(l.getLiga())){
                    l = DataHandler.getLigaVector().get(i);
                }
            }

            Team t = new Team(DataHandler.getTeamC()+1,team, "1.png", l);
            for (int i = 0; i < DataHandler.getTeamVector().size(); i++) {
                if(DataHandler.getTeamVector().get(i).getTeam().equals(t.getTeam())){
                    t = DataHandler.getTeamVector().get(i);
                }
            }

            Position p = new Position(DataHandler.getPositionC()+1,position);
            for (int i = 0; i < DataHandler.getPosVector().size(); i++) {
                if(DataHandler.getPosVector().get(i).getPos().equals(p.getPos())){
                    p = DataHandler.getPosVector().get(i);
                }
            }

            Spieler s = new Spieler(DataHandler.getSpielerC()+1,name, t,n,p,"1.png");

            DataHandler.saveNation(n);
            DataHandler.saveLiga(l);
            DataHandler.saveTeam(t);
            DataHandler.savePosition(p);
            DataHandler.saveSpieler(s);
        }

        Response response = Response
                .status(httpStatus)
                .entity("")
                .build();

        return response;
    }

    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response delete(
            @QueryParam("id") int id,
            @CookieParam("userRole") String userRole
    ){
        int httpStatus = 200;
        if(userRole == null || userRole.equals("guest") || userRole.equals("read")){
            httpStatus = 403;
        }
        else if(userRole.equals("admin")){
            httpStatus = 200;
            DataHandler.deleteSpieler(id);
        }

        Response response = Response
                .status(httpStatus)
                .entity("")
                .build();

        return response;
    }

    @PUT
    @Path("update")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(
            @FormParam("id")int id,
            @FormParam("name") String name,
            @FormParam("nation") String nation,
            @FormParam("team") String team,
            @FormParam("liga") String liga,
            @FormParam("position") String position,
            @CookieParam("userRole") String userRole
    ){

        int httpStatus = 200;
        if(userRole == null || userRole.equals("guest") || userRole.equals("read")){
            httpStatus = 403;
        }
        else if(userRole.equals("admin")){
            httpStatus = 200;
            DataHandler.getSpielerID();
            DataHandler.getNationID();
            DataHandler.getTeamID();
            DataHandler.getLigaVector();
            DataHandler.getPositionID();

            Nation n = new Nation(DataHandler.getNationC()+1,nation,"1.png");
            for (int i = 0; i < DataHandler.getNatVector().size(); i++) {
                if(DataHandler.getNatVector().get(i).getNat().equals(n.getNat())){
                    n = DataHandler.getNatVector().get(i);
                }
            }

            Liga l = new Liga(DataHandler.getLigaC()+1,liga, "1.png");
            for (int i = 0; i < DataHandler.getLigaVector().size(); i++) {
                if(DataHandler.getLigaVector().get(i).getLiga().equals(l.getLiga())){
                    l = DataHandler.getLigaVector().get(i);
                }
            }

            Team t = new Team(DataHandler.getTeamC()+1,team, "1.png", l);
            for (int i = 0; i < DataHandler.getTeamVector().size(); i++) {
                if(DataHandler.getTeamVector().get(i).getTeam().equals(t.getTeam())){
                    t = DataHandler.getTeamVector().get(i);
                }
            }

            Position p = new Position(DataHandler.getPositionC()+1,position);
            for (int i = 0; i < DataHandler.getPosVector().size(); i++) {
                if(DataHandler.getPosVector().get(i).getPos().equals(p.getPos())){
                    p = DataHandler.getPosVector().get(i);
                }
            }

            Spieler s = new Spieler(id,name, t,n,p,"1.png");

            DataHandler.updateSpieler(s);
        }

        Response response = Response
                .status(httpStatus)
                .entity("")
                .build();

        return response;
    }

    /**
     * Get JSON of all Spieler
     * @return response
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response spieler(
            @CookieParam("userRole") String userRole
    ){
        Vector<Spieler> spieler = null;
        int httpStatus = 200;
        if(userRole == null || userRole.equals("guest")){
            httpStatus = 403;
        }
        else if(userRole.equals("admin") || userRole.equals("read")){
            httpStatus = 200;
            spieler = DataHandler.getSpielerVector();
        }

        Response response = Response
                .status(httpStatus)
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
    public Response spielerSortbyName(
            @CookieParam("userRole") String userRole
    ){
        Vector<Spieler> spieler = null;

        int httpStatus = 200;
        if(userRole == null || userRole.equals("guest")){
            httpStatus = 403;
        }
        else if(userRole.equals("admin") || userRole.equals("read")){
            httpStatus = 200;
            spieler = DataHandler.getSpielerVectorSorted();
        }

        Response response = Response
                .status(httpStatus)
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
            @QueryParam("name") String name,
            @CookieParam("userRole") String userRole
    ) throws IOException {
        Vector<Spieler> spieler = null;

        int httpStatus = 200;
        if(userRole == null || userRole.equals("guest")){
            httpStatus = 403;
        }
        else if(userRole.equals("admin") || userRole.equals("read")){
            httpStatus = 200;
            spieler = DataHandler.readSpielerByName(name);
        }

        Response response = Response
                .status(httpStatus)
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
            @QueryParam("id") int id,
            @CookieParam("userRole") String userRole
    ) throws IOException {
        Spieler spieler = null;

        int httpStatus = 200;
        if(userRole == null || userRole.equals("guest")){
            httpStatus = 403;
        }
        else if(userRole.equals("admin") || userRole.equals("read")){
            httpStatus = 200;
            try {
                spieler = DataHandler.readSpielerByID(id);
                if (spieler.getName() != null) {
                    httpStatus = 200;
                } else {
                    httpStatus = 404;
                }
            } catch (IllegalArgumentException argEx) {
                httpStatus = 400;
            }
        }

        Response response = Response
                .status(httpStatus)
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
            @QueryParam("name") String name,
            @CookieParam("userRole") String userRole
    ) throws IOException {
        Vector <Spieler> spieler = null;

        int httpStatus = 200;
        if(userRole == null || userRole.equals("guest")){
            httpStatus = 403;
        }
        else if(userRole.equals("admin") || userRole.equals("read")){
            httpStatus = 200;
            spieler = DataHandler.readSpielerByNationName(name);
        }

        Response response = Response
                .status(httpStatus)
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
            @QueryParam("name") String name,
            @CookieParam("userRole") String userRole
    ) throws IOException {
        Vector <Spieler> spieler = null;

        int httpStatus = 200;
        if(userRole == null || userRole.equals("guest")){
            httpStatus = 403;
        }
        else if(userRole.equals("admin") || userRole.equals("read")){
            httpStatus = 200;
            spieler = DataHandler.readSpielerByTeamName(name);
        }

        Response response = Response
                .status(httpStatus)
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
            @QueryParam("name") String name,
            @QueryParam("userRole") String userRole
    ) throws IOException {
        Vector <Spieler> spieler = null;

        int httpStatus = 200;
        if(userRole == null || userRole.equals("guest")){
            httpStatus = 403;
        }
        else if(userRole.equals("admin") || userRole.equals("read")){
            httpStatus = 200;
            spieler = DataHandler.readSpielerByPositionName(name);
        }

        Response response = Response
                .status(httpStatus)
                .entity(spieler)
                .build();

        return response;
    }
}
