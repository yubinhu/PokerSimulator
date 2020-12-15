import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Player {
    private Card[] hand;

    public Player() {
        hand = new Card[2];
    }

    public void collect(Card[] cards) {
        if (cards.length > 2) {
            throw new IllegalArgumentException("Too many cards dealt");
        } else if (cards.length < 2) {
            throw new IllegalArgumentException("Too few cards dealt");
        }
        for (int i = 0; i < 2; i += 1) {
            this.hand[i] = cards[i];
        }
    }

    public void collect(Set<Card> cards) {
        Card[] array = new Card[cards.size()];
        collect(cards.toArray(array));
    }

    public Set<Card> getHand() {
        return new HashSet<>(Arrays.asList(hand));
    }
}
