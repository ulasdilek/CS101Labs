package lab7;

public class PlayerLAB {
    //instance variables
    private boolean isActive;
    private int id, rosterNumber, gamesPlayed, totalPoints, totalAssists, totalRebounds,totalTurnovers;
    private double pointsPerGame, assistsPerGame, reboundsPerGame, turnoversPerGame, efficiency;
    private String name, position, team;
    
    //static variables
    private static int count, allCount, removedIDs;

    //constructors
    public PlayerLAB(String name, String position, String team, int rosterNumber) {
        register(name, position, team, rosterNumber);
    }

    //accessors
    public int getAllCount() {
        return allCount;
    }
    public int getCount() {
        return count;
    }
    public int getID() {
        return id;
    }
    public int getRosterNumber() {
        return rosterNumber;
    }
    public String getName() {
        return name;
    }
    public String getPosition() {
        return position;
    }
    public String getTeam() {
        return team;
    }
    public boolean getActive() {
        return isActive;
    }
    public double getEfficiency() {
        return efficiency;
    }

    //mutators
    public void setRosterNumber(int rosterNumber) {
        if (rosterNumber > 0 && rosterNumber < 100) {
            this.rosterNumber = rosterNumber;
        }
        else {
            this.rosterNumber = -1;
            System.out.println("Invalid roster number! Valid values are integers in (0, 100)");
        }
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPosition(String position) {
        position = position.toUpperCase();
        if (position.equals("SF") || position.equals("PF") || position.equals("SG") || position.equals("PG") || position.equals("C")) {
            this.position = position;
        }
        else {
            this.position = "NA";
            System.out.println("Invalid position! Valid positions are: SF, PF, SG, PG, C");
        }
    }
    private void setTeam(String team) {
        if (team.length() == 3) {
            this.team = team.toUpperCase();
        }
        else {
            this.team = "NNN";
            System.out.println("Invalid team! Team names are 3-letter abbreviations");
        }
    }
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    //instance methods
    public void register(String name, String position, String team, int rosterNumber) {
        setName(name);
        setPosition(position);
        setTeam(team);
        setRosterNumber(rosterNumber);
        setActive(true);
        count++;
        allCount++;
        id = allCount +removedIDs;
    }
    public void transfer(String team) {
        if (!team.equals(getTeam())){
            setTeam(team);
        }
        else {
            System.out.println("The player is already in this team.");
        }
    }
    public void retire() {
        setActive(false);
        count--;
        this.rosterNumber = -1;
        this.position = "NA";
        this.team = "NNN";
    }
    public void remove() {
        if (getActive()) {
            count--;
        }
        allCount--;
        id = -1;
        removedIDs++;
        this.rosterNumber = -1;
        this.position = null;
        this.team = null;
        //this = null;
    }
    public boolean isBeforeThan(PlayerLAB otherPlayer) {
        return this.name.toUpperCase().compareTo(otherPlayer.name.toUpperCase()) < 0;
    }
    public boolean isMoreEfficient(PlayerLAB otherPlayer) {
        return getEfficiency() > otherPlayer.getEfficiency();
    }
    public void recordGame(int points, int assists, int rebounds, int turnovers) {
        if (points >= 0 && assists >= 0 && rebounds >= 0 && turnovers >= 0) {
            totalPoints += points;
            totalAssists += assists;
            totalRebounds += rebounds;
            totalTurnovers += turnovers;
            updateStats();
        }
        else {
            System.out.println("Game stats are invalid! No record done.");
        }
    }
    
    @Override
    public String toString() {
        String retirement;
        if (isActive) {
            retirement = "Retirement Status: Active among " + getCount() + " active player(s)";
        }
        else {
            retirement = "Retirement Status: Retired among " + (getAllCount() - getCount()) + " retired player(s)";
        }

        // 17 is the char number of "Player Position: "
        int lineLength;
        if (retirement.length() > 17 + getName().length()) {
            lineLength = retirement.length();
        }
        else {
            lineLength = 17 + getName().length();
        }
        /*
        str
        str int
        str str
        str str
        str str
        str int
        str
        str
        str dou
        str dou
        str dou
        str dou
        str dou
        str
        str
        str
        */
        return String.format("%s%n%-17s%d%n%-17s%s%n%-17s%s%n%-17s%s%n%-17s%d%n%s%n%s%n%s%5.2f%n%s%5.2f%n%s%5.2f%n%s%5.2f%n%s%5.2f%n%s%n%s%n%s%n",
        fillLine('*', lineLength),
        "Player ID: ", getID(),
        "Player Name: ", getName(),
        "Player Position: ", getPosition(),
        "Current Team: ", getTeam(),
        "Jersey Number: ", getRosterNumber(),
        fillLine('-', lineLength),
        "Stats:",
        "PPG: ", pointsPerGame,
        "APG: ", assistsPerGame,
        "RPG: ", reboundsPerGame,
        "TPG: ", turnoversPerGame,
        "Efficiency: ", efficiency,
        fillLine('-', lineLength),
        retirement,
        fillLine('*', lineLength) );
    }

    //static methods

    //private methods
    private void updateStats() {
        gamesPlayed++;
        pointsPerGame = (double) totalPoints / gamesPlayed;
        assistsPerGame = (double) totalAssists/ gamesPlayed;
        reboundsPerGame = (double) totalRebounds / gamesPlayed;
        turnoversPerGame = (double) totalTurnovers / gamesPlayed;
        efficiency = pointsPerGame + 0.7 * (assistsPerGame + reboundsPerGame) - 0.9 * turnoversPerGame;
    }
    private String fillLine(char ch, int length) {
        String result = "";
        for (int i = 0; i < length; i++) {
            result += ch;
        }
        return result;
    }
}