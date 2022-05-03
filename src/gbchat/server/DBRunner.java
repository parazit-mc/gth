package gbchat.server;

import gbchat.client.MessageChatLogging;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


//test database connection
public class DBRunner {
    public static void main(String[] args) throws SQLException, IOException {
//        DbAuthService in = new DbAuthService();
//        in.getNickByLoginAndPassword("login0","pass0");
        MessageChatLogging ml = new MessageChatLogging();
        ml.addEvent("chat started");
        ml.addEvent("test message");
    }


}
