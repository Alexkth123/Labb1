import java.util.Scanner;

/**
 * Use this class to play the game when the class PyramidPuzzle is completed.
 */
public class PyramidPuzzleUI {

    private final PyramidPuzzle puzzle;
    private final Scanner scan;

    public PyramidPuzzleUI(int numDisks) {
        puzzle = new PyramidPuzzle(numDisks);
        scan = new Scanner(System.in);
    }

    public void playGame() {
        System.out.println("Welcome to the Pyramid Puzzle!");

        while (!puzzle.isSolved()) {
            printState(puzzle.getCopyOfState());
            System.out.println("Moves: " + puzzle.getNumMoves());

            System.out.println("Move from (l (left), m (middle) or r (right)? ");
            String fromStr = scan.next(); // read next "word"
            Peg fromPeg = valueFromString(fromStr);
            System.out.println("Move to (l (left), m (middle) or r (right)? ");
            String toStr = scan.next();
            Peg toPeg = valueFromString(toStr);

            if (puzzle.isAllowedMove(fromPeg, toPeg)) {
                puzzle.makeMove(fromPeg, toPeg);
            } else {
                System.out.println("Move not allowed!");
            }
        }
        System.out.println("Puzzle solved!");
    }

    private Peg valueFromString(String str) {
        char value = str.toLowerCase().charAt(0);
        switch (value) {
            case 'l':
                return Peg.LEFT;
            case 'm':
                return Peg.MIDDLE;
            case 'r':
                return Peg.RIGHT;
            default:
                throw new IllegalArgumentException("value: " + value);
        }
    }

    private void printState(int[][] state) {
        int height = state[0].length; // the number of positions on a peg
        int nPegs = state.length; // number of pegs, i.e. width
        for (int pos = height - 1; pos >= 0; pos--) {
            String rowStr = "";
            for (int peg = 0; peg < nPegs; peg++) {
                if (state[peg][pos] != 0) {
                    rowStr += " " + state[peg][pos];
                } else {
                    rowStr += "  ";
                }
            }
            System.out.println(rowStr);
        }
        String bottomStr = "";
        for (int peg = 0; peg < nPegs; peg++) {
            bottomStr += " =";
        }
        System.out.println(bottomStr);
    }

    public static void main(String[] args) {
        PyramidPuzzleUI ui = new PyramidPuzzleUI(4);
        ui.playGame();
    }
}
