package gbchat.server;

import java.io.IOException;
import java.sql.SQLException;

public class ChatRunner {
    public static void main(String[] args) throws Exception {
        final ChatServer server = new ChatServer();
        server.run();

    }
}
