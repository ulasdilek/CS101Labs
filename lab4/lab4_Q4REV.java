/**
 * This is a program that does more sophisticated "text art"
 * @author Ulas Dilek
 */

package lab4;

import java.util.Scanner;

public class lab4_Q4REV {
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        int numberOfLines = in.nextInt();
        int counter = 0;
        String art = "*";
        while (counter < numberOfLines) {
            System.out.printf("%" + (2 * numberOfLines - 1) + "s%n", art);// the width of the line must be 2 * input - 1. So we format the string to fit that
            art = art + "**";// We concatinate the stars to reach the desired length
            counter++;
        }
        
    }
}
