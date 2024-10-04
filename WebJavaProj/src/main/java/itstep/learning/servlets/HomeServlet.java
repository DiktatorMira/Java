package itstep.learning.servlets;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import itstep.learning.dal.dao.AccessLogDao;
import itstep.learning.dal.dao.TokenDao;
import itstep.learning.dal.dao.UserDao;
import itstep.learning.dal.dao.shop.CategoryDao;
import itstep.learning.dal.dao.shop.ProductDao;
import itstep.learning.dal.dto.User;
import itstep.learning.services.hash.HashService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@Singleton public class HomeServlet extends HttpServlet {
    private final UserDao userDao;
    private final TokenDao tokenDao;
    private final CategoryDao categoryDao;
    private final ProductDao productDao;
    @Inject private AccessLogDao accessLogDao;

    @Inject public HomeServlet(UserDao userDao, TokenDao tokenDao, CategoryDao categoryDao, ProductDao productDao) {
        this.userDao = userDao;
        this.tokenDao = tokenDao;
        this.categoryDao = categoryDao;
        this.productDao = productDao;
    }
    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute( "hash",
                userDao.installTables() &&
                        tokenDao.installTables() &&
                        productDao.installTables() &&
                        categoryDao.installTables()
                        ? "Tables OK" : "Tables Fail" );
        req.setAttribute( "page", "home" );
        req.getRequestDispatcher("WEB-INF/views/_layout.jsp").forward(req, resp);
        User user = (User) req.getSession().getAttribute("user");
        if (user != null) {
            try { accessLogDao.logAccess(user.getId(), req.getRequestURI()); }
            catch (SQLException e) { e.printStackTrace(); }
        }
    }
}