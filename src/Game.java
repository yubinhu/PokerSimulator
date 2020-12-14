import java.util.Random;

public class Game {
    private Deck deck;
    private int playerNum;
    private Random rand;
    private long seed;
    private Player[] players;

    /**
     * Default game.
     */
    public Game() {
        this(4, 1);
    }

    public Game(int playerNum, long seed) {
        deck = new Deck();
        this.playerNum = playerNum;
        this.seed = seed;
        rand = new Random(seed);
        players = new Player[playerNum];
        for (int i = 0; i < playerNum; i += 1) {
            players[i] = new Player();
        }
    }

    public void deal() {
        for (int i = 0; i < playerNum; i += 1) {
            players[i].collect((Card[]) deck.randomDraw(rand, 2).toArray());
        }
    }

    public static void main(String[] args) {
        
    }

}
