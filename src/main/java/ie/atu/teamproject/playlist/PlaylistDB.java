package ie.atu.teamproject.playlist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;


public class PlaylistDB {
    //methods will be done here e.g. add, remove, search will be done here
    Scanner scanner = new Scanner(System.in);
    public boolean addArtist() {

        //prompt user for artist name, real name & age.
        System.out.print("\nEnter the name of the artist you want to add: ");
        String artistName = scanner.nextLine();
        System.out.print("Enter the real name of the artist: ");
        String realName = scanner.nextLine();
        System.out.print("Enter the age of the artist: ");
        int age = Integer.parseInt(scanner.nextLine());

        boolean isAdded = false;

        try
        {
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
                System.out.println("\n " + artistName + " added to database successfully");
            }else{
                System.out.println("\n Error: Failed to add " + artistName + "to the database");
            }

        }catch (Exception e)
        {
            System.out.println("\nError " + e.getMessage());
            e.printStackTrace();
        }

        return isAdded;
    }

    public boolean removeArtist(){
        System.out.print("\nEnter the name of the artist you want to remove: ");
        String artistToRemove = scanner.nextLine();

        boolean isRemoved = false;

        return isRemoved;
    }
}
