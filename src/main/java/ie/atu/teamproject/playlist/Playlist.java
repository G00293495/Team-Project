package ie.atu.teamproject.playlist;

public class Playlist implements Playlistable {
    private String artistName;
    private String songNames;
    private int streams;

    public Playlist() {
        super();
        artistName = "";
        songNames = "";
        streams = 0;
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
