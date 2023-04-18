package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {
    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:sqlserver://playlistserver.database.windows.net:1433;database=PlaylistExplorerDB;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;","playlistAdmin","password1.");
        System.out.println("Database connection test: " + connection.getCatalog());

    }
}
