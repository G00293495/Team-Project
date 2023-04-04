package ie.atu.teamproject.playlist;

public class Artist extends Playlist{
    private int age;
    private String realName;
    private String artistName;

    public Artist() {
        super();
        realName = "";
        age = 0;
        artistName = "";
    }

    public Artist(int age, String realName, String artistName) {
        super();
        this.age = age;
        this.realName = realName;
        this.artistName = artistName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    @Override
    public String toString() {
        return "Artist Details: " + "\n" +
                "Artist: " + getArtistName() + "\n" +
                "Real Name: " + getRealName() + "\n" +
                "Age: " + getAge();
    }
}
