import java.util.Random;
import java.util.Set;

public class RandomSimulation {
    public static void simple(long seed) {
        Random rand = new Random(seed);
        Deck deck = new Deck();
        Set<Card> test = deck.randomDraw(rand, 5);
        Hands hand = new Hands(test, false);
        System.out.println(test);
        System.out.println(hand.identifyFive());
    }

    public static void compare(long seed) {
        Random rand = new Random(seed);
        Deck deck = new Deck();
        Set<Card> test1 = deck.randomDraw(rand, 7);
        Set<Card> test2 = deck.randomDraw(rand, 7);
        Hands hand1 = new Hands(test1, false);
        Hands hand2 = new Hands(test2, false);
        System.out.println(test1);
        System.out.println(hand1.identify());
        System.out.println(test2);
        System.out.println(hand2.identify());
        System.out.println("Compare hand1 to hand2: " + hand1.compareTo(hand2));
    }

    public static void stat(long seed) {
        Random rand = new Random(seed);
        Deck deck = new Deck();
        Set<Card> test1 = deck.randomDraw(rand, 7);
        Set<Card> test2 = deck.randomDraw(rand, 7);
        Hands hand1 = new Hands(test1, true);
        //TODO: implement stat
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i += 1) {
            compare(i);
        }
    }
}
