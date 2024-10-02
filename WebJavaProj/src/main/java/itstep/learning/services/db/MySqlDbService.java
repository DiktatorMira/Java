package itstep.learning.services.db;
import com.google.inject.Singleton;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Singleton public class MySqlDbService implements DbService {
    private Connection connection;

    @Override
    public Connection getConnection() throws SQLException {
        if(connection == null) {
            DriverManager.registerDriver( new com.mysql.cj.jdbc.Driver() );
            String connectionUrl = "jdbc:mysql://localhost:3308/java_kn_p_213" + "?useUnicode=true&characterEncoding=utf8";
            String username = "user213", password = "pass213";
            connection = DriverManager.getConnection( connectionUrl, username, password );
        }
        return connection;
    }
}