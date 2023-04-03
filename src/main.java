import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class main {
    public static void main(String[] arg) {


        Scanner scan = new Scanner(System.in);
        System.out.println("please type the first number:");
        String firstNumb;
        while (!checkNumber(firstNumb = scan.nextLine()));
        System.out.println("please type the second number:");
        String secondNumb;
        while (!checkNumber(secondNumb = scan.nextLine()));
        BigInt one = new BigInt(firstNumb);
        BigInt two = new BigInt(secondNumb);

        one.multiply(two).toString();
        one.divide(two).toString();
        one.plus(two).toString();
        one.minus(two).toString();


    }
    //This method cheking if the argument are correct
    public static boolean checkNumber(String s) {
        try {
            int number = Integer.parseInt(s);
        } catch (IllegalArgumentException e) {
            System.out.println("the string must to use just digits and + or - at the beginning try again");
            return false;
        }
        return true;
    }
    public static void choice()
    {


    }
}

