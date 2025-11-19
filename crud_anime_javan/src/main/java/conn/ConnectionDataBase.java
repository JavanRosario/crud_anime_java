package conn;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDataBase {
    public static Connection getConnection() throws SQLException, IOException {

        Properties props = new Properties();
        props.load(ConnectionDataBase.class.getClassLoader().getResourceAsStream("config.properties"));


        String url = props.getProperty("url");
        String user = props.getProperty("user");
        String password = props.getProperty("password");
        return DriverManager.getConnection(url, user, password);
    }
}
