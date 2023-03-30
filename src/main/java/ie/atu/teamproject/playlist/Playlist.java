package ie.atu.teamproject.playlist;

public class Playlist implements Playlistable{
    private String name;    //counts as artist name
    private String songName;
    private double streams; //counts as streams

    public Playlist(){
        super();
        name = "";
        songName = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getStreams() {
        return streams;
    }

    public void setStreams(double streams) {
        this.streams = streams;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    @Override
    public String toString() {
        return "Name: " + name;
    }
}
