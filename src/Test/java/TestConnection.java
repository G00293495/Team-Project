import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestConnection {
    @Test
    public void testConnection() throws SQLException {
        //establish connection
        Connection connection = DriverManager.getConnection("jdbc:sqlserver://playlistserver.database.windows.net:1433;database=PlaylistExplorerDB;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;", "playlistAdmin", "password1.");

        //check if name of db is correct
        assertEquals("PlaylistExplorerDB",connection.getCatalog());
    }
}
