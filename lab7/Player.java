package lab7;

import java.util.Random;

public class Player {

    // data fields
    private static final int MAX_ID = 99999999;
    private static int count, allCount;
    private static int[] ids = new int[0];
    private static final String[] POSITIONS = {"SF", "PF", "SG", "PG", "C"};  
    private int id, rosterNumber, gamesPlayed;
    private double pointsPerGame, assistsPerGame, reboundsPerGame, turnoversPerGame, efficiency;
    private String name, position, team;
    private boolean isActive;

    // construtors
    public Player( String name, String position, String team, int rosterNumber) {
        register(name, position, team, rosterNumber);
    }

    // accessors
    public static int getCount() {
        return count;
    }

    public static int getAllCount() {
        return allCount;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public int getRosterNumber() {
        return rosterNumber;
    }

    public String getTeam() {
        return team;
    }

    public boolean getActive() {
        return isActive;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public double getPointsPerGame() {
        return pointsPerGame;
    }

    public double getAssistsPerGame() {
        return assistsPerGame;
    }

    public double getReboundsPerGame() {
        return reboundsPerGame;
    }

    public double getTurnoversPerGame() {
        return turnoversPerGame;
    }

    public double getEfficiency() {
        return efficiency;
    }

    // mutators
    public void setName(String name) {
        this.name = name;
    }

    private void setTeam(String team) {
        this.team = team;
    }

    public void setPosition(String position) {
        boolean validPos = false;
        position = position.toUpperCase();
        // check whether input position is valid
        for (String pos : POSITIONS) {
            if (pos.equals(position)) {
                validPos = true;
            }
        }
        if (validPos) {
            this.position = position;
        }
        else {
            System.out.println("Position is invalid! Valid positions are: SF, PF, SG, PG, C\nCurrent position is preserved (" + this.position + ")");
        }
    }

    public void setRosterNumber(int rosterNumber) {
        // valid roster numbers are positive integers up to 99
        if (rosterNumber < 1 || rosterNumber > 99) {
            this.rosterNumber = 1;
        }
        else {
            this.rosterNumber = rosterNumber;
        }
    }

    private void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * A recursive method that assigns a new valid random ID to the player
     * 
     * First creates a new random integer in the appropriate range
     * then runs itself until the ID is not taken by another player
     * updates the ID and appends the ID to the array
     */
    private void setId() {
        Random rnd = new Random();
        int newID = rnd.nextInt(MAX_ID) + 1; // random integer in [1, MAX_ID]
        for (int i = 0; i < ids.length; i++) {
            if (newID == ids[i]) { // repeat process if random ID matches with a previously assigned one
                setId();
                return;
            }
        }
        id = newID;
        appendID(id);
    }

    /**
     * A method that increases the IDs array's length by one and appends the ID to the end
     * @param id the valid ID of the player that is set randomly
     */
    private void appendID( int id) {
        int[] temp = ids;
        ids = new int[temp.length + 1];
        ids[ids.length - 1] = id; 
        for (int i = 0; i < ids.length - 1; i++) {
            ids[i] = temp[i];
        }
    }

    /**
     * Finds the ID of the player in the IDs array and removes it by keeping the data in a temporary array
     * whose values are copied over to the new IDs arrays except for the ID of the player
     */
    private void removeID () {
        int[] temp = ids;
        boolean idFound = false;
        int idIndex = 0;
        for (int i = 0; i < ids.length && !idFound; i++) {
            if (ids[i] == id) {
                idIndex = i;
                idFound = true;
                ids = new int[ids.length - 1];
            }
        }
        for (int i = 0; i < temp.length; i++) {
            if (i < idIndex) {
                ids[i] = temp[i];
            }
            else if (i > idIndex) {
                ids[i - 1] = temp[i];
            }
        }
        // if the next piece of code is executed, the dev knows there is something wrong
        if (!idFound) {
            System.out.println("Was unable to remove player");
        }
    }

    /**
     * registers a new player using appropriate values
     * @param name the name of the player as String
     * @param position the position of the player as String. These can be: SF, PF, SG, PG, C
     * @param team the team of the player as String in the format "AAA"
     * @param rosterNumber the roster (jersey) number of the player
     */
    private void register(String name, String position, String team, int rosterNumber) {
        setName(name);
        setPosition(position);
        transfer(team);
        setRosterNumber(rosterNumber);

        isActive = true;
        count++;
        allCount++;
        setId();
    }

    /**
     * removes all data from player instance 
     */
    public void remove() {
        if (isActive) {
            setActive(false);
            count--;
        }
        allCount--;
        removeID();
        setName(null);
        position = null;
        setTeam(null);
        rosterNumber = -1;

    }

    /**
     * sets the player as retired and removes data that an active player instance would have
     */
    public void retire() {
        setActive(false);
        count--;
        setTeam(null);
        position = null;
        rosterNumber = -1;
    }

    /**
     * Changes the team of the player if the argument is correct
     * @param team the new team of the player as String formatted as "OOO"
     */
    public void transfer( String team) {
        if (team.length() == 3) {
            this.team = team.toUpperCase();
        }
        else {
            System.out.println(team + " is not a valid team name. Current name is preserved (" + this.team + ")");
        }
    }
    
    
    // non-static methods

    /**
     * check whether the current player is before than the compared player lexicographically
     * the comparison uses UPPERCASE
     * @param player the player instance to be compared with
     * @return whether the current player comes before the compared player lexicographically
     */
    public boolean isBeforeThan( Player player) {
        return name.toUpperCase().compareTo(player.name.toUpperCase()) < 0;
    }

    /**
     * @param player the player to be compared
     * @return whether current player has a greater efficiency than the compared player
     */
    public boolean isMoreEfficient( Player player) {
        return efficiency > player.efficiency;
    }

    /**
     * Calculates the efficiency of the player using the formula:
     * E = 1*PPG + 0.7*(APG + RPG) - 0.9*TPG
     */
    private void calculateEfficiency() {
        efficiency = pointsPerGame + 0.7 * (assistsPerGame + reboundsPerGame) - 0.9 * turnoversPerGame; 
    }
    
    /**
     * Updates the players stats according to the arguments
     * @param pointsInGame number of points the player scores in one game
     * @param assistsInGame number of assists the player makes in one game
     * @param reboundsInGame number of rebounds the player catches in one game
     * @param turnoversInGame number of turnover the player is responsible for in one game
     */
    public void recordGame(int pointsInGame, int assistsInGame, int reboundsInGame, int turnoversInGame) {
        if (pointsInGame < 0 || assistsInGame < 0 || reboundsInGame < 0 || turnoversInGame < 0) { // checks the validity of arguments
            System.out.println("Game not recorded. All values must be non-negative.");
        }
        else {
            gamesPlayed++;
            pointsPerGame = (pointsPerGame + pointsInGame) / gamesPlayed;
            assistsPerGame = (assistsPerGame + assistsInGame) / gamesPlayed;
            reboundsPerGame = (reboundsPerGame + reboundsInGame) / gamesPlayed;
            turnoversPerGame = (turnoversPerGame + turnoversInGame) / gamesPlayed;
            calculateEfficiency();
        }
    }

    /**
     * A helper method to fill a line by repeating one character
     * @param ch the character to be used in the string
     * @param length the length of the line and string
     * @return A string that fills a line of length [length] with the given character
     */
    private String fillLine(char ch, int length) {
        String result = "";
        for (int i = 0; i < length; i++) {
            result = result + ch;
        }
        return result;
    }

    public String toString() {
        String retirement;
        if (isActive) {
            retirement = "Active among " + count + " active player(s)";
        }
        else {
            retirement = "Retired among " + (allCount - count) + " retired player(s)";
        }
        int lineLength = 19 + retirement.length();
        if (lineLength < 17 + name.length()) { lineLength = 17 + name.length(); }

        // the formatting took ages to complete :(
        return String.format("%s%n%-17s%d%n%-17s%s%n%-17s%s%n%-17s%s%n%-17s%d%n%s%n%s%n%-12s%5.2f%n%-12s%5.2f%n%-12s%5.2f%n%-12s%5.2f%n%-12s%5.2f%n%s%n%s%s%n%s%n", fillLine('*', lineLength), "Player ID: ", id, "Player Name: ", name, "Player Position: ", position, "Current Team: ", team, "Jersey Number: ", rosterNumber, fillLine('-', lineLength), "Stats:", "PPG: ", pointsPerGame, "APG: ", assistsPerGame, "RPG: ", reboundsPerGame, "TPG: ", turnoversPerGame, "Efficiency: ", efficiency, fillLine('-', lineLength), "Retirement Status: ", retirement, fillLine('*', lineLength));
    }

    // static methods
    
}
