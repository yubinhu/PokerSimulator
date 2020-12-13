import java.util.*;

public class Hands implements Comparable {
    public static final String SF = "Straight Flush";
    public static final String FK = "Four of a Kind";
    public static final String FH = "Full House (3+2)";
    public static final String FL = "Flush";
    public static final String ST = "Straight";
    public static final String TK = "Three of a Kind";
    public static final String TP = "Two Pairs";
    public static final String OP = "One Pair";
    public static final String NP = "No Pair";
    private List<Integer> compareBases;
    private Set<Card> cardSet;
    private String type;

    private int rank(String s) {
        switch (s) {
            case SF:
                return 0;
            case FK:
                return 1;
            case FH:
                return 2;
            case FL:
                return 3;
            case ST:
                return 4;
            case TK:
                return 5;
            case TP:
                return 6;
            case OP:
                return 7;
            case NP:
                return 9;
            default:
                throw new IllegalArgumentException("Cannot rank " + s);
        }
    }

    /**
     * Construct an empty hand.
     */
    public Hands() {
        cardSet = new HashSet<>();
        compareBases = new ArrayList<>();
        type = null;
    }

    public Hands(Set<Card> cards, boolean doIdentify) {
        this();
        cardSet = cards;
        if (doIdentify) {
            this.identify();
        }
    }

    public List<Integer> getCompareBases() {
        return compareBases;
    }

    /**
     * Helper function for compareToFive.
     * Return a positive int if this is better than other.
     * Both should have the same rank and the same size 5!!!
     */
    private int tieBreak(Hands other) {
        List<Integer> cb1 = this.compareBases;
        List<Integer> cb2 = other.getCompareBases();
        if (cb1.size() != cb2.size()) {
            throw new IllegalArgumentException("Error: Not a tie");
        }
        for (int i = 0; i < cb1.size(); i += 1) {
            if (cb1.get(i) == cb2.get(i)) {
                continue;
            } else {
                return cb1.get(i) - cb2.get(i);
            }
        }
        return 0;
    }

    public int size() {
        return cardSet.size();
    }

    /**
     * Identify a set of five cards.
     * Change compareBases
     */
    public String identifyFive() {
        if (cardSet.size() != 5) {
            throw new IllegalArgumentException("Not Five Cards");
        }
        List<Card> ordered = new ArrayList<>(cardSet);
        Map<Integer, Integer> pairs = new HashMap<>();
        Collections.sort(ordered);
        boolean sf = true;
        boolean fl = true;
        boolean st = true;
        boolean fk = false;
        boolean fh = false;
        boolean tk = false;
        boolean op = false;
        boolean tp = false;
        int lastColor = ordered.get(0).getColor();
        int lastNum = ordered.get(0).getNum() - 1;
        int pairCount = 0;
        for (Card c : ordered) {
            if (c.getColor() != lastColor) {
                sf = false;
                fl = false;
            }
            if (c.getNum() != lastNum + 1) {
                sf = false;
                st = false;
            }

            if (c.getNum() == lastNum) {
                op = true;
                pairCount += 1;
                if (pairCount >= 2) {
                    tp = true;
                }
                pairs.put(lastNum, pairs.get(lastNum) + 1);
            } else {
                pairs.put(c.getNum(), 1);
            }
            lastNum = c.getNum();
            lastColor = c.getColor();
        }
        if (pairs.containsValue(4)) {
            fk = true;
        }
        if (pairs.containsValue(3)) {
            tk = true;
            if (pairs.containsValue(2)) {
                fh = true;
            }
        }


        if (sf) {
            compareBases.clear();
            compareBases.add(ordered.get(4).getNum());
            type = SF;
            return SF;
        }
        if (fk) {
            int first = -1, second = -1;
            for (Map.Entry<Integer, Integer> entry : pairs.entrySet()) {
                if (entry.getValue() == 4) {
                    first = entry.getKey();
                } else if (entry.getValue() == 1) {
                    second = entry.getKey();
                } else {
                    throw new IllegalStateException("Weird pairs");
                }
            }
            compareBases.clear();
            compareBases.add(first);
            compareBases.add(second);
            type = FK;
            return FK;
        }
        if (fh) {
            int first = -1, second = -1;
            for (Map.Entry<Integer, Integer> entry : pairs.entrySet()) {
                if (entry.getValue() == 3) {
                    first = entry.getKey();
                } else if (entry.getValue() == 2) {
                    second = entry.getKey();
                } else {
                    throw new IllegalStateException("Weird pairs");
                }
            }
            compareBases.clear();
            compareBases.add(first);
            compareBases.add(second);
            type = FH;
            return FH;
        }
        if (fl) {
            compareBases.clear();
            for (int i = 4; i >= 0; i -= 1) {
                compareBases.add(ordered.get(i).getNum());
            }
            type = FL;
            return FL;
        }
        if (st) {
            compareBases.clear();
            compareBases.add(ordered.get(4).getNum());
            type = ST;
            return ST;
        }
        if (tk) {
            int first = -1;
            for (Map.Entry<Integer, Integer> entry : pairs.entrySet()) {
                if (entry.getValue() == 3) {
                    first = entry.getKey();
                }
            }
            compareBases.clear();
            compareBases.add(first);
            for (int i = 4; i >= 0; i -= 1) {
                if (ordered.get(i).getNum() != first) {
                    compareBases.add(ordered.get(i).getNum());
                }
            }
            type = TK;
            return TK;
        }
        if (tp) {
            int one = -1, another = -1, last = -1;
            boolean flag = true;
            for (Map.Entry<Integer, Integer> entry : pairs.entrySet()) {
                if (entry.getValue() == 2) {
                    if (flag) {
                        one = entry.getKey();
                    } else {
                        another = entry.getKey();
                    }
                } else if (entry.getValue() == 1) {
                    last = entry.getKey();
                } else {
                    throw new IllegalStateException("Weird pairs");
                }
            }
            compareBases.clear();
            if (one > another) {
                compareBases.add(one);
                compareBases.add(another);
            } else {
                compareBases.add(another);
                compareBases.add(one);
            }
            compareBases.add(last);
            type = TP;
            return TP;
        }
        if (op) {
            int first = -1;
            for (Map.Entry<Integer, Integer> entry : pairs.entrySet()) {
                if (entry.getValue() == 2) {
                    first = entry.getKey();
                }
            }
            compareBases.clear();
            compareBases.add(first);
            for (int i = 4; i >= 0; i -= 1) {
                if (ordered.get(i).getNum() != first) {
                    compareBases.add(ordered.get(i).getNum());
                }
            }
            type = OP;
            return OP;
        }
        compareBases.clear();
        for (int i = 4; i >= 0; i -= 1) {
            compareBases.add(ordered.get(i).getNum());
        }
        type = NP;
        return NP;
    }

