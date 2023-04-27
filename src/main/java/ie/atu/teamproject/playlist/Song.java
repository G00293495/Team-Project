package ie.atu.teamproject.playlist;

import javax.management.Query;
import java.sql.*;
import java.util.ArrayList;
import java.util.Queue;

public class Song implements Media {
    private String songName;
    private Connection conn;
    private String artistName;
    private static ArrayList<Song> songList = new ArrayList<>();

    //constructor
    public Song(String songName, Connection conn,String artistName) {
        this.songName = songName;
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
    //methods

    //@Sean I referenced this from here
    //https://stackoverflow.com/questions/1376218/is-there-a-way-to-retrieve-the-autoincrement-id-from-a-prepared-statement
    @Override
        public void addMedia() {
        String sqlSong = "INSERT INTO Song(songName) VALUES (?)";
        try(PreparedStatement statement = conn.prepareStatement(sqlSong,Statement.RETURN_GENERATED_KEYS))
        {
            //insert song into Song TABLE
            statement.setString(1,songName);
            int affectedRows = statement.executeUpdate();

            //get generated keys
            int songID = -1;
            if(affectedRows == 1)
            {
                ResultSet rs = statement.getGeneratedKeys();
                rs.next();
                songID = rs.getInt(1);
            }

            //insert artist into Artist Table
            if (artistName == null) {   //artist must be null
                System.out.println("\nError: artistName cannot be null");
                return;
            }

            String artistInsertSql = "INSERT INTO Artist(artistName) VALUES (?)";
            PreparedStatement artistInsertStmt = conn.prepareStatement(artistInsertSql, PreparedStatement.RETURN_GENERATED_KEYS);
            artistInsertStmt.setString(1, artistName);
            affectedRows = artistInsertStmt.executeUpdate();

            //get generated keys
            int artistID = -1;
            {
                ResultSet rs = artistInsertStmt.getGeneratedKeys();
                rs.next();
                artistID = rs.getInt(1);
            }

            if (affectedRows == 1) {
                System.out.println("\n" + artistName + " added to database successfully");
            } else {
                System.out.println("\nError: Failed to add " + artistName + " to the database");
                return;
            }

            //relation between Artist & Song
            String artistSongInsertSql = "INSERT INTO ArtistSong(songID, artistID) VALUES (?, ?)";
            PreparedStatement artistSongInsertStmt = conn.prepareStatement(artistSongInsertSql);
            artistSongInsertStmt.setInt(1, songID);
            artistSongInsertStmt.setInt(2, artistID);
            affectedRows = artistSongInsertStmt.executeUpdate();

            if (affectedRows == 1) {
                System.out.println("\nRelationship between " + songName + " and " + artistName + " added to database successfully");
            } else {
                System.out.println("\nError: Failed to add relationship between " + songName + " and " + artistName + " to the database");
                return;
            }

            //Add the song to the songList ArrayList
            Song newSong = new Song(songName, conn, artistName);
            songList.add(newSong);
            System.out.println("\n" + songName + " added to song list successfully");

        }
        catch (Exception ex){
            System.out.println("\nError: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    /*
    try {
            // Insert song into Song table
            String songInsertSql = "INSERT INTO Song(songName) VALUES (?)";
            PreparedStatement songInsertStmt = conn.prepareStatement(songInsertSql, Statement.RETURN_GENERATED_KEYS);
            songInsertStmt.setString(1, songName);
            int rowsAffected = songInsertStmt.executeUpdate();

            // Get the generated song ID
            ResultSet generatedKeys = songInsertStmt.getGeneratedKeys();
            int songId = -1;
            if (generatedKeys.next()) {
                songId = generatedKeys.getInt(1);
            }

            if (rowsAffected == 1) {
                System.out.println("\n" + songName + " added to database successfully");
            } else {
                System.out.println("\nError: Failed to add " + songName + " to the database");
                return;
            }

            // Insert artist into Artist table
            if (artistName == null) {
                System.out.println("\nError: artistName cannot be null");
                return;
            }

            String artistInsertSql = "INSERT INTO Artist(artistName) VALUES (?)";
            PreparedStatement artistInsertStmt = conn.prepareStatement(artistInsertSql, PreparedStatement.RETURN_GENERATED_KEYS);
            artistInsertStmt.setString(1, artistName);
            rowsAffected = artistInsertStmt.executeUpdate();

            // Get the generated artist ID
            generatedKeys = artistInsertStmt.getGeneratedKeys();
            int artistId = -1;
            if (generatedKeys.next()) {
                artistId = generatedKeys.getInt(1);
            }

            if (rowsAffected == 1) {
                System.out.println("\n" + artistName + " added to database successfully");
            } else {
                System.out.println("\nError: Failed to add " + artistName + " to the database");
                return;
            }

            // Insert the relationship between song and artist into ArtistSong table
            String artistSongInsertSql = "INSERT INTO ArtistSong(songID, artistID) VALUES (?, ?)";
            PreparedStatement artistSongInsertStmt = conn.prepareStatement(artistSongInsertSql);
            artistSongInsertStmt.setInt(1, songId);
            artistSongInsertStmt.setInt(2, artistId);
            rowsAffected = artistSongInsertStmt.executeUpdate();

            if (rowsAffected == 1) {
                System.out.println("\nRelationship between " + songName + " and " + artistName + " added to database successfully");
            } else {
                System.out.println("\nError: Failed to add relationship between " + songName + " and " + artistName + " to the database");
                return;
            }

            // Add the song to the songList ArrayList
            Song newSong = new Song(songName, conn, artistName);
            songList.add(newSong);
            System.out.println("\n" + songName + " added to song list successfully");

        } catch (Exception ex) {
            System.out.println("\nError: " + ex.getMessage());
            ex.printStackTrace();
        }
     */

    @Override
    public void removeMedia() {

    }
    /*try {

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
            System.out.println("\nError" + ex.getMessage());
            ex.printStackTrace();
        }*/
}

