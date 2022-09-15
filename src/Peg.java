/**
 * Represents rods in the Tower of Hanoi game. getIndex() returns
 * the corresponding index.
 * 
 * @author Anders Lindström, anderslm@kth.se
 */
public enum Peg {
    LEFT(0), MIDDLE(1), RIGHT(2);

    public int getIndex() {
        return index;
    }

    private final int index;

    private Peg(int index) {
        this.index = index;
    }
}
