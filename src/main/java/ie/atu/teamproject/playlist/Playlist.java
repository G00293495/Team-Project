package ie.atu.teamproject.playlist;

import java.util.ArrayList;

public class Playlist implements Playlistable {
    private String artistName;
    private String songNames;
    private ArrayList<ArtistDetails> artistDetails;

    public Playlist() {
        super();
        artistName = "";
        songNames = "";
        artistDetails = new ArrayList<ArtistDetails>();
    }

    // getter setter for the ArrayList
    public ArrayList<ArtistDetails> getArtists() {
        return artistDetails;
    }

    public void setArtists(ArrayList<ArtistDetails> artistDetails) {
        this.artistDetails = artistDetails;
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
