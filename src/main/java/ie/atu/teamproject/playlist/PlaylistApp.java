package ie.atu.teamproject.playlist;

import java.util.Scanner;

public class PlaylistApp {
    private static Playlist playlist = new Playlist();

    public static void main(String[] args) {
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
                    String artistName = scanner.nextLine();

                    Artist newArtist = new Artist();
                    newArtist.setArtistName(artistName);
                    playlist.addArtist(newArtist);

                    System.out.println("\n" + artistName + " added to the playlist");
                    break;

                case 3:
                    System.out.print("\nEnter the name of the artist you want to remove: ");
                    String artistToRemove = scanner.nextLine();

                    boolean removed = playlist.removeArtist(artistToRemove);
                    if (removed) {
                        System.out.println("\n" + artistToRemove + " removed from the playlist");
                    } else {
                        System.out.println("\n" + artistToRemove + " not found in the playlist");
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