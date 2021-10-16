/**
 * This is a program that takes inputs from the user to calculate their BMI.
 * @author Ulas Dilek
 */

package lab3;

import java.util.Scanner;

public class Lab03_Q1{

    public static void main(String[] args){

        Scanner in = new Scanner(System.in);
        System.out.print("Please enter your weight in kilograms:\t");
        double weight = Math.abs(in.nextDouble()); // Absolute value to prevent negative numbers
        System.out.print("Please enter your height in meters:\t");
        double height = Math.abs(in.nextDouble());
        double BMI = weight / (height * height);
        String status;

        //checking the conditions
        if (BMI == 0){
            status = "NON_EXISTENT";
        }
        else if (BMI >= 30) {
            status = "obese";
        }
        else if (BMI >= 25) {
            status = "overweight";
        }
        else if (BMI >= 18.5) {
            status = "healthy";
        }
        else {
            status = "underweight";
        }

        System.out.printf("%n%s%.2f%s%s%s%n", "Your BMI is ", BMI, " and you are ", status, ".");
        
    }
    
}