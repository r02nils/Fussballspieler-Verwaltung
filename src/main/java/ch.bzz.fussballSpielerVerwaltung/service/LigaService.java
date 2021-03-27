package ch.bzz.fussballSpielerVerwaltung.service;

import ch.bzz.fussballSpielerVerwaltung.data.DataHandler;
import ch.bzz.fussballSpielerVerwaltung.model.Liga;
import ch.bzz.fussballSpielerVerwaltung.model.Nation;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Vector;

/**
 * LigaService
 * M133: Fussballspieler-Verwaltung
 *
 * @author Nils Rothenbühler
 * @version 1.0
 * @date 13.03.2021
 */

@Path("liga")
public class LigaService {

    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response create(
            @FormParam("name")
            @NotEmpty
            @Pattern(regexp="^[a-zA-Z0-9 ]+$")
            @Size(min=2, max=40)
                    String name
    ){
        DataHandler.getLigaVector();

        Liga liga = new Liga(DataHandler.getLigaC()+1, name, "1.png");

        DataHandler.saveLiga(liga);

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

        DataHandler.deleteLiga(id);

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
            @FormParam("name")String name
    ){
        Liga liga = new Liga(id, name, "1.png");

        DataHandler.updateLiga(liga);

        Response response = Response
                .status(200)
                .entity("")
                .build();

        return response;
    }

    /**
     * Get JSON of all Ligas
     * @return response
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response liga(){
        Vector<Liga> liga = DataHandler.getLigaVector();

        Response response = Response
                .status(200)
                .entity(liga)
                .build();

        return response;
    }

    /**
     * Get JSON of all Ligas that contains name
     * @return response
     */
    @GET
    @Path("readName")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readLigaByName(
            @QueryParam("name") String name
    ) throws IOException {
        Vector<Liga> liga = null;
        liga = DataHandler.readLigaByName(name);

        Response response = Response
                .status(200)
                .entity(liga)
                .build();

        return response;
    }

    /**
     * Get JSON of Liga with id
     * @return response
     */
    @GET
    @Path("readID")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readLigaByID(
            @QueryParam("id") int id
    ) throws IOException {
        Liga liga = null;

        int httpStatus;

        try {
            liga = DataHandler.readLigaByID(id);
            if (liga.getLiga() != null) {
                httpStatus = 200;
            } else {
                httpStatus = 404;
            }
        } catch (IllegalArgumentException argEx) {
            httpStatus = 400;
        }
        Response response = Response
                .status(httpStatus)
                .entity(liga)
                .build();

        return response;
    }
}
