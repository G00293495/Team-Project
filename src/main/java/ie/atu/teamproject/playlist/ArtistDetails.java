package ie.atu.teamproject.playlist;

public class ArtistDetails extends Playlist{
    private int age;
    private String realName;

    public ArtistDetails() {
        super();
        realName = "";
        age = 0;
    }

    public ArtistDetails(int age, String realName, String artistName) {
        super();
        this.age = age;
        this.realName = realName;
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

    @Override
    public String toString() {
        return "Artist Details: " + "\n" +
                "Artist: " + getArtistName() + "\n" +
                "Real Name: " + getRealName() + "\n" +
                "Age: " + getAge();
    }
}
