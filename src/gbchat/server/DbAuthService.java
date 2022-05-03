package gbchat.server;

import gbchat.client.MessageChatLogging;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.*;

public class DbAuthService implements AuthService {

    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    MessageChatLogging ml = new MessageChatLogging();
    public DbAuthService() throws SQLException, IOException {
        this.connection();
    }

    public void disconnect() throws SQLException {
        if (resultSet!=null) resultSet.close();
        if (preparedStatement!=null) preparedStatement.close();
        if (statement!=null) statement.close();
        if (connection!=null) connection.close();

    }

    void connection() throws SQLException, FileNotFoundException, UnsupportedEncodingException {
        connection = DriverManager.getConnection("jdbc:sqlite:javadb.db");
        ml.addEvent("database connected");
        statement = connection.createStatement();
    }

    @Override
    public String getNickByLoginAndPassword(String login, String password) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement("SELECT nick FROM users WHERE login = ? AND password = ?")) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            final ResultSet rs = preparedStatement.executeQuery();
            return rs.getString("nick");
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }

    public void changeNick (String currentNick, String newNick) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET nick = ? WHERE nick = ?")) {
            preparedStatement.setString(1, newNick);
            preparedStatement.setString(2, currentNick);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {

    }

    @Override
    public void close() throws IOException {

    }
}
