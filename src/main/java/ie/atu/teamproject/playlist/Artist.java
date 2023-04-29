package ie.atu.teamproject.playlist;

import java.sql.*;

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
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    //methods
    @Override
    public void addMedia() {
        try {
            String sql = "SELECT artistID FROM Artist WHERE artistName = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, artistName);
            ResultSet rs = pstmt.executeQuery();
            // artist already exists in database
            if (rs.next()) {
                artistID = rs.getInt("artistID");
                System.out.println(artistName + " already exists in the database " + artistID);

            }
            // artist doesn't exist, add them to the database
            else
            {
                sql = "INSERT INTO Artist (artistName) VALUES (?)";
                pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, artistName);
                pstmt.executeUpdate();
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next())
                {
                    artistID = generatedKeys.getInt(1);
                    System.out.println(artistName + " added to database with ID " + artistID);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void removeMedia(){
        try {
            String sql = "DELETE FROM Artist WHERE artistName = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, artistName);
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
