package ch.bzz.fussballSpielerVerwaltung.service;

import ch.bzz.fussballSpielerVerwaltung.data.DataHandler;
import ch.bzz.fussballSpielerVerwaltung.model.Spieler;

import javax.print.attribute.standard.Media;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.util.Map;

@Path("spieler")
public class TestService {

    @GET
    @Path("spieler")
    @Produces(MediaType.APPLICATION_JSON)
    public Response spieler(){
        Map<String, Spieler> spielerMap = DataHandler.getSpielerMap();

        Response response = Response
                .status(200)
                .entity(spielerMap)
                .build();

        return response;
    }
}
