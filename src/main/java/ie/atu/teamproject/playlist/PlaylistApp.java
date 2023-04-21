package ie.atu.teamproject.playlist;

import java.sql.*;
import java.util.Scanner;

public class PlaylistApp {
    private static Playlist playlist = new Playlist();
    public static void main(String[] args) throws SQLException {
        try
        {
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://playlistserver.database.windows.net:1433;database=PlaylistExplorerDB;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;","playlistAdmin","password1.");
            System.out.println("Database Connection Successful!");
        }
        catch (SQLException e)
        {
            System.out.println("Connection Failed\n");
        }

        Scanner scanner = new Scanner(System.in);
        String choice = "y";

        System.out.println("Welcome to Playlist App!\n");

        while (choice.equalsIgnoreCase("y")) {

            System.out.println("Playlist App Menu:");
            System.out.println("1. Search for a song or artist");
            System.out.println("2. Add an artist to the playlist");
            System.out.println("3. Remove an artist from the playlist");
            System.out.print("\nEnter your choice (1-3): ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {

                //Search Feature
                case 1:
                    System.out.print("\nEnter the name of a song or artist: ");
                    String songOrArtist = scanner.nextLine();

                    //Artist Search
                    try {
                        Connection conn = DriverManager.getConnection("jdbc:sqlserver://playlistserver.database.windows.net:1433;database=PlaylistExplorerDB;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;","playlistAdmin","password1.");
                        Statement stmt = conn.createStatement();

                        String artistSQL = "SELECT * FROM Artist where Name = '" + songOrArtist + "'"; // Checks database for artist inputted
                        ResultSet artistRS = stmt.executeQuery(artistSQL); //executes SELECT in sql
                        if (artistRS.next()){ //check if artist is in database
                            String artistName = artistRS.getString("Name"); //searches name column
                            String artistRealName = artistRS.getString("RealName");//Colum is RealName
                            int artistAge = artistRS.getInt("Age");

                            Artist artist = new Artist();
                            artist.setArtistName(artistName);
                            artist.setRealName(artistRealName);
                            artist.setAge(artistAge);
                            Playlistable p = artist;
                            System.out.println("Artist " + artistName);

                            //Print the top 5 Songs from artist based on spotify stats
                            System.out.println("Top 5 Songs");
                            //feel free to edit this, im unsure
                            String songsSQL = "SELECT * FROM Song WHERE Album = ''"; //album is a placeholder for songName
                            ResultSet songsRS = stmt.executeQuery(songsSQL);

                            //prompt user about artist info
                            System.out.print("\nDo you want to learn more about the artist? (y/n): ");
                            String userChoice = scanner.nextLine();
                            if (userChoice.equalsIgnoreCase("y"))
                            {
                                System.out.println(p);
                            }


                        }else{
                            System.out.println("Song/Artist not found in the database");
                        }
                    }catch (Exception e){
                        System.out.println("\nError " + e.getMessage());
                        e.printStackTrace();
                    }
                    break;

                    //Add feature
                case 2:
                    //prompt user for artist name, real name & age.
                    PlaylistDB playlistDB = new PlaylistDB();
                    boolean isAdded = playlistDB.addArtist();

                    //code to method above where it originally was, remove later
                    /*
                    System.out.print("\nEnter the name of the artist you want to add: ");
                    String artistName = scanner.nextLine();
                    System.out.print("Enter the real name of the artist: ");
                    String realName = scanner.nextLine();
                    System.out.print("Enter the age of the artist: ");
                    int age = Integer.parseInt(scanner.nextLine());

                    Artist newArtist = new Artist();
                    newArtist.setArtistName(artistName);

                    try {
                        Connection conn = DriverManager.getConnection("jdbc:sqlserver://playlistserver.database.windows.net:1433;database=PlaylistExplorerDB;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;","playlistAdmin","password1.");

                        String sql = "INSERT INTO Artist(Name, RealName, Age) VALUES (?, ?, ?)";

                        //create prepared statement with sql this allows us to set params
                        PreparedStatement pstmt = conn.prepareStatement(sql);

                        // Set params values
                        pstmt.setString(1, artistName);
                        pstmt.setString(2, realName);
                        pstmt.setInt(3, age);

                        //execute prepared statement
                        int rowsAffected = pstmt.executeUpdate();

                        if(rowsAffected == 1) {     //check if rows affected
                            playlist.addArtist(newArtist);  //if rows affected, adds the artist to database
                            System.out.println("\n " + artistName + " added to database successfully");
                        }else{
                            System.out.println("\n Error: Failed to add " + artistName + "to the database");
                        }
                    }catch (Exception e){
                        System.out.println("\nError " + e.getMessage());
                        e.printStackTrace();
                    }
                    */
                    break;

                    //Remove feature
                case 3:
                    System.out.print("\nEnter the name of the artist you want to remove: ");
                    String artistToRemove = scanner.nextLine();

                    try {
                        Connection conn = DriverManager.getConnection("jdbc:sqlserver://playlistserver.database.windows.net:1433;database=PlaylistExplorerDB;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;","playlistAdmin","password1.");

                        String sql = "DELETE FROM Artist WHERE Name = ?";

                        //create prepared statement with sql this allows us to set params
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        // Set params values
                        pstmt.setString(1, artistToRemove);
                        //execute prepared statement
                        int rowsAffected = pstmt.executeUpdate();

                        if (rowsAffected == 1) {
                            boolean removed = playlist.removeArtist(artistToRemove);
                            if (removed) {
                                System.out.println("\n" + artistToRemove + " removed successfully from database");
                            } else {
                                System.out.println("Failed to remove " + artistToRemove + " from database");
                            }
                        } else{
                            System.out.println("\nFailed to remove " + artistToRemove + " from the database");
                        }
                    }catch (SQLException e){
                        System.out.println("\nError " + e.getMessage());
                        e.printStackTrace();
                    }
                    break;

                default:
                    System.out.println("\nInvalid option selected. Please choose 1-3");
                    break;
            }

            System.out.print("\nDo you want to continue? (y/n): ");
            choice = scanner.nextLine();
        }

        System.out.println("\nGoodbye!");

    }
}