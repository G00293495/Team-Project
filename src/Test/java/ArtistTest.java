import ie.atu.teamproject.playlist.Artist;
import org.junit.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArtistTest {
    static Connection conn;

    //establish connection
    static {
        try {
            conn = DriverManager.getConnection("jdbc:sqlserver://playlistserver.database.windows.net:1433;database=PlaylistExplorerDB;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;", "playlistAdmin", "password1.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testAddMedia() throws Exception {
        String testName = "testArtist";
        Artist testArtist = new Artist(conn);

        testArtist.addMedia();

        testArtist.setArtistName(testName);

        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Artist WHERE artistName = testArtist");

        while (resultSet.next())
        {
            String name = resultSet.getString("artistName");
            assertEquals(testName,name);
        }
    }
}
