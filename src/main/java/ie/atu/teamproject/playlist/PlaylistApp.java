package ie.atu.teamproject.playlist;

import java.sql.*;
import java.util.Scanner;

public class PlaylistApp {
    private static Playlist playlist = new Playlist();
    public static void main(String[] args) {
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

                case 1:
                    System.out.print("\nEnter the name of a song or artist: ");
                    String songOrArtist = scanner.nextLine();
                    try {
                        Connection conn = DriverManager.getConnection("jdbc:sqlserver://playlistserver.database.windows.net:1433;database=PlaylistExplorerDB;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;","playlistAdmin","password1.");
                        Statement stmt = conn.createStatement();
                        String sql = "SELECT * FROM Artist where name = '" + songOrArtist + "'";
                        ResultSet rs = stmt.executeQuery(sql);
                        if (rs.next()){
                            String artistName = rs.getString("name");
                            Artist artist = new Artist();
                            artist.setArtistName(artistName);
                            Playlistable p = artist;
                            System.out.println(p);
                        }else{
                            System.out.println("Song/Artist not found in the database");
                        }
                    }catch (Exception e){
                        System.out.println("\nError");

                    }

                    break;

                case 2:
                    System.out.print("\nEnter the name of the artist you want to add: ");
                    String artistName = scanner.nextLine();

                    Artist newArtist = new Artist();
                    newArtist.setArtistName(artistName);

                    try {
                        Connection conn = DriverManager.getConnection("jdbc:sqlserver://playlistserver.database.windows.net:1433;database=PlaylistExplorerDB;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;","playlistAdmin","password1.");
                        Statement stmt = conn.createStatement();
                    }catch (Exception e){
                        System.out.println("\nError");
                    }
                    break;

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