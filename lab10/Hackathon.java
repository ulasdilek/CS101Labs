package lab10;

public class Hackathon {

    // static variables
    private static final int MAX_NO_OF_PROJECTS = 6;
    private static final int INDICATOR_COUNT = 4;
    private static final double[] INDICATOR_WEIGHTS = {0.1, 0.25, 0.3, 0.35};

    // instance variables
    private int projectCount;
    private Project[] projects;

    // constructors
    public Hackathon () {
        projectCount = 0;
        projects = new Project[MAX_NO_OF_PROJECTS];
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
    public void addProject (Project project) {
        project.setHackathon(this);
        projects[projectCount] = project;
        projectCount++;
    }

    @Override
    public String toString () {
        int count = 5;
        String status = "Initial";
        String firstLine = String.format("%-15s%-15s%-15s%-15s%-15s","Project ID", "Indicator 1", "Indicator 2", "Indicator 3", "Indicator 4");
        for (Project project : projects) {
            if (project.getStatus() == Project.MODIFIED) {
                status = "Modified";
            }
            else if (project.getStatus() == Project.FINAL) {
                count = 8;
                status = "Final";
            }
        }
        if (status.equals("Final")) {
            firstLine += String.format("%-15s%-15s%-15s", "Weighted Mean", "Segment", "Credibility");
        }
        String result = status + " Table\n" + "*".repeat(count * 15) + "\n";
        result += firstLine + "\n";
        for (Project project : projects) {
            result += project + "\n";
        }
        result += "*".repeat(count * 15) + "\n";
        return result;
    }

    // static methods
    public static void main(String[] args) {
        Hackathon hackathon = new Hackathon();
        hackathon.addProject(new Project("A1", new int[] {100, 240, 15, 26}));
        hackathon.addProject(new Project("A2", new int[] {20, 407, 13, 11}));
        hackathon.addProject(new Project("A3", new int[] {100, 281, 13, 39}));
        hackathon.addProject(new Project("A4", new int[] {80, 1264, 4, 38}));
        hackathon.addProject(new Project("A5", new int[] {20, 1020, 12, 11}));
        hackathon.addProject(new Project("A6", new int[] {100, 1162, 17, 34}));
        System.out.println(hackathon);
        for (Project project : hackathon.projects) {
            project.calcModifiedScores();
        }
        System.out.println(hackathon);
        for (Project project : hackathon.projects) {
            project.calcWeightAvg();
            project.calcSegment();
            project.calcCredibility();
        }
        System.out.println(hackathon);
    }

    /**
     * @deprecated I learnt how to do it properly so there is no need for the method
     * but this parameter declaration is also something new for me so I'm keeping it :)
     */
    private static int[] createArray (int ... values) {
        return values;
    }
    
}
