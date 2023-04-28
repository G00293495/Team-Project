package ie.atu.teamproject.playlist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Objects;

public class Artist implements Media{
    private String artistName;
    private int artistID;
    private Connection conn;

    //constructor
    public Artist(Connection conn, String artistName) {
        this.conn = conn;
        this.artistName = artistName;
    }


    //getter setter
    public String getArtistName() {
        return artistName;
    }

    public int getArtistID() {
        return artistID;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    //methods
    @Override
    public void addMedia() {
        try {
            String sql = "INSERT INTO Artist(artistName) VALUES (?)";

            //create prepared statement with sql this allows us to set params
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // Set params values
            pstmt.setString(1, artistName);

            //execute prepared statement
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected == 1) {     //check if rows affected
                System.out.println("\n" + artistName + " added to database successfully");
            } else {
                System.out.println("\nError: Failed to add " + artistName + " to the database");
            }

        } catch (Exception e) {
            System.out.println("\nError " + e.getMessage());
            e.printStackTrace();
        }
    }


    @Override
    public void removeMedia(){
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://playlistserver.database.windows.net:1433;database=PlaylistExplorerDB;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;", "playlistAdmin", "password1.");
            String sql = "DELETE FROM Artist WHERE artistName = ?";

            //create prepared statement with sql this allows us to set params
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // Set params values
            pstmt.setString(1, artistName);
            //execute prepared statement
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected == 1) {
                System.out.println("Successfully removed " + artistName + " from database");

            } else {
                System.out.println("\nFailed to remove " + artistName + " from the database");
            }

        }catch (Exception e){
            System.out.println("\nError " + e.getMessage());
            e.printStackTrace();
        }

    }

}
