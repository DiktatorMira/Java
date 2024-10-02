package itstep.learning.dal.dao;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import itstep.learning.services.db.DbService;

@Singleton
public class AuthDao {
    private final DbService dbService;

    @Inject public AuthDao(DbService dbService) { this.dbService = dbService; }
    public void install() { String sql = "CREATE TABLE  IF NOT EXISTS  ";  }
}