package ie.atu.teamproject.playlist;

import java.sql.*;
import java.util.Scanner;

public class PlaylistApp {
    static Connection conn;
    public static void main(String[] args) {
        //establish connection
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
            System.out.println("1. Search for a song or artist/band");
            System.out.println("2. Add a song or artist/band to the playlist");
            System.out.println("3. Remove a song or artist/band from the playlist");
            System.out.println("4. Shuffle Playlist");
            System.out.println("5. Recommend Artist");
            System.out.println("6. Exit");
            System.out.print("\nEnter your choice (1-6): ");

            int option = scanner.nextInt();
            scanner.nextLine();

            //connection established
            ie.atu.teamproject.playlist.Artist artist = new ie.atu.teamproject.playlist.Artist(conn);
            ie.atu.teamproject.playlist.Song song = new ie.atu.teamproject.playlist.Song(conn);

            switch (option) {
                //Artist Search Feature
                case 1 -> {
                    System.out.print("\nEnter the name of an Artist/Band: ");
                    String artistName = scanner.nextLine();
                    artist.setArtistName(artistName);
                    artist.searchArtist();

                }

                //Add feature
                case 2 -> {
                    System.out.println("\n1. Add a song");
                    System.out.println("2. Add an artist/band");
                    System.out.print("\nEnter your choice: ");
                    int ch = scanner.nextInt();
                    scanner.nextLine();
                    switch (ch) {
                        case 1:
                            // Add a song
                            System.out.print("\nEnter the name of the song: ");
                            String songName = scanner.nextLine();
                            System.out.print("\nEnter the name of the artist/band: ");
                            String artistName = scanner.nextLine();
                            song.setSongName(songName);
                            song.setArtistName(artistName);
                            song.addMedia();
                            break;
                        case 2:
                            // Add an artist/band
                            System.out.print("\nEnter the name of the artist/band: ");
                            artistName = scanner.nextLine();
                            artist.setArtistName(artistName);
                            artist.addMedia();
                            break;
                        default:
                            System.out.println("\nInvalid choice!");
                    }
                }

                //Remove feature
                case 3 -> {
                    System.out.println("\n1. Remove a song");
                    System.out.println("2. Remove an artist/band");
                    System.out.print("\nEnter your choice: ");
                    int ch = scanner.nextInt();
                    scanner.nextLine();
                    switch (ch) {
                        case 1 -> {
                            // Remove song
                            System.out.print("\nEnter the song you want to remove: ");
                            String songName = scanner.nextLine();
                            System.out.print("Enter the artist of the song: ");
                            String artistName = scanner.nextLine();
                            song.setSongName(songName);
                            song.setArtistName(artistName);
                            song.removeMedia();
                        }
                        case 2 -> {
                            //remove artist/band
                            System.out.print("\nEnter the name of the artist/band you want to remove: ");
                            String artistName = scanner.nextLine();
                            artist.setArtistName(artistName);
                            artist.removeMedia();
                        }
                    }
                }
                //shuffle song
                case 4 -> {
                    song.randomSong();
                }

                case 6 -> {
                    System.out.println("\nExiting program");
                    System.exit(0);
                }


                default -> System.out.println("\nInvalid option selected. Please choose 1-4");

            }
            System.out.print("\nDo you want to continue? (y/n): ");
            choice = scanner.nextLine();
        }

        System.out.println("\nGoodbye!");

    }




}