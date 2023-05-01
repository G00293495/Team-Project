package ie.atu.teamproject.playlist;

import java.sql.*;
import java.util.Scanner;

public class Artist implements Media {
    private String artistName;
    private Connection conn;

    //constructor
    public Artist(Connection conn) {
        this.conn = conn;
    }

    //getter setter
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    //methods

    public void searchArtist() {
        try {
            // Check if artist exists
            String artistSQL = "SELECT * FROM Artist WHERE artistName = ?";
            PreparedStatement artistStmt = conn.prepareStatement(artistSQL);
            artistStmt.setString(1, artistName);
            ResultSet artistRS = artistStmt.executeQuery();

            //if artist exists
            if (artistRS.next()) {
                // Print artist and songs for the artist
                int artistID = artistRS.getInt("artistID");
                String songsSQL = "SELECT * FROM Song WHERE artistID = ?";
                PreparedStatement songsStmt = conn.prepareStatement(songsSQL);
                songsStmt.setInt(1, artistID);
                ResultSet songsRS = songsStmt.executeQuery();

                System.out.println("Artist: " + artistName);
                // Retrieves song from artist
                while(songsRS.next()) {
                    String songTitle = songsRS.getString("songName");
                    System.out.println("Song Title: " + songTitle);
                }

            } else {
                // If artist does not exist, ask if they should be added
                System.out.print("Artist/Band not found in the database\n" +
                        "Would you like to add them? (y/n): ");

                Scanner scanner = new Scanner(System.in);
                String choice = scanner.nextLine();

                int artistID;
                if (choice.equals("y")) {
                    String sql = "INSERT INTO Artist (artistName) VALUES (?)";
                    PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    pstmt.setString(1, artistName);
                    pstmt.executeUpdate();
                    ResultSet generatedKeys = pstmt.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        artistID = generatedKeys.getInt(1);
                        System.out.println(artistName + " added to database");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("\nError " + e.getMessage());
        }
    }

    @Override
    public void addMedia() {
        try {
            String sql = "SELECT artistID FROM Artist WHERE artistName = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, artistName);
            ResultSet rs = pstmt.executeQuery();

            //if artist already exists in database
            int artistID;
            if (rs.next()) {
                artistID = rs.getInt("artistID");
                System.out.println(artistName + " already exists in the database");
            }

            //if artist doesn't exist, add them to the database
            else {
                sql = "INSERT INTO Artist (artistName) VALUES (?)";
                pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, artistName);
                pstmt.executeUpdate();
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    artistID = generatedKeys.getInt(1);
                    System.out.println(artistName + " added to database");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void removeMedia() {
        try {
            //Delete all songs with the specified artist name
            String songSql = "DELETE Song FROM Song JOIN Artist ON Song.artistId " +
                    "= Artist.artistId WHERE Artist.artistName = ?";
            PreparedStatement deleteSongsStmt = conn.prepareStatement(songSql);
            deleteSongsStmt.setString(1, artistName);
            int songsRowsAffected = deleteSongsStmt.executeUpdate();

            //Delete the artist
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
        }
    }




}