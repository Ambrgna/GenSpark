package Project2;

import java.util.Scanner;

public class Main {
    static Scanner s = new Scanner(System.in);
    static String name = "";
    static int rand;
    static int n = 0;
    public static void main(String[] args) {
        rand =(int)(Math.random()*20)+1;

        System.out.println(rand);

        System.out.println("Hello! What is your name?");

        name = getName();

        n = guessNumber();

        System.out.println(compareNumber());
    }
    static int guessNumber(){
        int g = 0;
        while (true){
            try {
                g = s.nextInt();
            } catch (Exception e) {
                System.out.println("Please enter a number between 1 and 20.");
                s.next();
            }
            if(g>=1&&g<=20)
                break;
            else
                System.out.println("Number between 1 and 20.");
        }
        return g;
    }
    static String getName(){
        String name = s.nextLine().trim();

        while(name.equals("")) {
            System.out.println("Please enter your name.");
            name = s.nextLine().trim();
        }
        System.out.println("Well, "+name+"""
                 I am thinking of a number between 1 and 20.
                Take a guess.
                """
        );
        return name;
    }
    static String compareNumber(){
        int guess = 1;
        String g;
        String c;
        String e;

        while (rand!=n&&guess<=6) {
            c = (n>rand) ? "high" : "low";

            System.out.println("Your guess is too "+c+".");
            System.out.println("Take a guess.");

            n = guessNumber();

            guess++;
        }
        g = (guess != 1) ? " guesses!" : " guess!";

        e = (n==rand) ?
                "Good job, "+ name +"! You guessed my number in "+ guess + g
                : "Too bad, "+ name +"! You ran out of guesses, my number was "+rand;

        return e;
    }
}