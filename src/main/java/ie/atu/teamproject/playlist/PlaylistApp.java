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
            System.out.println("4. Add song to the playlist");
            System.out.println("5. Remove song to the playlist");
            System.out.print("\nEnter your choice (1-5): ");

            int option = scanner.nextInt();
            scanner.nextLine();

            //connection established
            ie.atu.teamproject.playlist.Artist artist = new ie.atu.teamproject.playlist.Artist(conn);
            ie.atu.teamproject.playlist.Song song = new ie.atu.teamproject.playlist.Song(conn);

            switch (option) {
                //Artist Search Feature
                case 1 -> {
                    System.out.print("\nEnter the name of an Artist: ");
                    String artistName = scanner.nextLine();

                    try {
                        // Connect to the database
                        Connection conn = DriverManager.getConnection("jdbc:sqlserver://playlistserver.database.windows.net:1433;database=PlaylistExplorerDB;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;", "playlistAdmin", "password1.");
                        // Check if artist exists
                        String artistSQL = "SELECT * FROM Artist WHERE artistName = ?";
                        PreparedStatement artistStmt = conn.prepareStatement(artistSQL);
                        artistStmt.setString(1, artistName);
                        ResultSet artistRS = artistStmt.executeQuery();

                        if (artistRS.next()) {
                            // Print artist name and retrieve all songs for the artist
                            int artistID = artistRS.getInt("artistID");
                            String songsSQL = "SELECT * FROM ArtistSong JOIN Song ON ArtistSong.songID = Song.songID WHERE ArtistSong.artistID = ?";
                            PreparedStatement songsStmt = conn.prepareStatement(songsSQL);
                            songsStmt.setInt(1, artistID);
                            ResultSet songsRS = songsStmt.executeQuery();

                            System.out.println("Artist: " + artistName);
                            while (songsRS.next()) {
                                String songTitle = songsRS.getString("songName");
                                System.out.println("Song Title: " + songTitle);
                            }
                        } else {
                            System.out.println("Artist not found in the database");
                        }

                        conn.close();
                    } catch (SQLException e) {
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
                    System.out.print("Enter song name: ");
                    String songName = scanner.nextLine();
                    System.out.print("Enter artist name: ");
                    String artistName = scanner.nextLine();
                    if (artistName.isBlank()) {
                        System.out.println("\nError: artistName cannot be null");
                        return;
                    }
                    artist.setArtistName(artistName);
                    song.setSongName(songName);
                    song.addMedia();
                }

                case 5 -> {
                    System.out.print("\nWhat song do you want to remove song: ");
                    String songName = scanner.nextLine();
                    song.setSongName(songName);
                    song.removeMedia();
                }

                default -> System.out.println("\nInvalid option selected. Please choose 1-5");
            }
            System.out.print("\nDo you want to continue? (y/n): ");
            choice = scanner.nextLine();
        }

        System.out.println("\nGoodbye!");

    }


}