package lab10;

/**
 * This class is implemented in accordance to the lab assignment but I have used some initiative for a few things.
 * {@code Project_Rev} class works in harmony with {@code Hackathon_Rev} class and performs some utility actions to
 * modify all scores, calculate the weighted average and store credibility of the {@code Project_Rev} instance.
 * @author Ulas Dilek
 */
public class Project_Rev {
    //static variables
    public static final int INITIAL = 0, MODIFIED = 1, FINAL = 2;
    private static final int[][] INDICATOR_BOUNDARIES_AND_SCORES = {{500, 1000, 20, 80, 100}, {6, 12, 100, 80, 20}, {10, 15, 20, 80, 100}, {5, 10, 20, 80, 100}};
    public static final String ACCEPTED = "INVEST", REJECTED = "DO NOT INVEST", DISQUALIFIED = "REMOVED";

    //instance variables
    private String ID;
    /**ßß
     * This value is determined by the weights assigned in {@code hackathon}.
     * If {@code hackathon} is null, then {@code finalScore} will not be calculated.
     * {@code finalScore} needs to be calculated in order to be able to evaluate
     * {@code segment} and {@code credibility}
     */
    private double finalScore;
    private String segment;
    private String credibility;
    private int[] rawScores;
    private int[] modifiedScores;
    /**
     * A {@code Hackathon_Rev} instance is required for some actions such as calculating the {@code finalScore} of the {@code Project_Rev} instance.
     */
    private Hackathon_Rev hackathon;
    /**
     * {@code status} is always equal to one of {@code Project_Rev.INITIAL}, {@code Project_Rev.MODIFIED} and {@code Project_Rev.FINAL}.
     * 
     * It is used to determine the state of {@code modifiedScores}. These are
     *      {@code INITIAL}: A copy of {@code rawScores}
     *      {@code MODIFIED}: Each score is clamped to the range (0, 100) according to its rules predetermined in the assignment.
     *      {@code FINAL}: {@code finalScore}, {@code segment} and {@code credibility} values are finalised according to {@code modifiedScores}
     */
    private int status;
    
    //constructors
    /**
     * The only constructor of {@code Project_Rev}. Initializes all required instance variables appropriately.
     * @param ID The ID of the {@code Project_Rev} instance. It is expected to be in the format {@code "X0"} where X represents a {@code String} of length 1 and 0 represents a single digit number.
     * @param rawScores An {@code int[]} of length {@code Hackathon_Rev.INDICATOR_COUNT} that contains the raw scores of the {@code Project_Rev} instance that are going to be clamped to range (0, 100) according to a set of rules predetermined in the lab assignment.
     */
    public Project_Rev(String ID, int[] rawScores) {
        this.ID = ID;
        this.rawScores = rawScores;
        modifiedScores = rawScores.clone();
        finalScore = 0;
        segment = "X";
        credibility = "NONE";
        status = INITIAL;
    }

    //accessors
    /**
     * 
     * @return status
     */
    public int getStatus () {
        return status;
    }

    /**
     * 
     * @return hackathon
     */
    public Hackathon_Rev getHackathon () {
        return hackathon;
    }

    public String getCredibility() {
        return credibility;
    }

    public String getID() {
        return ID;
    }

    public int[] getRawScores() {
        return rawScores;
    }
    
    //mutators
    /**
     * 
     * @param hackathon assinged to hackathon
     */
    public void setHackathon (Hackathon_Rev hackathon) {
        this.hackathon = hackathon;
    }

    //instance methods
    /**
     * Uses the two-dimensional array {@code INDICATOR_BOUNDARIES_AND_SCORES} to easily modify each score of {@code this}
     * The {@code rawScores} are clamped inside the range (0, 100) but only certain values are assigned. So this is not a scaling process
     * Since the scores are now modified, {@code status} is now set to {@code Project_Rev.MODIFIED}
     */
    public void calcModifiedScores () {
        for (int i = 1; i < modifiedScores.length; i++) {
            if (0 <= modifiedScores[i] && modifiedScores[i] <= INDICATOR_BOUNDARIES_AND_SCORES[i - 1][0]) {modifiedScores[i] = INDICATOR_BOUNDARIES_AND_SCORES[i - 1][2];}
            else if ( modifiedScores[i] <= INDICATOR_BOUNDARIES_AND_SCORES[i - 1][1] ) { modifiedScores[i] = INDICATOR_BOUNDARIES_AND_SCORES[i - 1][3];}
            else if ( modifiedScores[i] > INDICATOR_BOUNDARIES_AND_SCORES[i - 1][1] ) { modifiedScores[i] = INDICATOR_BOUNDARIES_AND_SCORES[i - 1][4];}
        }
        if (rawScores[rawScores.length - 1] <= 2) {
            modifiedScores[modifiedScores.length - 1] = rawScores[rawScores.length - 1];
            credibility = DISQUALIFIED;
        }
        status = MODIFIED;
    }

    /**
     * This method calculates {@code finalScore} of {@code this} according to the indicator weights of {@code hackathon}
     * The formula for this process is {@code finalScore} = (sum of each score * its weight) / (sum of all weigths)
     * 
     * {@code this} needs to have {@code hackathon} initialized for this method to to work.
     */
    public void calcWeightAvg () {
        if (getHackathon() == null) {
            System.out.println("Error! There is no Hackathon_Rev associated with this project.");
        }
        else {
            double scoreSum = 0, weightSum = 0;
            for (int i = 0; i < modifiedScores.length; i++) {
                double weight = Hackathon_Rev.getIndicatorWeights()[i];
                weightSum += weight;
                scoreSum += modifiedScores[i] * weight;
            }
            finalScore = scoreSum / weightSum;
            status = FINAL;
        }
    }

    /**
     * 
     */
    public void calcSegment () {
        if (finalScore == 0) {
            System.out.println("Error! Final score of this project is not calculated.");
        }
        else if (finalScore == 100) {
            segment = "A+";
        }
        else if (finalScore >= 90) {
            segment = "A";
        }
        else if (finalScore >= 75) {
            segment = "B";
        }
        else if (finalScore >= 60) {
            segment = "C";
        }
        else if (finalScore >= 40) {
            segment = "D";
        }
        else {
            segment = "F"; // initiative
        }
    }

    /**
     * 
     */
    public void calcCredibility () {
        int lastIndicator = modifiedScores[modifiedScores.length - 1];
        if (finalScore == 0) {
            System.out.println("Error! Final score of this project is not calculated.");
        }
        else if (credibility.equals(DISQUALIFIED)) {
            //bypass if branches
        }
        else if (segment.equals("A+") && lastIndicator > 0) {
            acceptProject();
        }
        else if (segment.equals("A") && lastIndicator > 0) {
            acceptProject();
        }
        else if (segment.equals("B") &&  lastIndicator > 20) {
            acceptProject();
        }
        else if (segment.equals("C") && lastIndicator == 100) {
            acceptProject();
        }
        else {
            credibility = REJECTED;
        }
    }

    private void acceptProject() {
        credibility = ACCEPTED;
        hackathon.incrementAcceptedProjects();
    }

    /**
     * 
     */
    @Override
    public String toString () {
        String result = String.format("%-15s%-15d%-15d%-15d%-15d%-15d", ID, modifiedScores[0], modifiedScores[1], modifiedScores[2], modifiedScores[3], modifiedScores[4]);
        if (status == FINAL) {
            result += String.format("%-15.2f%-15s%-15s", finalScore, segment, credibility);
        }
        return result;
    } 

    //static methods
}