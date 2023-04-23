package ie.atu.teamproject.playlist;

public class Song extends Playlist {
    /*
    private String genre;

    public Song() {
        super();
        genre = "";
    }

    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        //return super.toString() + "Genre: " + genre;
        return "Song Details: " + "\n" +
                "Genre: " + getGenre() + "\n";
    }*/
    private String songName;
    private String artistName;
    private String genre;

    //constructor

    public Song(String songName, String artistName, String genre) {
        this.songName = songName;
        this.genre = genre;
    }

    //getter setter

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}

