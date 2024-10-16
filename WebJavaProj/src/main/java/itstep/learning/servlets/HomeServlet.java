package itstep.learning.servlets;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import itstep.learning.dal.dao.AuthDao;
import itstep.learning.dal.dao.shop.ProductDao;
import itstep.learning.services.db.DbService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Singleton public class HomeServlet extends HttpServlet {
    private final AuthDao authDao;
    private final DbService dbService;
    private final ProductDao productDao;

    @Inject public HomeServlet(AuthDao authDao, @Named("Oracle") DbService dbService, ProductDao productDao) {
        this.authDao = authDao;
        this.dbService = dbService;
        this.productDao = productDao;
    }
    @Override protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        boolean isSigned = false;
        Object signature = req.getAttribute("signature");
        if (signature instanceof Boolean) isSigned = (Boolean)signature;

        if (isSigned) {
            String dbMessage;
            try { dbMessage = authDao.install() ? "Install OK" : "Install failed"; }
            catch (Exception e) { dbMessage = e.getMessage(); }

            try {
                Statement stmt = dbService.getConnection().createStatement();
                ResultSet rs = stmt.executeQuery("SELECT CURRENT_TIMESTAMP FROM dual");
                rs.next();
                dbMessage += " " + rs.getString(1);
                rs.close();
                stmt.close();
            } catch (SQLException e) { dbMessage += " " + e.getMessage(); }

            req.setAttribute( "hash", dbMessage );
            req.setAttribute( "body", "home.jsp" );
        } else req.setAttribute( "body", "not_found.jsp" );
        req.getRequestDispatcher( "WEB-INF/views/_layout.jsp" ).forward(req, resp);
    }
}