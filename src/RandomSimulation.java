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

    public static double[] stat(long seed, int n) {
        Random rand = new Random(seed);
        int[] counter = new int[]{
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0
        };
        for (int i = 0; i < n; i += 1) {
            Deck deck = new Deck();
            Set<Card> test1 = deck.randomDraw(rand, 7);
            Hands hand1 = new Hands(test1, true);
            for (int j = 0; j < Hands.getNames().length; j += 1) {
                if (hand1.getType() == Hands.name(j)) {
                    counter[j] += 1;
                }
            }
        }
        double[] result = new double[Hands.getNames().length];
        for (int i = 0; i < result.length; i += 1) {
            result[i] = (double) counter[i] / (double) n;
        }
        return result;
    }

    public static void printStat(double[] arr, long seed, int n) {
        Record record = new Record("Problem 1 results");
        record.setPrint(true);
        record.appendln(n + " trials with seed " + seed);
        for (int i = 0; i < arr.length; i += 1) {
            record.append(Hands.getNames()[i]);
            record.append(": ");
            record.append(arr[i] * 100);
            record.append("%");
            record.appendln();
        }
        record.appendln();
    }

    public static void main(String[] args) {
        long seed = 95342534;
        int n = 10000000;
        printStat(stat(seed, n), seed, n);
    }
}
