package itstep.learning.servlets;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import itstep.learning.dal.dao.AuthDao;
import itstep.learning.dal.dto.User;
import itstep.learning.rest.RestMetaData;
import itstep.learning.rest.RestResponse;
import itstep.learning.rest.RestServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Base64;
import java.util.Date;

@Singleton public class AuthServlet extends RestServlet {
    private final AuthDao authDao;

    @Inject public AuthServlet(AuthDao authDao) {
        this.authDao = authDao;
    }
    @Override protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.restResponse = new RestResponse()
                .setMeta( new RestMetaData()
                    .setUri( "/auth" )
                    .setMethod( req.getMethod() )
                    .setName( "KN-P-213 Authentication API" )
                    .setServerTime( new Date() )
                    .setAllowedMethods( new String[]{"GET", "POST", "PUT", "DELETE", "OPTIONS"} )
                );
        super.service(req, resp);
    }
    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RestResponse restResponse = new RestResponse();
        try {
            String authHeader = req.getHeader("Authorization");
            if (authHeader == null) throw new ParseException("Authorization header not found", 401);

            String authScheme = "Basic ";
            if (!authHeader.startsWith(authScheme)) throw new ParseException("Invalid Authorization scheme. Required " + authScheme, 400);

            String credentials = authHeader.substring(authScheme.length()), decodedCredentials;
            try {
                decodedCredentials = new String(
                    Base64.getUrlDecoder().decode( credentials.getBytes( StandardCharsets.UTF_8 ) ),
                    StandardCharsets.UTF_8
                );
            } catch (IllegalArgumentException ignored) {
                throw new ParseException( "Invalid credentials format", 400 );
            }

            String[] parts = decodedCredentials.split( ":", 2 );
            if (parts.length != 2) throw new ParseException( "Invalid credentials composition", 400 );

            User user = authDao.authenticate( parts[0], parts[1] );
            if (user == null) throw new ParseException( "Credentials rejected", 401 );
            super.sendResponse(user);
        } catch(ParseException ex) {
            super.sendResponse(ex.getErrorOffset(), ex.getMessage());
        }
    }
}