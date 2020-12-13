import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Set<Card> test = new HashSet<>();
        test.add(new Card(3));
        test.add(new Card(16));
        test.add(new Card(29));
        test.add(new Card(5));
        test.add(new Card(18));
        Hands hand = new Hands(test,false);
        System.out.println(test);
        System.out.println(hand.identifyFive());
    }
}
