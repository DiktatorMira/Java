package itstep.learning.dal.dao;
import itstep.learning.dal.dto.AccessLog;
import com.google.inject.Inject;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.*;

public class AccessLogDao {
    private final MysqlDataSource dataSource;

    @Inject public AccessLogDao(MysqlDataSource dataSource) {
        this.dataSource = dataSource;
    }
    public void logAccess(int userId, String pageUrl) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            String query = "INSERT INTO AccessLog (user_id, page_url) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, userId);
                statement.setString(2, pageUrl);
                statement.executeUpdate();
            }
        }
    }
    public void install() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            String query = "CREATE TABLE IF NOT EXISTS AccessLog (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "user_id INT, " +
                    "access_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                    "page_url VARCHAR(255), " +
                    "FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE" +
                    ")";
            try (PreparedStatement statement = connection.prepareStatement(query)) { statement.executeUpdate(); }
        }
    }
}