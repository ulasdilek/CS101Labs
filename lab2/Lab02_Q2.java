/**
 * This is a program that calculates a car's maintenance costs
 * according to the user's input
 * @author Ulas Dilek
 */

import java.util.Scanner;

public class Lab02_Q2 {

    public static void main(String[] args) {
        
        //declaration and initialization of the constants
        final double COST_PER_KM = 0.1;
        final int OIL_CHANGE_PERCENT = 30;
        final int BATTERY_PERCENT = 15;
        final int BRAKES_PERCENT = 25;
        final int TIRES_PERCENT = 13;
        final int OTHER_PERCENT = 17;

        //create the scanner and take the input
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the odometer reading of the car in kilometers:\t");
        int distanceTravelled = in.nextInt();
        double totalCost = distanceTravelled * COST_PER_KM;

        //calculate the values
        double oilCost = totalCost * OIL_CHANGE_PERCENT/100.0;
        double batteryCost = totalCost * BATTERY_PERCENT/100.0;
        double brakesCost = totalCost * BRAKES_PERCENT/100.0;
        double tiresCost = totalCost * TIRES_PERCENT/100.0;
        double otherCost = totalCost * OTHER_PERCENT/100.;

        //total width is 49 characters
        //printing is done with formatting
        System.out.println();
        System.out.println("*************************************************");
        System.out.printf("%-7s%s%7s%n", "*****", "Maintenance Cost Distribution Table", "*****");
        System.out.println("*************************************************");
        System.out.printf("%-22s%s%-4d%16.2f%6s%n", "*Oil Change", "%", OIL_CHANGE_PERCENT, oilCost, "*");
        System.out.printf("%-22s%s%-4d%16.2f%6s%n", "*Battery", "%", BATTERY_PERCENT, batteryCost, "*");
        System.out.printf("%-22s%s%-4d%16.2f%6s%n", "*Brakes", "%", BRAKES_PERCENT, brakesCost, "*");
        System.out.printf("%-22s%s%-4d%16.2f%6s%n", "*Tires", "%", TIRES_PERCENT, tiresCost, "*");
        System.out.printf("%-22s%s%-4d%16.2f%6s%n", "*Other", "%", OTHER_PERCENT, otherCost, "*");
        System.out.printf("%-22s%5s%16.2f%6s%n", "*", "TOTAL", totalCost, "*");
        System.out.println("*************************************************");
        
        
    }
    
}
