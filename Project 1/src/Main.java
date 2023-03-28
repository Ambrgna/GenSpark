import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int n = 0;
        int rand =(int)(Math.random()*2)+1;
        String ending;
        Scanner s = new Scanner(System.in);
        System.out.println(rand);
        System.out.println("""
                You are in a land full of dragons. In front of you,
                you see two caves. In one cave, the dragon is friendly
                and will share his treasure with you. The other dragon 
                is greedy amd hungry and will eat you on sight.
                Which cave will you go into? (1 or 2)
                """
        );

        while (true) {
            try {
                n = s.nextInt();
            } catch (Exception e) {
                System.out.println("Please enter 1 or 2.");
                s.next();
            }
            if(n==1||n==2)
                break;
            else
                System.out.println("Number must be 1 or 2. ");

        }

        ending = (n==rand) ? "Gobbles you down in one bite!" : "Offers you some of his treasure!";

        System.out.println("""
                    You approach the cave...
                    It is dark and spooky...
                    A large dragon jumps out in front of you! He opens his jaws and...
                    """+ending
        );
    }
}