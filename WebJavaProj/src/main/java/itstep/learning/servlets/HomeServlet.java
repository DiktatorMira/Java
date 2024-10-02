package itstep.learning.servlets;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import itstep.learning.services.db.DbService;
import itstep.learning.services.hash.HashService;
import itstep.learning.services.kdf.KdfService;
import itstep.learning.services.RandomFileNameService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Connection;

@Singleton public class HomeServlet extends HttpServlet {
    private final HashService hashService;
    private final KdfService kdfService;
    private final DbService dbService;
    private final RandomFileNameService randomFileNameService;

    public HomeServlet() {
        super();
        this.hashService = new HashService() {
            @Override public String hash(String string) { return ""; }
        };
        this.kdfService = new KdfService() {
            @Override public String dk(String password, String salt) { return password + salt; }
        };
        this.dbService = new DbService() {
            @Override public Connection getConnection() throws SQLException { return null; }
        };
        this.randomFileNameService = new RandomFileNameService();
    }
    @Inject public HomeServlet(HashService hashService, KdfService kdfService, DbService dbService, RandomFileNameService randomFileNameService) {
        this.hashService = hashService;
        this.kdfService = kdfService;
        this.dbService = dbService;
        this.randomFileNameService = randomFileNameService;
    }
    @Override protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        boolean isSigned = false;
        Object signature = req.getAttribute("signature");
        if ( signature instanceof Boolean ) isSigned = (Boolean) signature;
        if( isSigned ) {
            String dbMessage;
            try {
                dbService.getConnection();
                dbMessage = "Connection OK";
            } catch( SQLException ex ) { dbMessage = ex.getMessage(); }

            req.setAttribute( "hash",
            hashService.hash( "123" ) + " " +
                kdfService.dk( "password", "salt.4" ) + " " + dbMessage
            );
            req.setAttribute( "body", "home.jsp" );
        }
        else req.setAttribute( "body", "not_found.jsp" );

        String fileNameDefault = randomFileNameService.generateRandomFileName();
        String fileNameCustomLength = randomFileNameService.generateRandomFileName(16);
        req.setAttribute("fileNameDefault", fileNameDefault);
        req.setAttribute("fileNameCustomLength", fileNameCustomLength);

        req.getRequestDispatcher("WEB-INF/views/_layout.jsp").forward(req, resp);
    }
}