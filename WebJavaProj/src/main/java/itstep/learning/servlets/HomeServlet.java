package itstep.learning.servlets;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import itstep.learning.dal.dao.AuthDao;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class HomeServlet extends HttpServlet {
    private final AuthDao authDao;

    @Inject public HomeServlet(AuthDao authDao) {
        this.authDao = authDao;
    }
    @Override protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        boolean isSigned = false;
        Object signature = req.getAttribute("signature");
        if ( signature instanceof Boolean ) isSigned = (Boolean) signature;
        if( isSigned ) {
            String dbMessage = authDao.install() ? "Install OK" : "Install failed";
            req.setAttribute( "hash", dbMessage );
            req.setAttribute( "body", "home.jsp" );   // ~ ViewData["body"] = "home.jsp";
        } else req.setAttribute( "body", "not_found.jsp" );
        req.getRequestDispatcher( "WEB-INF/views/_layout.jsp" ).forward(req, resp);
    }
}