package ie.atu.teamproject.playlist;

import java.util.Scanner;

public class PlaylistApp {
    public static void main(String[] args) {
        System.out.println("Welcome to playlist App");
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        String choice = "y";
        //while (choice.equalsIgnoreCase("y")) {
            System.out.println("Enter Song Or Artist Name"); //change to song name or artist name

            String SongID = scanner.nextLine();

            Playlistable p = PlaylistDB.getPlaylist(SongID);
            if(p != null) {
                System.out.println(p);
            }
            else {
                System.out.println("Song/Artist Can't be found");
            }
        //}
    }
}
