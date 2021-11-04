package lab6;

import java.util.Scanner;

public class Lab06_Q1 {

    public static void main (String[] args) {
        
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the number to be examined:\t");
        int input = in.nextInt();
        System.out.println("Number of digits: " + countDigits(input));
        System.out.println("Sum of digits: " + sumOfDigits(input));
        System.out.println(input + isOrIsNot(isFibonacci(input)) + " a Fibonacci number.");
        System.out.println(input + isOrIsNot(isHarshad(input)) + " a Harshad number.");
        System.out.println(input + isOrIsNot(isNarcissistic(input)) + " Narcissistic.");
        System.out.println(input + isOrIsNot(isPrime(input)) + " a Prime number.");
        
    }

    /**
     * determines the correct form of output according to the boolean value
     * @param bool 
     * @return if true return " is", if not return " is not"
     */
    static String isOrIsNot(boolean bool) {
        return (bool ? " is" : " is not");
    }

    /**
     * checks if the given integer is a perfect square
     * @param num the integer to be taken into account
     * @return whether the number is perfect square or not 
     */
    static boolean isPerfectSquare ( int num) {
        return ( Math.sqrt((double) num) == (int) Math.sqrt((double) num) ? true : false);
    }

    /**
     * checks if the given integer is a fibonacci number
     * @param num the integer to be taken into account
     * @return whether the number is in Fibonacci sequence or not
     */
    static boolean isFibonacci (int num) {
        return ( isPerfectSquare(5 * num * num + 4 ) || isPerfectSquare(5 * num * num - 4) ? true : false);
    }
    
    /**
     * returns the sum of all digits of the integer given
     * @param num the integer whose digits are added
     * @return the sum of digits of the integer
     */
    static int sumOfDigits (int num) {
        int sum = 0;
        int digitCount = countDigits(num);
        for (int i = 0; i < digitCount; i++) {
            sum = sum + (num % 10);
            num = num / 10;
        }
        return sum;
    }

    /**
     * checks whether the parameter is a Harshad number or not
     * @param num the integer to be checked
     * @return whether the integer is a Harshad number or not
     */
    static boolean isHarshad (int num) {
        if (num == 0 || num % sumOfDigits(num) != 0) { return false; }
        return true;
    }
    
    /**
     * studies whether the integer is prime or not by first checking the special cases
     * then checking if it is divisible by odd numbers up to the square root of the parameter
     * @param num the integer to be studied
     * @return whether the integer is prime or not
     */
    static boolean isPrime ( int num) {
        if (num < 2) {
            return false;
        }
        else if (num == 2) {
            return true;
        }
        else {
            if (num % 2 == 0) {return false;}
            for (int divisor = 3; divisor <= Math.sqrt((double) num); divisor+= 2){
                if (num % divisor == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * executes a power operation with two integers
     * @param base the base integer of the operation
     * @param power the power integer of the operation
     * @return returns base^power
     */
    static int power( int base, int power) {
        int result = 1;
        while ( power > 0) {
            result = result * base;
            power--;
        }
        return result;
    }
    
    /**
     * finds the number of digits of an integer
     * @param num the integer to be studied
     * @return the number of digits
     */
    static int countDigits (int num) {
        int digits = 0;
        while (num != 0) {
            num = num / 10;
            digits++;
        }
        return digits;
    }

    /**
     * returns whether an integer is narcissistic, which is An n-digit number
     * that is the sum of the n-th power of its digits
     * @param num the integer to be studied
     * @return whether the integer is narcissistic
     */
    static boolean isNarcissistic (int num) {
        int digitCount = countDigits(num);
        int currentNum = num;
        int sum = 0;
        for (int i = 0; i < digitCount; i++) {
            sum = sum + power(currentNum % 10, digitCount);
            currentNum = currentNum / 10;
        }
        return sum == num;
    }
    
}
