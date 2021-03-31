package ch.bzz.fussballSpielerVerwaltung.service;

import ch.bzz.fussballSpielerVerwaltung.data.UserData;
import ch.bzz.fussballSpielerVerwaltung.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;

@Path("user")
public class UserService {

    @POST
    @Path("login")
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(
        @FormParam("username") String username,
        @FormParam("password") String password

    ){
        int httpStatus = 200;

        User user = UserData.findUser(username, password);

        if(user.getUsername() == null){
            httpStatus = 404;
        }
        else{
            httpStatus = 200;
        }

        Response response = Response
                .status(httpStatus)
                .entity("")
                .build();
        return response;
    }

    @DELETE
    @Path("logout")
    @Produces(MediaType.TEXT_PLAIN)
    public Response logout(
    ){
        int httpStatus = 200;

        UserData.logout();

        Response response = Response
                .status(httpStatus)
                .entity("")
                .build();
        return response;
    }
}
