package gbchat.server;


import java.sql.SQLException;

public class ChatRunner {
    public static void main(String[] args) throws SQLException {
        final ChatServer server = new ChatServer();
        server.run();

    }
}
