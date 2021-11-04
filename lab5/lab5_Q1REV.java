package lab5;

/**
 * This is a program that builds a city out of "*"
 * This one even has DOUBLE DELUXE TM!!!!
 * @author Ulas Dilek
 */

import java.util.Scanner;

public class lab5_Q1REV{
    public static void main(String[] args) {

        //final byte ASCII_OFFSET_OF_NUMBERS = 48;
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter city plan string:\t");
        String input = in.next();
        in.close();
        System.out.println("City blueprint:");
        byte maxHeight = 0;
        int length = input.length();
        byte currentHeight = 0;
        //find the maximum height for the buildings to determine how many spaces there will be
        for (int i = 0; i < length; i++) {

            
            if (input.charAt(i) != 'D') {
                currentHeight = (byte) Character.digit(input.charAt(i), 10);
            }
            if (currentHeight > maxHeight) {
                maxHeight = currentHeight;
            }
            
        }

        char print;
        for (byte line = maxHeight; line > 0; line--) {// iterates through each line
            for (int column = 0; column < length; column++) {// iterates through each character of the input
                currentHeight = (input.charAt(column) == 'D' ? (byte) Character.digit(input.charAt(column + 1), 10) : (byte) Character.digit(input.charAt(column), 10));
                print = '*';
                if (line > currentHeight){
                    print = ' ';
                }
                System.out.print(print);
                
            }
            System.out.println();
        }

    }
}