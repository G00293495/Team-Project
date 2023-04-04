package ie.atu.teamproject.playlist;

public class Song extends Playlist {
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
                "Artist: " + getArtistName() + "\n" +
                "Genre: " + getGenre() + "\n" +
                "Streams: " + getStreams();
    }


}

