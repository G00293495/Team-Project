package ie.atu.teamproject.playlist;

public interface Playlistable {
    public String getSongNames();
    public void setSongNames(String songNames);
    public String getArtistName();
    public void setArtistName(String artistName);
    public String getGenre();
    public void setGenre(String genre);
    public int getStreams();
    public void setStreams(int streams);
    public Artist getArtist();
    public void setArtist(Artist artist);
}
