import java.util.*;

public class Deck {
    private List<Integer> cards;

    public Deck() {
        cards = new ArrayList<>(52);
        for (int i = 0; i < 52; i += 1) {
            cards.add(i);
        }
    }

    public void reset() {
        cards.clear();
        for (int i = 0; i < 52; i += 1) {
            cards.add(i);
        }
    }

    private int randomDraw(Random rand) {
        int num = RandomUtils.uniform(rand, cards.size());
        return cards.remove(num);
    }

    public Set<Card> randomDraw(Random rand, int n) {
        Set<Card> hand = new HashSet<>(n);
        for (int i = 0; i < n; i += 1) {
            hand.add(new Card(randomDraw(rand)));
        }
        return hand;
    }
}
