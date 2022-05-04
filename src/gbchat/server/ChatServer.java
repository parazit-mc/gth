package gbchat.server;
import gbchat.Command;
import gbchat.client.MessageChatLogging;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ChatServer {

    private final Map<String, ClientHandler> clients;
    private DbAuthService dbAuthService;
    MessageChatLogging ml = new MessageChatLogging();

    public ChatServer() throws SQLException, IOException {
        this.dbAuthService = new DbAuthService();
        this.clients = new HashMap<>();

    }

    public void run() throws SQLException {
        try (ServerSocket serverSocket = new ServerSocket(8189);
//             AuthService authService = new InMemoryAuthService())
             AuthService authService = new DbAuthService())
        {
            while (true) {
                System.out.println("Wait client connection...");
                final Socket socket = serverSocket.accept();
                new ClientHandler(socket, this, authService, dbAuthService);
                System.out.println("Client connected");
                ml.addEvent("client connected");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isNickBusy(String nick) {
        return clients.containsKey(nick);
    }

    public void subscribe(ClientHandler client) throws Exception {
        clients.put(client.getNick(), client);
        broadcastClientList();
        ml.addEvent(client.getNick() + " joined chat");
    }

    public void unsubscribe(ClientHandler client) throws Exception {
        clients.remove(client.getNick());
        broadcastClientList();
        ml.addEvent(client.getNick() + " left chat");
    }

    private void broadcastClientList() {
        StringBuilder nicks = new StringBuilder();
        for (ClientHandler value : clients.values()) {
            nicks.append(value.getNick()).append(" ");
        }
        broadcast(Command.CLIENTS, nicks.toString().trim());
    }

    private void broadcast(Command command, String nicks) {
        for (ClientHandler client : clients.values()) {
            client.sendMessage(command, nicks);
        }
    }

    public void broadcast(String msg) {
        clients.values().forEach(client -> client.sendMessage(msg));
    }

    public void sendMessageToClient(ClientHandler sender, String to, String message) throws Exception {
        final ClientHandler receiver = clients.get(to);
        if (receiver != null) {
            receiver.sendMessage("от " + sender.getNick() + ": " + message);
            sender.sendMessage("участнику " + to + ": " + message);
            ml.addEvent(sender.getNick() + " send message to " + to +" "+ message);

        } else {
            sender.sendMessage(Command.ERROR, "Участника с ником " + to + " нет в чате!");
            ml.addEvent("User "+ to + "not presented");
        }
    }
}