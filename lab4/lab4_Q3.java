/**
 * This is a program that finds the min, max and average of n numbers
 * @author Ulas Dilek
 */

package lab4;

import java.util.Scanner;

public class lab4_Q3 {
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        System.out.print("Please input n: ");
        int n = in.nextInt();
        System.out.println("Now input " + n + " positive integers:");
        int counter = 0;
        int min = Integer.MAX_VALUE; // so that we an input will definitely be smaller than this
        int max = 0; // so that we an input will definitely be greater than this
        double sum = 0;
        int input;
        while (counter < n) {
            input = in.nextInt();
            if (input < min) { // check if the input is the new min
                min = input;
            }
            if (input > max) { // check if the input is the new max
                max = input;
            }
            sum += input;
            counter++;
        }
        System.out.println("Min: " + min);
        System.out.println("Max: " + max);
        System.out.printf("%s%.1f%n", "Average: ", sum / n);
        
    }
}
