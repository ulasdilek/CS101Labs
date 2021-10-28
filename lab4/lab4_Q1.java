/**
 * This is a program that finds nth fibonacci number
 * @author Ulas Dilek
 */

package lab4;

import java.util.Scanner;

public class lab4_Q1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Please input n: ");
        int n = in.nextInt();
        int fibA = 1;
        int fibB = 1;
        int fibN = (n > 0 ? 1 : 0); // we check manually for fib0 fib1 and fib2
        int counter = 2;
        while (counter < n) {
            //fibA will always come before fibB in the series and fibN will be their sum
            fibN = fibA + fibB;
            fibA = fibB;
            fibB = fibN;
            counter++;
        }
        System.out.println("Fib(" + n + ") is " + fibN);
    }
    
}
