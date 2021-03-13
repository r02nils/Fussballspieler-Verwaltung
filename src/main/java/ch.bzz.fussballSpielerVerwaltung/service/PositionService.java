package ch.bzz.fussballSpielerVerwaltung.service;

import ch.bzz.fussballSpielerVerwaltung.data.DataHandler;
import ch.bzz.fussballSpielerVerwaltung.model.Position;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
        position = DataHandler.readPositionByID(id);

        Response response = Response
                .status(200)
                .entity(position)
                .build();

        return response;
    }
}
