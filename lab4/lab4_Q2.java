/**
 * This is a program thath checks if an input is palindrome or not
 * @author Ulas Dilek
 */

package lab4;

import java.util.Scanner;

public class lab4_Q2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Please input the string: ");
        String input = in.nextLine();
        int length = input.length();
        int counter = 0;
        boolean palindrome = false;
        while (input.charAt(counter) == input.charAt(length - counter - 1) && counter < (length % 2) + length / 2) {
            //it will run until we reach the middle of the string, then we know the string is a palindrome
            counter++;
        }
        if (counter == (length % 2) + length / 2){ //check if the counter reached the middle of the string
            palindrome = true;
        }
        String statement = (palindrome ? "a palindrome." : "not a palindrome.");
        System.out.println("It is " + statement);
    }
    
}
