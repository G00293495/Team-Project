package ie.atu.teamproject.playlist;

import java.util.ArrayList;

public class Playlist implements Playlistable {
    private String artistName;
    private String songNames;
    private int streams;

    private ArrayList<Artist> artists;

    public Playlist() {
        super();
        artistName = "";
        songNames = "";
        streams = 0;
        artists = new ArrayList<Artist>();
    }

    // add an Artist to the ArrayList
    public void addArtist(Artist artist) {
        artists.add(artist);
    }

    // remove an Artist from the ArrayList
    public void removeArtist(Artist artist) {
        artists.remove(artist);
    }

    // getter for the ArrayList
    public ArrayList<Artist> getArtists() {
        return artists;
    }

    // setter for the ArrayList
    public void setArtists(ArrayList<Artist> artists) {
        this.artists = artists;
    }


    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public int getStreams() {
        return streams;
    }

    public void setStreams(int streams) {
        this.streams = streams;
    }

    public String getSongNames() {
        return songNames;
    }

    public void setSongNames(String songNames) {
        this.songNames = songNames;
    }

    @Override
    public String getGenre() {
        return null;
    }

    @Override
    public void setGenre(String genre) {

    }

    @Override
    public Artist getArtist() {
        return null;
    }

    @Override
    public void setArtist(Artist artist) {

    }

    @Override
    public String toString() {
        return "Song: " + getSongNames() + "\n";
    }
}
