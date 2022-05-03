package gbchat.server;

import java.io.IOException;
import java.sql.SQLException;

public class ServerRunner {

    public static void main(String[] args) throws SQLException, IOException {
        new ChatServer().run();
    }


}