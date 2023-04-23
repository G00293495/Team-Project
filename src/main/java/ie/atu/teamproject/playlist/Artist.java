package ie.atu.teamproject.playlist;

import java.util.ArrayList;

public class Artist extends Playlist{
    /*
    private int age;
    private String realName;

    public Artist() {
        super();
        realName = "";
        age = 0;
    }

    public Artist(int age, String realName, String artistName) {
        super();
        this.age = age;
        this.realName = realName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Override
    public String toString() {
        return "Artist Details: " + "\n" +
                "Artist: " + getArtistName() + "\n" +
                "Real Name: " + getRealName() + "\n" +
                "Age: " + getAge();
    }*/

    private String artistName;
    private ArrayList<Song> songs;

    //getter setter
    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }
}
