package ie.atu.teamproject.playlist.Test;

import ie.atu.teamproject.playlist.PlaylistMethods;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlaylistMethodsTest {
    private PlaylistMethods playlistMethods;

    @Before
    public void setUp() {
        playlistMethods = new PlaylistMethods();
    }

    @Test
    public void testAddArtist() {
        boolean isAdded = playlistMethods.addArtist();
        assertEquals(this, isAdded);
    }
}
