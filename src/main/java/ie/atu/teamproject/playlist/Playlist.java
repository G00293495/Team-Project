package ie.atu.teamproject.playlist;

public class Playlist implements Playlistable{
    private String songName;    //counts as song name & artist name
    private String artistName;
    private double streams; //counts as monthly listeners

    public Playlist(){
        super();
        songName = "";
        artistName = "";
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public double getStreams() {
        return streams;
    }

    public void setStreams(double streams) {
        this.streams = streams;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    @Override
    public String toString() {
        return "Name: " + songName;
    }
}
