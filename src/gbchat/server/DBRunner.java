package gbchat.server;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


//test database connection
public class DBRunner {
    static final Logger LOGGER = LogManager.getLogger(gbchat.server.DBRunner.class);
    public static void main(String[] args) throws SQLException, IOException {
        LOGGER.info("info");
        LOGGER.info("info2");
    }

}
