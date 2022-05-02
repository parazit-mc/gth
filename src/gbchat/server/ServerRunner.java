package gbchat.server;

import java.sql.SQLException;

public class ServerRunner {

    public static void main(String[] args) throws SQLException {
        new ChatServer().run();
    }


}