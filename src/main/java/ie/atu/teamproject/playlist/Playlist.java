package ie.atu.teamproject.playlist;

public class Playlist implements Playlistable{
    private String name;    //counts as song name & artist name
    private double streams; //counts as monthly listeners

    public Playlist(){
        super();
        name = "";
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

    @Override
    public String toString() {
        return "Name: " + name;
    }
}
