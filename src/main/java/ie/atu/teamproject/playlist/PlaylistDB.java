package ie.atu.teamproject.playlist;

public class PlaylistDB {

    public static Playlistable getPlaylist(String songID){
        // In a more realistic application, this code would
        // get the data for the product from a file or database
        // For now, this code just uses if/else statements
        // to return the correct product data

        Playlistable p = null;

        if(songID.equalsIgnoreCase("The Hills"))//he made graduation //ref off productDB
        {
            Song song = new Song();
            song.setSongName("The Hills");
            song.setGenre("Alternative R&B");
            song.setStreams(1800000);
            p = song;
        }

        return p;
    }
}
