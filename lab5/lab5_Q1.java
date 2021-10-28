package lab5;

/**
 * This is a program that builds a city out of "*"
 * @author Ulas Dilek
 */

import java.util.Scanner;

public class lab5_Q1{
    public static void main(String[] args) {

        final byte ASCII_OFFSET_OF_NUMBERS = 48;
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter city plan string:\t");
        String input = in.next();
        in.close();
        System.out.println("City blueprint:");
        byte maxHeight = 0;
        int length = input.length();
        //find the maximum height for the buildings to determine how many spaces there will be
        for (int i = 0; i < length; i++) {

            byte currentHeight = (byte) (input.charAt(i) - ASCII_OFFSET_OF_NUMBERS);
            if (currentHeight > maxHeight) {
                maxHeight = currentHeight;
            }
            
        }

        char print;
        for (byte line = maxHeight; line > 0; line--) {// iterates through each line
            for (int digit = 0; digit < length; digit++) {// iterates through each character of the input

                //print = ( line > (byte) (input.charAt(digit) - ASCII_OFFSET_OF_NUMBERS) ? ' ' : '*' );
                print = '*';
                if (line > (byte) (input.charAt(digit) - ASCII_OFFSET_OF_NUMBERS)){
                    print = ' ';
                }
                System.out.print(print);
                
            }
            System.out.println();
        }

    }
}