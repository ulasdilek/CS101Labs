/**
 * This program uses four predetermined values and uses them to find requested values.
 * @author Ulas Dilek
 */

public class Lab01_Q3 {

    public static void main( String[] args){

        //declaration and initialization of the constants
        final int TOTAL_AREA_OF_THE_WORLD = 510072000;
        final double PERCENTAGE_OF_TURKEYS_AREA = 0.1536;

        //declaration and initialization of the dynamic values
        double percentageOfWaterInTheWorld = 70.8;
        double percentageOfWaterInTurkey = 1.3;

        //How much of the area of the world is dry land and how much is water?
        int waterInTheWorld = (int) ( TOTAL_AREA_OF_THE_WORLD * percentageOfWaterInTheWorld / 100);
        int dryLandInTheWorld = TOTAL_AREA_OF_THE_WORLD - waterInTheWorld;
        System.out.println( "Earth has " + dryLandInTheWorld +  " km2 dry land and " + waterInTheWorld + " km2 water.");
        
        //How much of the area in Turkey is dry land and how much is water?
        int waterInTurkey = (int) ( TOTAL_AREA_OF_THE_WORLD * PERCENTAGE_OF_TURKEYS_AREA * percentageOfWaterInTurkey / (100 * 100));
        int dryLandInTurkey = (int) ( TOTAL_AREA_OF_THE_WORLD * PERCENTAGE_OF_TURKEYS_AREA / 100 - waterInTurkey);
        System.out.println( "Turkey has " + dryLandInTurkey + " km2 dry land and " + waterInTurkey + " km2 water.");
        
        //What percent of the whole dry land of earth is in Turkey?
        System.out.println( "Turkey has " + ( 100 * (double) dryLandInTurkey / dryLandInTheWorld) + " percent of the Earth's dry land.");
        
        //What percent of all water is in Turkey?
        System.out.println( "Turkey has " + ( 100 * (double) waterInTurkey / waterInTheWorld) + " percent of the Earth's water.");
        
    }
    
}
