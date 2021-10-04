/**
 * This program calculates some predetermined mathematical expressions and prints their results.
 * @author Ulas Dilek
 */

public class Lab01_Q2 {
    
    public static void main( String[] args){

        //The expressions are executed here keeping in mind the operation order and value types
        double expression1 = ( 32.2 - 1.7 / 2.2) / (( 1.5 - 7.3) * (4.3 + 2.4));
        double expression2 = (( 73.5 * 16.4 - Math.pow( 3, 6)) / ( 34 + Math.pow( 2, 5)) );
        double expression3 = Math.pow( 1.2 + 0.8, - 1.0 / 3.0);

        //The results are printed out
        System.out.println( "Result of the expression 1 is: " + expression1);
        System.out.println( "Result of the expression 2 is: " + expression2);
        System.out.println( "Result of the expression 3 is: " + expression3);

    }
    
}