package ie.atu.teamproject.playlist;

import java.sql.*;

public class Song implements Media {
    private String songName;
    private Connection conn;
    private String artistName;

    //constructor
    public Song(Connection conn) {
        this.conn = conn;
    }

    //getter setter
    public void setSongName(String songName) {
        this.songName = songName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    //methods
    @Override
    public void addMedia() {
        try {
            //check if the artist exists in the database
            String artistSQL = "SELECT artistId FROM Artist WHERE artistName = ?";
            PreparedStatement artistSTMNT = conn.prepareStatement(artistSQL);
            //Set prepared statement artistName param to the value of the artistName variable.
            artistSTMNT.setString(1, artistName);
            ResultSet resultset = artistSTMNT.executeQuery();

            int artistId;
            if (resultset.next()) {
                // If artist exists in the DB, get the artistID
                artistId = resultset.getInt("artistId");
            } else {
                //if the artist does not exist, add artist to the database
                String addArtistSQL = "INSERT INTO Artist (artistName) VALUES (?)";
                PreparedStatement statement = conn.prepareStatement(addArtistSQL, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, artistName);
                int affectedRows = statement.executeUpdate();

                //Execute the INSERT statement and retrieve the generated keys
                ResultSet generatedKeys = statement.getGeneratedKeys();
                // Check if the artist creation was successful and retrieve the generated artistID.
                if (generatedKeys.next()) {
                    artistId = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating artist failed, artistID not obtained.");
                }
            }

            //insert song
            // Create INSERT statement for adding a song with the inputted song name and artist ID
            String songSQL = "INSERT INTO Song (songName, artistId) VALUES (?, ?)";
            PreparedStatement statement = conn.prepareStatement(songSQL);
            // Set the first parameter of the prepared statement to the value of the songName variable
            statement.setString(1, songName);
            // Set the second parameter of the prepared statement to the value of the artistId variable
            statement.setInt(2, artistId);
            // Execute statement and retrieve the number of rows affected
            int songRowsAffected = statement.executeUpdate();

            if (songRowsAffected == 1) {
                System.out.println("\n" + songName + " by " + artistName +
                        " added to database successfully");
            } else {
                System.out.println("\nError: Failed to add " + songName +
                        " to the database");
            }
        } catch (Exception e) {
            System.out.println("\nError " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void removeMedia() {
    }

    @Override
    public void randomSong(){
        try{

            String sql = "SELECT TOP 1 songName FROM Song ORDER BY NEWID()";
            PreparedStatement statement = conn.prepareStatement(sql);
            //statement.setInt(1, personId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("songName");
                System.out.println("Random Song: " + name);

            }

        }
        catch (SQLException ex){
            System.out.println("\nError " + ex.getMessage());
            ex.printStackTrace();

        }

    }
}