package ie.atu.teamproject.playlist;

import java.sql.*;
import java.util.Scanner;

public class PlaylistApp {
    private static final Playlist playlist = new Playlist();
    static Connection conn;

    public static void main(String[] args) {
        try
        {
            conn = DriverManager.getConnection("jdbc:sqlserver://playlistserver.database.windows.net:1433;database=PlaylistExplorerDB;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;","playlistAdmin","password1.");
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

            ie.atu.teamproject.playlist.Artist artist = new ie.atu.teamproject.playlist.Artist(conn);

            switch (option) {
                //Search Feature
                case 1 -> {
                    System.out.print("\nEnter the name of an Artist: ");
                    String songOrArtist = scanner.nextLine();

                    //Artist Search
                    try {
                        //Connection conn = DriverManager.getConnection("jdbc:sqlserver://playlistserver.database.windows.net:1433;database=PlaylistExplorerDB;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;", "playlistAdmin", "password1.");
                        Statement stmt = conn.createStatement();

                        String artistSQL = "SELECT * FROM Artist where Name = '" + songOrArtist + "'"; // Checks database for artist inputted
                        ResultSet artistRS = stmt.executeQuery(artistSQL); //executes SELECT in sql
                        if (artistRS.next()) { //check if artist is in database
                            String artistName = artistRS.getString("Name"); //searches name column

                            artist.setArtistName(artistName);
                            System.out.println("Artist: " + "\n" + artistName);

                            //prompt user about artist info
                            System.out.print("\nDo you want to learn more about the artist? (y/n): ");
                            String userChoice = scanner.nextLine();
                            if (userChoice.equalsIgnoreCase("y")) {
                                System.out.println(artist);
                            }

                        } else {
                            System.out.println("Song/Artist not found in the database");
                        }
                    } catch (Exception e) {
                        System.out.println("\nError " + e.getMessage());
                        e.printStackTrace();
                    }
                }

                //Add feature
                case 2 -> {
                    System.out.print("\nEnter the name of the artist you want to add: ");
                    String artistName = scanner.nextLine();
                    artist.setArtistName(artistName);
                    artist.addMedia();
                }

                //Remove feature
                case 3 -> {
                    System.out.print("\nEnter the name of the artist you want to remove: ");
                    String artistName = scanner.nextLine();
                    artist.setArtistName(artistName);
                    artist.removeMedia();
                }
                case 4 -> {
                    System.out.println("Do you want to add SongId, SongName, Genre, ArtistId");
                    Scanner scanner2 = new Scanner(System.in);
                    int SongID = scanner2.nextInt();
                    scanner2.nextLine();
                    String SongName = scanner2.nextLine();
                    String Genre = scanner2.nextLine();
                    int ArtistID = scanner2.nextInt();

                    addSong(conn,SongID,SongName,Genre,ArtistID);



                }

                default -> System.out.println("\nInvalid option selected. Please choose 1-3");
            }

            System.out.print("\nDo you want to continue? (y/n): ");
            choice = scanner.nextLine();
        }

        System.out.println("\nGoodbye!");

    }
    private static void addSong(Connection conn, int SongID, String SongName, String Genre, int ArtistID){

        try {
            Statement stmt = conn.createStatement();

            String songSql = "INSERT INTO Song (SongID, SongName, Genre, ArtistID) VALUES (" + SongID + ", " + SongName + "," + Genre + "," + ArtistID +" )";
            stmt.executeUpdate(songSql);
            System.out.println("Data inserted successfully into MySQL.");

            stmt.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }

    }


}