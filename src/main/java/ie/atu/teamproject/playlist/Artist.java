package ie.atu.teamproject.playlist;

import java.sql.*;

public class Artist implements Media {
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
            else {
                sql = "INSERT INTO Artist (artistName) VALUES (?)";
                pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, artistName);
                pstmt.executeUpdate();
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
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
    public void removeMedia() {
        try {
            // Delete all songs with the specified artist name
            String songSql = "DELETE Song FROM Song JOIN Artist ON Song.artistId " +
                    "= Artist.artistId WHERE Artist.artistName = ?";
            PreparedStatement deleteSongsStmt = conn.prepareStatement(songSql);
            deleteSongsStmt.setString(1, artistName);
            int songsRowsAffected = deleteSongsStmt.executeUpdate();

            // Delete the artist
            String artistSQL = "DELETE FROM Artist WHERE artistName = ?";
            PreparedStatement deleteArtistStmt = conn.prepareStatement(artistSQL);
            deleteArtistStmt.setString(1, artistName);
            int artistRowsAffected = deleteArtistStmt.executeUpdate();

            if (artistRowsAffected == 1) {
                System.out.println("Successfully removed " + artistName + " and " + songsRowsAffected +
                        " songs from database");
            } else {
                System.out.println("\nFailed to remove " + artistName + " from the database");
            }

        } catch (Exception e) {
            System.out.println("\nError " + e.getMessage());
            e.printStackTrace();
        }
    }
}