    public String getType() {
        return type;
    }

    /**
     * After identifying, compareBases and type should be initiated.
     * Both are the best.
     */
    public String identify() {
        int n = cardSet.size();
        if (n == 0) {
            return null;
        }
        if (n <= 5) {
            return this.identifyFive();
        } else {
            Hands bestHand = new Hands();
            Set<Card> buffer = new HashSet<>();
            for (Card card : cardSet) {
                buffer.add(card);
            }
            for (Card card : cardSet) {
                buffer.remove(card);
                Hands hand = new Hands(buffer, false);
                hand.identify();
                bestHand.identify();
                if (hand.compareTo(bestHand) > 0) {
                    bestHand = hand;
                }
                buffer.add(card);
            }
            type = bestHand.getType();
            compareBases = bestHand.getCompareBases();
            return type;
        }
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure
     * {@code sgn(x.compareTo(y)) == -sgn(y.compareTo(x))}
     * for all {@code x} and {@code y}.  (This
     * implies that {@code x.compareTo(y)} must throw an exception iff
     * {@code y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code x.compareTo(y)==0}
     * implies that {@code sgn(x.compareTo(z)) == sgn(y.compareTo(z))}, for
     * all {@code z}.
     *
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     *
     * <p>In the foregoing description, the notation
     * {@code sgn(}<i>expression</i>{@code )} designates the mathematical
     * <i>signum</i> function, which is defined to return one of {@code -1},
     * {@code 0}, or {@code 1} according to whether the value of
     * <i>expression</i> is negative, zero, or positive, respectively.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(Object o) {
        Hands other = (Hands) o;
        if (o instanceof Hands) {
            if (this.size() == 0 && other.size() == 0) {
                return 0;
            } else if (this.size() == 0) {
                return -1;
            } else if (other.size() == 0) {
                return 1;
            } else if (this.size() != other.size()) {
                throw new IllegalArgumentException("Comparing different sized hands");
            }

            String r1 = this.getType();
            String r2 = other.getType();
            if (r1.equals(r2)) {
                return tieBreak(other);
            } else {
                return rank(r2) - rank(r1);
            }
        } else {
            throw new IllegalArgumentException("Cannot compare to non-Hands");
        }
    }
}
