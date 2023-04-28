import ie.atu.teamproject.playlist.Song;
import org.junit.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SongTest {
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
    public void testAddMediaWhenArtistNameIsNull() {
        Song song = new Song(conn);
        song.setArtistName(null);
        assertThrows(NullPointerException.class, () -> song.addMedia());
    }

    @Test
    public void testAddMedia() throws Exception {
        String testName = "testSong";
        Song testSong = new Song(conn);

        testSong.setSongName(testName);

        testSong.addMedia();

        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM SONG WHERE songName = '" + testName + "'");

        while (resultSet.next())
        {
            String name = resultSet.getString("songName");
            assertEquals(testName,name);
        }
    }
    @Test
    public void testRemoveMedia() throws Exception{
        String testName = "testSong";
        Song testSong = new Song(conn);

        testSong.setSongName(testName);

        testSong.removeMedia();

        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM SONG WHERE songName = '" + testName + "'");

        while (resultSet.next())
        {
            String name = resultSet.getString("songName");
            assertEquals(testName,name);
        }
    }
}
