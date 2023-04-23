package ie.atu.teamproject.playlist;

import java.util.ArrayList;

public class Playlist implements Playlistable {
    private String artistName;
    private String songNames;

    public Playlist() {
        super();
        artistName = "";
        songNames = "";
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getSongNames() {
        return songNames;
    }

    public void setSongNames(String songNames) {
        this.songNames = songNames;
    }

    public String toString() {
        return "SongDetails: " + getSongNames() + "\n";
    }
}
