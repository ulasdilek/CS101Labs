package lab10;

public class Hackathon_Rev {

    // static variables
    private static final int MAX_NO_OF_PROJECTS = 6;
    private static final int INDICATOR_COUNT = 5;
    private static final double[] INDICATOR_WEIGHTS = {0.1, 0.2, 0.3, 0.35, 0.05};

    // instance variables
    private int projectCount, numberOfAcceptedProjects;
    private Project_Rev[] projects;

    // constructors
    public Hackathon_Rev () {
        projectCount = 0;
        numberOfAcceptedProjects = 0;
        projects = new Project_Rev[MAX_NO_OF_PROJECTS];
    }

    // accessors
    public static int getMaxNoOfProjects() {
        return MAX_NO_OF_PROJECTS;
    }

    public static int getIndicatorCount() {
        return INDICATOR_COUNT;
    }

    public static double[] getIndicatorWeights() {
        return INDICATOR_WEIGHTS;
    }

    public int getProjectCount() {
        return projectCount;
    }

    // mutators


    // instance methods
    public void addProject (Project_Rev project) {
        project.setHackathon(this);
        projects[projectCount] = project;
        projectCount++;
    }

    public void incrementAcceptedProjects() {
        numberOfAcceptedProjects++;
    }

    // id, supported goals, durations
    public String[][] makeDecision () {
        String[][] investedProjects = new String[numberOfAcceptedProjects + 1][3];
        int index = 0;
        for (int i = 0; i < projectCount; i++) {
            if (projects[i].getCredibility().equals(Project_Rev.ACCEPTED)) {
                investedProjects[index][0] = projects[i].getID();
                investedProjects[index][1] = String.valueOf(projects[i].getRawScores()[4]);
                investedProjects[index][2] = String.valueOf(projects[i].getRawScores()[2]);
                index++;
            }
        }
        investedProjects[numberOfAcceptedProjects][0] = "MAX:";
        int maxGoals = 0;
        for (int i = 0; i < numberOfAcceptedProjects; i++) {
            maxGoals = Math.max(maxGoals, Integer.parseInt(investedProjects[i][1]));
        }
        investedProjects[numberOfAcceptedProjects][1] = String.valueOf(maxGoals);
        int maxDuration = 0;
        for (int i = 0; i < numberOfAcceptedProjects; i++) {
            maxDuration = Math.max(maxDuration, Integer.parseInt(investedProjects[i][2]));
        }
        investedProjects[numberOfAcceptedProjects][2] = String.valueOf(maxDuration);
        return investedProjects;
    }

    public void printInvestmentTable () {
        String[][] investedProjects = makeDecision();
        System.out.println("Investment Decision");
        System.out.println("*".repeat(45));
        System.out.printf("%-15s%-15s%-15s%n", "Project ID", "Goals", "Duration");
        for (int i = 0; i < investedProjects.length - 1; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.printf("%-15s", investedProjects[i][j]);
            }
            System.out.println();
        }
        System.out.println("-".repeat(45));
        for (int i = 0; i < 3; i++) {
            System.out.printf("%-15s", investedProjects[numberOfAcceptedProjects][i]);
        }
        System.out.println("\n" + "*".repeat(45));
    }

    @Override
    public String toString () {
        int count = 1 + INDICATOR_COUNT;
        String status = "Initial";
        String firstLine = String.format("%-15s%-15s%-15s%-15s%-15s%-15s","Project ID", "Indicator 1", "Indicator 2", "Indicator 3", "Indicator 4", "Indicator 5");
        for (Project_Rev project : projects) {
            if (project.getStatus() == Project_Rev.MODIFIED) {
                status = "Modified";
            }
            else if (project.getStatus() == Project_Rev.FINAL) {
                count = INDICATOR_COUNT + 4;
                status = "Final";
            }
        }
        if (status.equals("Final")) {
            firstLine += String.format("%-15s%-15s%-15s", "Weighted Mean", "Segment", "Credibility");
        }
        String result = status + " Table\n" + "*".repeat(count * 15) + "\n";
        result += firstLine + "\n";
        for (Project_Rev project : projects) {
            result += project + "\n";
        }
        result += "*".repeat(count * 15) + "\n";
        return result;
    }

    // static methods
    public static void main(String[] args) {
        Hackathon_Rev hackathon = new Hackathon_Rev();
        hackathon.addProject(new Project_Rev("A1", new int[] {100, 240, 15, 26, 12}));
        hackathon.addProject(new Project_Rev("A2", new int[] {20, 407, 13, 11, 1}));
        hackathon.addProject(new Project_Rev("A3", new int[] {100, 281, 13, 39, 9}));
        hackathon.addProject(new Project_Rev("A4", new int[] {80, 1264, 4, 38, 7}));
        hackathon.addProject(new Project_Rev("A5", new int[] {20, 1020, 12, 11, 1}));
        hackathon.addProject(new Project_Rev("A6", new int[] {100, 1162, 17, 34, 6}));
        System.out.println(hackathon);
        for (Project_Rev project : hackathon.projects) {
            project.calcModifiedScores();
        }
        System.out.println(hackathon);
        for (Project_Rev project : hackathon.projects) {
            project.calcWeightAvg();
            project.calcSegment();
            project.calcCredibility();
        }
        System.out.println(hackathon);
        hackathon.printInvestmentTable();
    }
    
}
