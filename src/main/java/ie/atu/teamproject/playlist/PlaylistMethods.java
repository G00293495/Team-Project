package ie.atu.teamproject.playlist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;



public class PlaylistMethods {
    //methods will be done here e.g. add, remove, search will be done here
    Scanner scanner = new Scanner(System.in);
    public boolean removeArtist(){
        System.out.print("\nEnter the name of the artist you want to remove: ");
        String artistToRemove = scanner.nextLine();
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://playlistserver.database.windows.net:1433;database=PlaylistExplorerDB;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;", "playlistAdmin", "password1.");
            String sql = "DELETE FROM Artist WHERE Name = ?";

            //create prepared statement with sql this allows us to set params
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // Set params values
            pstmt.setString(1, artistToRemove);
            //execute prepared statement
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected == 1) {
                System.out.println("Successfully removed " + artistToRemove + " from database");

            } else {
                System.out.println("\nFailed to remove " + artistToRemove + " from the database");
            }

        }catch (Exception e){
            System.out.println("\nError " + e.getMessage());
            e.printStackTrace();
        }

        boolean isRemoved = false;

        return isRemoved;
    }
    public boolean removeSong(){
        System.out.print("\nEnter the name of the Song you want to remove: ");
        String songToRemove = scanner.nextLine();
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://playlistserver.database.windows.net:1433;database=PlaylistExplorerDB;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;", "playlistAdmin", "password1.");
            String sql = "DELETE FROM Song WHERE SongName = ?";

            //create prepared statement with sql this allows us to set params
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // Set params values
            pstmt.setString(1, songToRemove);
            //execute prepared statement
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected == 1) {
                System.out.println("Successfully removed " + songToRemove + " from database");

            } else {
                System.out.println("\nFailed to remove " + songToRemove + " from the database");
            }

        }catch (Exception e) {
            System.out.println("\nError " + e.getMessage());
            e.printStackTrace();
        }

        boolean isRemoved = false;

        return isRemoved;
    }
}
