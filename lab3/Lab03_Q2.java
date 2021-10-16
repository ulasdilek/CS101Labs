package lab3;

import java.util.Scanner;

/**
 * This is a program that makes computations on the user's input to determine if it is a leap year
 * according to the Gregorian calendar
 * @author Ulas Dilek
 */

public class Lab03_Q2 {
    
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        System.out.print("Oh no, I overslept! What year is it? \t");
        int year = in.nextInt();
        in.close();
        boolean isLeapYear;

        //divisible by 4?
        if ( year % 4 == 0){
            //divisible by 100?
            if ( year % 100 == 0){
                //divisible by 400?
                if ( year % 400 == 0){
                    isLeapYear = true;
                }
                else {
                    isLeapYear = false;
                }
            }
            else {
                isLeapYear = true;
            }
        }
        else{
            isLeapYear = false;
        }
        String leapYear = "not a leap year.";
        if (isLeapYear){
            leapYear = "a leap year.";
        }

        //minus 1 is for the inclusion of x00 years
        int century = (year - 1) / 100 + 1;
        String ordinal;
        switch (century % 10){
            case (1): ordinal = "st"; break;
            case (2): ordinal = "nd"; break;
            case (3): ordinal = "rd"; break;
            default: ordinal = "th";
        }

        System.out.printf("%s%d%s%s%s%s%s", "So the year is ", year, " and we are in the ", century, ordinal, " century? That's ", leapYear);
        
    }
    
}
