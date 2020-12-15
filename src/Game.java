import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private Deck deck;
    private int playerNum;
    private Random rand;
    private long seed;
    private Player[] players;
    private List<Card> community;

    /**
     * Default game.
     */
    public Game() {
        this(4, 1);
    }

    public Game(int playerNum, long seed) {
        community = new ArrayList<>();
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
            players[i].collect(deck.randomDraw(rand, 2));
        }
    }

    public void printGame() {
        Record printer = new Record(); //Just print, no record
        printer.appendln("Community cards: ");
        printer.appendln(community);
        for (int i = 0; i < playerNum; i += 1) {
            printer.appendln("Player " + i);
            printer.appendln(players[i].getHand());
        }
    }


    public void drawGame() {
        //TODO: implement a graphic interface
        return;
    }

    public static void main(String[] args) {
        Game test = new Game();
        test.deal();
        test.printGame();
    }

}
