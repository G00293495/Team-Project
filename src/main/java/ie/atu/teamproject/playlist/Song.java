    package ie.atu.teamproject.playlist;

    import java.sql.*;

    public class Song implements Media {
        private String songName;
        private Connection conn;
        private String artistName;

        //constructor
        public Song(String songName, Connection conn, String artistName) {
            this.songName = songName;
            this.conn = conn;
            this.artistName = artistName;
        }

        //getter setter
        public Connection getConn() {
            return conn;
        }

        public void setConn(Connection conn) {
            this.conn = conn;
        }

        public void setSongName(String songName) {
            this.songName = songName;
        }

        //methods
        @Override
        public void addMedia() {
            try {
                //check if the artist exists in the database
                String artistSQL = "SELECT artistId FROM Artist WHERE artistName = ?";
                PreparedStatement artistSTMNT = conn.prepareStatement(artistSQL);
                artistSTMNT.setString(1, artistName);
                ResultSet artistRs = artistSTMNT.executeQuery();
                int artistId;
                if (artistRs.next()) {
                    artistId = artistRs.getInt("artistId");
                } else {
                    //if the artist does not exist, add artist to the database
                    String addArtistSQL = "INSERT INTO Artist (artistName) VALUES (?)";
                    PreparedStatement addArtistSTMNT = conn.prepareStatement(addArtistSQL, Statement.RETURN_GENERATED_KEYS);
                    addArtistSTMNT.setString(1, artistName);
                    int artistRowsAffected = addArtistSTMNT.executeUpdate();
                    ResultSet generatedKeys = addArtistSTMNT.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        artistId = generatedKeys.getInt(1);
                    } else {
                        throw new SQLException("Creating artist failed, no ID obtained.");
                    }
                }

                //insert song
                String songSQL = "INSERT INTO Song (songName, artistId) VALUES (?, ?)";
                PreparedStatement addSongSQL = conn.prepareStatement(songSQL);
                addSongSQL.setString(1, songName);
                addSongSQL.setInt(2, artistId);
                int songRowsAffected = addSongSQL.executeUpdate();

                if (songRowsAffected == 1) {
                    System.out.println("\n" + songName + " by " + artistName + " added to database successfully");
                } else {
                    System.out.println("\nError: Failed to add " + songName + " to the database");
                }
            } catch (Exception e) {
                System.out.println("\nError " + e.getMessage());
                e.printStackTrace();
            }
        }



        @Override
        public void removeMedia() {

        }
    }
