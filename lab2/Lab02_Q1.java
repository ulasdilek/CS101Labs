/**
 * This is a program that takes two inputs from the user and calculates area, circumference
 * and diagonal of a rectangle with the given side lenghts
 * @author Ulas Dilek
 */

package lab2;

import java.util.Scanner;

public class Lab02_Q1
{

    public static void main(String[] args)
    {
        
        //Define and initialize the scanner
        Scanner in = new Scanner(System.in);
        //Prompt the user for inputs
        System.out.println("We need you to provide 2 double values for the sides of a theoretical rectangle.");
        System.out.print("Please write the first length:\t");
        double fSide = getDoubleValue(in);
        //Skip over whatever is unnecessary
        in.nextLine();
        System.out.print("Please write the second length:\t");
        double sSide = getDoubleValue(in);
        //Close the scanner because there is no use for it from now on
        in.close();
        
        //Calculate the requested values
        double area = fSide * sSide;
        double circumference = fSide + sSide;
        double diagonal = Math.sqrt(fSide * fSide + sSide * sSide);

        //Print all of them in with proper formatting
        System.out.println();
        System.out.printf("%-40s%s%15.3f%n", "The smaller side of the rectangle is", ":", Math.min( fSide, sSide));
        System.out.printf("%-40s%s%15.3f%n", "The larger side of the rectangle is", ":", Math.max( fSide, sSide));
        System.out.printf("%-40s%s%15.3f%n", "The area of the rectangle is", ":", area);
        System.out.printf("%-40s%s%15.3f%n", "The circumference of the rectangle is", ":", circumference);
        System.out.printf("%-40s%s%15.3f%n", "The diagonal of the rectangle is", ":", diagonal);
    }

    //Defined a seperate method that checks the input and acts accordingly to make the main method a bit neater
    public static double getDoubleValue(Scanner sc)
    {
        //A boolean to control the while loop
        boolean gotDouble = false;
        while (!gotDouble)
        {
            //A check to avoid type mismatch exceptions
            if (sc.hasNextDouble())
            {
                gotDouble = true;
            }
            else
            {
                sc.nextLine();
                System.err.println("It did not work.");
                System.out.print("Please write the length again:\t");
            }
        }
        //The code can escape the loop only if there is a double input
        return sc.nextDouble();
        
    }
    
}
