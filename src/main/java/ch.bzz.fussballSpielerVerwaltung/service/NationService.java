package ch.bzz.fussballSpielerVerwaltung.service;

import ch.bzz.fussballSpielerVerwaltung.data.DataHandler;
import ch.bzz.fussballSpielerVerwaltung.model.Nation;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.Vector;

/**
 * NationService
 * M133: Fussballspieler-Verwaltung
 *
 * @author Nils Rothenbühler
 * @version 1.0
 * @date 13.03.2021
 */
@Path("nation")
public class NationService {

    /**
     * Get JSON of all Nations
     * @return response
     */

    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response create(
            @FormParam("name")String name
    ){
        DataHandler.getAllIDs();
        Nation nation = new Nation(DataHandler.getNationC()+1, name, "1.png");

        DataHandler.saveNation(nation);

        Response response = Response
                .status(200)
                .entity("")
                .build();

        return response;
    }

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response nation(){
        Vector<Nation> nation = DataHandler.getNatVector();

        Response response = Response
                .status(200)
                .entity(nation)
                .build();

        return response;
    }

    /**
     * Get JSON of all Nations that contains name
     * @return response
     */
    @GET
    @Path("readName")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readNationbyName(
            @QueryParam("name") String name
    ) throws IOException {
        Vector<Nation> nation = null;
        nation = DataHandler.readNationByName(name);

        Response response = Response
                .status(200)
                .entity(nation)
                .build();

        return response;
    }

    /**
     * Get JSON of Nation with id
     * @return response
     */
    @GET
    @Path("readID")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readNationByID(
            @QueryParam("id") int id
    ) throws IOException {
        Nation nation = null;

        int httpStatus;

        try {
            nation = DataHandler.readNationByID(id);
            if (nation.getNat() != null) {
                httpStatus = 200;
            } else {
                httpStatus = 404;
            }
        } catch (IllegalArgumentException argEx) {
            httpStatus = 400;
        }

        Response response = Response
                .status(httpStatus)
                .entity(nation)
                .build();

        return response;
    }
}
