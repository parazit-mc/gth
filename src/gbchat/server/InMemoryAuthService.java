package gbchat.server;

import java.io.IOException;
import java.sql.*;


public class InMemoryAuthService implements AuthService  {
    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    void connection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:javadb.db");
        statement = connection.createStatement();
    }

    void createTable() throws SQLException {
//        this.connection();

        statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "login TEXT," +
                "password TEXT," +
                "nick TEXT" +
                ");");

    }

    void disconnect() throws SQLException {
        if (statement!=null) statement.close();
        if (preparedStatement!=null) preparedStatement.close();
        if (connection!=null) connection.close();

    }

    public InMemoryAuthService() throws SQLException {
    try {
        this.connection();
        this.createTable();


        for (int i = 0; i < 5; i++) {
            preparedStatement = connection.prepareStatement("INSERT INTO users(login, password,nick) VALUES (?, ?, ?)");
            preparedStatement.setString(1, "login" + i);
            preparedStatement.setString(2, "pass" + i);
            preparedStatement.setString(3, "user" + i);
            preparedStatement.executeUpdate();
        }
    }
    finally{
        this.disconnect();
    }
    }

    @Override
    public String getNickByLoginAndPassword(String login, String password)  {

        try{
//            this.connection();
            preparedStatement = connection.prepareStatement("select * from users where login = ? AND password = ?");
            preparedStatement.setString(1,login);
            preparedStatement.setString(2,password);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                if(login.equals(resultSet.getString("login")) && password.equals(resultSet.getString("password"))){
                    System.out.println("user found: " + resultSet.getString("nick"));
                    return resultSet.getString("nick");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            try {
                disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

       return null;
    }

    @Override
    public void run() {
        System.out.println("AuthService run");
    }

    @Override
    public void close() throws IOException {
        System.out.println("AuthService closed");
    }

    private static class UserData {
        private final String login;
        private final String password;
        private final String nick;

        public UserData(String login, String password, String nick) {
            this.login = login;
            this.password = password;
            this.nick = nick;
        }
    }

}