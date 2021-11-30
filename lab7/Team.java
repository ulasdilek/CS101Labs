package lab7;

public class Team {

    //instance variables
    private String name, abbreviation, stadiumName, city;
    private int playerCount;

    //static variables

    //constructors
    public Team(String name, String abbreviation, String stadiumName, String city) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.stadiumName = stadiumName;
        this.city = city;
        playerCount = 0;
    }

    //accessors
    public String getName() {
        return name;
    }
    public String getAbbreviation() {
        return abbreviation;
    }
    public String getStadiumName() {
        return stadiumName;
    }
    public String getCity() {
        return city;
    }
    public int getPlayerCount() {
        return playerCount;
    }

    //mutators

    //instance methods
    public void transferPlayer() {
        playerCount++;
        
    }

    public void releasePlayer() {
        playerCount--;
        
    }

    public void changeStadium(String stadium) {
        stadiumName = stadium;
    }

    public void changeOwnership(String name, String abbreviation, String city) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.city = city;
    }

    @Override
    public String toString() {
        int lineLength = 0;
        String line1 = "Team name: " + name;
        String line2 = "Abbreviation: " + abbreviation;
        String line3 = "Number of players: " + playerCount;
        String line4 = "Stadium name: " + stadiumName;
        String line5 = "City: " + city;
        if (line1.length() > lineLength) {
            lineLength = line1.length();
        }
        if (line2.length() > lineLength) {
            lineLength = line2.length();
        }
        if (line3.length() > lineLength) {
            lineLength = line3.length();
        }
        if (line4.length() > lineLength) {
            lineLength = line4.length();
        }
        if (line5.length() > lineLength) {
            lineLength = line5.length();
        }

        return String.format("%s%n%s%n%s%n%s%n%s%n%s%n%s%n",
        fillLine('*', lineLength),
        line1,
        line2,
        line3,
        line4,
        line5,
        fillLine('*', lineLength) );
    }

    //static methods

    //private methods
    private String fillLine(char ch, int length) {
        String result = "";
        for (int i = 0; i < length; i++) {
            result += ch;
        }
        return result;
    }
}
