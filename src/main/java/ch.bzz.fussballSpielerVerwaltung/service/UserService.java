package ch.bzz.fussballSpielerVerwaltung.service;

import ch.bzz.fussballSpielerVerwaltung.data.UserData;
import ch.bzz.fussballSpielerVerwaltung.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.awt.*;

/**
 * UserService
 * M133: Fussballspieler-Verwaltung
 *
 * @author Nils Rothenbühler
 * @version 1.0
 * @date 18.04.2021
 */
@Path("user")
public class UserService {
    @Context
    private HttpServletRequest request;

    /**
     * Login
     * @param username
     * @param password
     * @return response
     */
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

        NewCookie cookie = new NewCookie(
                "userRole",
                user.getRole(),
                "/",
                "",
                "Login-Cookie",
                600,
                false
        );

        Response response = Response
                .status(httpStatus)
                .entity("")
                .cookie(cookie)
                .build();
        return response;
    }

    /**
     * Logout
     * @return response
     */
    @DELETE
    @Path("logout")
    @Produces(MediaType.TEXT_PLAIN)
    public Response logout(
    ){

        NewCookie cookie = new NewCookie(
                "userRole",
                "guest",
                "/",
                "",
                "Login-Cookie",
                1,
                false
        );

        HttpSession session = request.getSession(true);
        session.invalidate();

        int httpStatus = 200;

        Response response = Response
                .status(httpStatus)
                .entity("")
                .cookie(cookie)
                .build();
        return response;
    }
}
