/**
 * This is a program that finds the min, max and average of n numbers
 * @author Ulas Dilek
 */

package lab4;

import java.util.Scanner;

public class lab4_Q3REV {
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        int counter = 0;
        int min = Integer.MAX_VALUE;
        int max = 0;
        double sum = 0;
        int input = 1;
        while (input > 0) {
            System.out.print("Please input a positive integer: ");
            input = in.nextInt();
            if (input > 0){//we won't make any operation if the input is not positive and exit the loop without incrementing counter
                if (input < min) {
                    min = input;
                }
                if (input > max) {
                    max = input;
                }
                sum += input;
                counter++;
            }
        }
        System.out.println("Min: " + min);
        System.out.println("Max: " + max);
        System.out.printf("%s%.1f%n", "Average: ", sum / counter);
        
    }
}
