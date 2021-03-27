package ch.bzz.fussballSpielerVerwaltung.service;

import ch.bzz.fussballSpielerVerwaltung.data.DataHandler;
import ch.bzz.fussballSpielerVerwaltung.model.Nation;
import ch.bzz.fussballSpielerVerwaltung.model.Position;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Vector;

/**
 * PositionService
 * M133: Fussballspieler-Verwaltung
 *
 * @author Nils Rothenbühler
 * @version 1.0
 * @date 13.03.2021
 */
@Path("position")
public class PositionService {

    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response create(
            @FormParam("name")String name
    ){
        DataHandler.getPositionID();

        Position position = new Position(DataHandler.getPositionC()+1, name);

        DataHandler.savePosition(position);

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

        DataHandler.deletePosition(id);

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
        Position position = new Position(id, name);

        DataHandler.updatePosition(position);

        Response response = Response
                .status(200)
                .entity("")
                .build();

        return response;
    }

    /**
     * Get JSON of all Positions
     * @return response
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response position(){
        Vector<Position> position = DataHandler.getPosVector();

        Response response = Response
                .status(200)
                .entity(position)
                .build();

        return response;
    }

    /**
     * Get JSON of all Positions that contains name
     * @return response
     */
    @GET
    @Path("readName")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readPositionByName(
            @QueryParam("name") String name
    ) throws IOException {
        Vector<Position> position = null;
        position = DataHandler.readPositionByName(name);

        Response response = Response
                .status(200)
                .entity(position)
                .build();

        return response;
    }

    /**
     * Get JSON of Position with id
     * @return response
     */
    @GET
    @Path("readID")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readPositionByID(
            @QueryParam("id") int id
    ) throws IOException {
        Position position = null;

        int httpStatus;

        try {
            position = DataHandler.readPositionByID(id);
            if (position.getPos() != null) {
                httpStatus = 200;
            } else {
                httpStatus = 404;
            }
        } catch (IllegalArgumentException argEx) {
            httpStatus = 400;
        }

        Response response = Response
                .status(httpStatus)
                .entity(position)
                .build();

        return response;
    }
}
