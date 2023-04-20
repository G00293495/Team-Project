package ie.atu.teamproject.playlist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class PlaylistApp {
    private static Playlist playlist = new Playlist();
    public static void main(String[] args) {
        try
        {
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://playlistserver.database.windows.net:1433;database=PlaylistExplorerDB;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;","playlistAdmin","password1.");
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

                case 1:
                    System.out.print("\nEnter the name of a song or artist: ");
                    String songOrArtist = scanner.nextLine();

                    Playlistable p = PlaylistDB.getPlaylist(songOrArtist);
                    if (p != null) {
                        System.out.println(p);
                    } else {
                        System.out.println("Song/Artist not found in the database");
                    }
                    break;

                case 2:
                    System.out.print("\nEnter the name of the artist you want to add: ");

                    break;  //template

                case 3:
                    System.out.print("\nEnter the name of the artist you want to remove: ");

                    break; //template

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