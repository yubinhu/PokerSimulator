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
        this.hand[0] = cards[0];
        this.hand[1] = cards[1];
    }
}
