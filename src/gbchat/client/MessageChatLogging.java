package gbchat.client;

import java.io.*;
import java.time.*;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.Level;


public class MessageChatLogging implements ChatLogging {

    Logger logger = Logger.getLogger(MessageChatLogging.class.getName());

    FileHandler fileHandler = new FileHandler("chat_log.txt", true);


    public MessageChatLogging() throws IOException {

    }

    public void addEvent(String event) throws FileNotFoundException, UnsupportedEncodingException {
        logger.addHandler(fileHandler);
        String timestamp = ZonedDateTime.now().getDayOfMonth() + "-" + ZonedDateTime.now().getMonth() + "-" +ZonedDateTime.now().getYear() + " "+ ZonedDateTime.now().toLocalTime();
        logger.info(timestamp + " " + event);
    }
}
