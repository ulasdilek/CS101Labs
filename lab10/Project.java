package lab10;

/**
 * This class is implemented in accordance to the lab assignment but I have used some initiative for a few things.
 * {@code Project} class works in harmony with {@code Hackathon} class and performs some utility actions to
 * modify all scores, calculate the weighted average and store credibility of the {@code Project} instance.
 * @author Ulas Dilek
 */
public class Project {
    //static variables
    public static final int INITIAL = 0, MODIFIED = 1, FINAL = 2;
    private static final int[][] INDICATOR_BOUNDARIES_AND_SCORES = {{500, 1000, 20, 80, 100}, {6, 12, 100, 80, 20}, {10, 15, 20, 80, 100}};

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
     * A {@code Hackathon} instance is required for some actions such as calculating the {@code finalScore} of the {@code Project} instance.
     */
    private Hackathon hackathon;
    /**
     * {@code status} is always equal to one of {@code Project.INITIAL}, {@code Project.MODIFIED} and {@code Project.FINAL}.
     * 
     * It is used to determine the state of {@code modifiedScores}. These are
     *      {@code INITIAL}: A copy of {@code rawScores}
     *      {@code MODIFIED}: Each score is clamped to the range (0, 100) according to its rules predetermined in the assignment.
     *      {@code FINAL}: {@code finalScore}, {@code segment} and {@code credibility} values are finalised according to {@code modifiedScores}
     */
    private int status;
    
    //constructors
    /**
     * The only constructor of {@code Project}. Initializes all required instance variables appropriately.
     * @param ID The ID of the {@code Project} instance. It is expected to be in the format {@code "X0"} where X represents a {@code String} of length 1 and 0 represents a single digit number.
     * @param rawScores An {@code int[]} of length {@code Hackathon.INDICATOR_COUNT} that contains the raw scores of the {@code Project} instance that are going to be clamped to range (0, 100) according to a set of rules predetermined in the lab assignment.
     */
    public Project(String ID, int[] rawScores) {
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
     * Accessor for status variable
     * @return status
     */
    public int getStatus () {
        return status;
    }
    /**
     * Accessor for hackathon variable
     * @return hackathon
     */
    public Hackathon getHackathon () {
        return hackathon;
    }
    
    //mutators
    /**
     * Mutator for hackathon variable
     * @param hackathon assinged to hackathon
     */
    public void setHackathon (Hackathon hackathon) {
        this.hackathon = hackathon;
    }

    //instance methods
    /**
     * Uses the two-dimensional array {@code INDICATOR_BOUNDARIES_AND_SCORES} to easily modify each score of {@code this}
     * The {@code rawScores} are clamped inside the range (0, 100) but only certain values are assigned. So this is not a scaling process
     * Since the scores are now modified, {@code status} is now set to {@code Project.MODIFIED}
     */
    public void calcModifiedScores () {
        for (int i = 1; i < modifiedScores.length; i++) {
            if (0 <= modifiedScores[i] && modifiedScores[i] <= INDICATOR_BOUNDARIES_AND_SCORES[i - 1][0]) {modifiedScores[i] = INDICATOR_BOUNDARIES_AND_SCORES[i - 1][2];}
            else if ( modifiedScores[i] <= INDICATOR_BOUNDARIES_AND_SCORES[i - 1][1] ) { modifiedScores[i] = INDICATOR_BOUNDARIES_AND_SCORES[i - 1][3];}
            else if ( modifiedScores[i] > INDICATOR_BOUNDARIES_AND_SCORES[i - 1][1] ) { modifiedScores[i] = INDICATOR_BOUNDARIES_AND_SCORES[i - 1][4];}
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
            System.out.println("Error! There is no Hackathon associated with this project.");
        }
        else {
            double scoreSum = 0, weightSum = 0;
            for (int i = 0; i < modifiedScores.length; i++) {
                double weight = Hackathon.getIndicatorWeights()[i];
                weightSum += weight;
                scoreSum += modifiedScores[i] * weight;
            }
            finalScore = scoreSum / weightSum;
            status = FINAL;
        }
    }

    /**
     * Segments are dependent on the {@code finalScore} of {@code this}
     * Ranges for every segment are defined in the lab assignment
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
     * Every {@code segment} corresponds to a {@code credibility} value that will help the
     * investor to decide
     */
    public void calcCredibility () {
        if (finalScore == 0) {
            System.out.println("Error! Final score of this project is not calculated.");
        }
        else if (segment.equals("A+")) {
            credibility = "Perfect";
        }
        else if (segment.equals("A")) {
            credibility = "Very Good";
        }
        else if (segment.equals("B")) {
            credibility = "Good";
        }
        else if (segment.equals("C")) {
            credibility = "Considerable";
        }
        else if (segment.equals("D")) {
            credibility = "Not Appropriate";
        }
        else if (segment.equals("F")) {
            credibility = "Failed"; // initiative
        }
    }

    /**
     * Returns all important properties of {@code this} in a format that is fit to print
     * Do not use for other utility
     */
    @Override
    public String toString () {
        String result = String.format("%-15s%-15d%-15d%-15d%-15d", ID, modifiedScores[0], modifiedScores[1], modifiedScores[2], modifiedScores[3]);
        if (status == FINAL) {
            result += String.format("%-15.1f%-15s%-15s", finalScore, segment, credibility);
        }
        return result;
    } 

    //static methods
}