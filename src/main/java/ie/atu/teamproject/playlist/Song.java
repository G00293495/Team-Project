package ie.atu.teamproject.playlist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Song implements Media{
    private String songName;
    private String genre;
    private Connection conn;
    private String artistName;




    //constructor

    public Song(String songName, String genre, Connection conn,String artistName) {
        this.songName = songName;
        this.genre = genre;
        this.artistName = artistName;
        this.conn = conn;

    }

    public Song(Connection conn) {
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

    @Override
    public void addMedia() {
        try
        {
            String sql = "INSERT INTO Song(songName) VALUES (?)";

            PreparedStatement psmt = conn.prepareStatement(sql);

            psmt.setString(1,songName);

            int rowsAffected = psmt.executeUpdate();

            if(rowsAffected == 1) {
                System.out.println("\n" + songName + "added to database succesfully");
            }
            else {
                System.out.println("\n Error: Failed to add" + songName + "to the database");
            }

        }
        catch (Exception ex){
            System.out.println("\nError" + ex.getMessage());
            ex.printStackTrace();
        }


    }

    @Override
    public void removeMedia() {
        try {

            String sql = " DELETE FROM Song WHERE songName = ?";

            PreparedStatement psmt = conn.prepareStatement(sql);

            psmt.setString(1,songName);

            int rowsAffected = psmt.executeUpdate();

            if(rowsAffected == 1) {
                System.out.println("\n" + songName + "removed from database succesfully");
            }
            else {
                System.out.println("\n Error: Failed to remove" + songName + "to the database");
            }

        }
        catch (Exception ex){
            System.out.println("\nError" +  ex.getMessage());
            ex.printStackTrace();
        }







    }
    @Override
    public void searchMedia(){
        try {
            String sql = "SELECT * FROM Song WHERE songName = ?";
            PreparedStatement psmt = conn.prepareStatement(sql);

            psmt.setString(1,songName);
            ResultSet search = psmt.executeQuery();

            if(search.next()) {
                System.out.println("\n" + songName + " Found in playlist ");
            }
            else {
                System.out.println("\n Error: Failed to find " + songName + " in the database");
            }




        }
        catch (Exception ex) {
            System.out.println("\nError" + ex.getMessage());
            ex.printStackTrace();
        }

    }
}




