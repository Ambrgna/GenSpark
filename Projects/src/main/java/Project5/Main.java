package Project5;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static boolean gameover = false;
    static boolean run = true;
    static Scanner s = new Scanner(System.in);
    static ArrayList<Character> all = new ArrayList<Character>();
    public static void main(String[] args) {

        do {
            runGame();
            }while (continueGame());
    }

    private static boolean continueGame() {
        String input = null;
        while (input == null) {
            try {
                System.out.println("Do you want to play again? (yes or no)");
                input = s.next().toLowerCase();
                if (input.equals("no")||input.equals("n"))
                    return false;
                else if (input.equals("yes")||input.equals("y")) {
                    gameover = false;
                    return true;
                } else {
                    input = null;
                    throw new IllegalArgumentException();
                }

            } catch (Exception e) {
                System.out.println("Please only enter yes or no!");
            }
        }
        return false;
    }

    private static void runGame() {
        char[] dead = {'O', '|', '/', '\\', '/', '\\'};
        char[] man = new char[6];
        char[] secretword;
        char guessletter;
        String word;
        ArrayList<Character> wrong = new ArrayList<Character>();
        all = new ArrayList<Character>();

        word = getWord();

        secretword = new char[word.length()];

        man=fillArray(man,' ');
        secretword=fillArray(secretword,'_');

        System.out.println(word);

        while(true&&!gameover) {
            guessletter = guessLetter();
            all.add(guessletter);
            if (word.contains(String.valueOf(guessletter))){
                secretword = updateArray(guessletter, secretword, word);
            } else {
                wrong.add(guessletter);
                man = updateMan(man);
            }
            printMan(man, wrong, secretword);
            if (man[5] != ' ') {
                gameover = true;
                System.out.println("GameOver");
            }
            if (winCon(secretword)) {
                gameover = true;
                System.out.println("Yes! The secret word is \""+word+"\"! You have won!");
            }
        }

    }

    private static boolean winCon(char[] secretword) {
        for (char c:secretword) {
            if (c=='_')
                return false;
        }
        return true;
    }

    private static char[] updateMan(char[] man) {
        char[] dead = {'O', '|', '/', '\\', '/', '\\'};
        for (int i = 0; i < 6; i++) {
            if(dead[i] != man[i]){
                man[i] = dead[i];
                break;
            }
        }
        return man;
    }

    static char[] updateArray(char g, char[] a, String w){
        while (w.contains(String.valueOf(g))){
            int index = w.indexOf(g);
            a[index] = w.charAt(index);
            w = w.substring(0, index) + w.substring(index + 1);
        }
        return a;
    }

    static char guessLetter() {
        String input = null;
        String  m="";
        char g = 0;

        System.out.println("Guess a letter.");

        while (input == null&&!gameover) {
            try {
                input = s.next();

                if (input.length() > 1 || !input.matches("^[a-zA-Z]+$")) {
                    m = "Please enter a single letter.";
                    input=null;
                    throw new IllegalArgumentException();
                }
                if(all.contains(input.charAt(0))){
                    m = "That letter was already guessed.";
                    input=null;
                    throw new IllegalArgumentException();
                }
            } catch (Exception e) {
                System.out.println(m);
            }
        }
        g = Character.toLowerCase(input.charAt(0));
        return g;
    }
    static void printMan(char[] man, ArrayList<Character> wrong, char[] secretword) {
        System.out.println("+---+");
        System.out.println(" "+man[0]+"  |");
        System.out.println(man[4]+""+man[1]+""+man[5]+" |");
        System.out.println(man[2]+" "+man[3]+" |");
        System.out.println("   ===");
        System.out.print("Missed: ");
        wrong.forEach(System.out::print);
        System.out.println();
        for (char c: secretword) {
            System.out.print(c);
        }
        System.out.println();
    }
    static char[] fillArray(char[] a, char c){
        for (int i = 0; i < a.length; i++) {
            a[i] = c;
        }
        return a;
    }
    static String getWord(){
        String[] words = {"cat", "dog", "cow", "pig"};
        int rand =(int)(Math.random()*words.length);
        return  words[rand];
    }
}