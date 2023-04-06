package ie.atu.teamproject.playlist;

public class PlaylistDB {
    public static Playlistable getPlaylist(String songID){
        Playlistable p = null;

        if(songID.equalsIgnoreCase("The Hills"))
        {
            Song song = new Song();
            song.setSongNames("The Hills");
            song.setArtistName("The Weekend");
            song.setGenre("Alternative R&B");
            song.setStreams(1800000);

            p = song;
        }
        else if (songID.equalsIgnoreCase("The Weekend"))
        {
            Artist artist = new Artist();
            artist.setArtistName("The Weekend");
            artist.setRealName("Abel Makkonen Tesfaye");
            artist.setAge(32);

            p = artist;
        }
        else if (songID.equalsIgnoreCase("Enter Sandman"))
        {
            Song song = new Song();
            song.setSongNames("Enter Sandman");
            song.setArtistName("Metallica");
            song.setGenre("Heavy Metal");
            song.setStreams(1600000000);

            p = song;
        }

        return p;
    }
}
