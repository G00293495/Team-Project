package ie.atu.teamproject.playlist;

import java.sql.Connection;

public class Song {
    private String songName;
    private String genre;
    private Connection conn;

    //constructor

    public Song(String songName, String genre, Connection conn) {
        this.songName = songName;
        this.genre = genre;
        this.conn = conn;
    }

    //getter setter

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

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

    //methods

}

