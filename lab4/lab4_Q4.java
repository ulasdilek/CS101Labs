/**
 * This is a program that does "text art"
 * @author Ulas Dilek
 */

package lab4;

import java.util.Scanner;

public class lab4_Q4 {
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        int numberOfLines = in.nextInt();
        int counter = 0;
        String art = "*";
        while (counter < numberOfLines) {
            System.out.println(art);
            art = art.concat("*");// We concatinate the stars to reach the desired length
            counter++;
        }
        
    }
}
