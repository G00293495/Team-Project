package ie.atu.teamproject.playlist.Test;

import ie.atu.teamproject.playlist.PlaylistDB;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlaylistDBTest {
    private PlaylistDB playlistDB;

    @Before
    public void setUp() {
        playlistDB = new PlaylistDB();
    }

    @Test
    public void testAddArtist() {
        boolean isAdded = playlistDB.addArtist();
        assertEquals(this, isAdded);
    }
}
