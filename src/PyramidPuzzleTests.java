/**
 * Write tests for PyramidPuzzle in this class.
 * Ideally, each non-trivial method in PyramidPuzzle
 * should have a corressponding test method in this class
 */
public class PyramidPuzzleTests {

    private PyramidPuzzle puzzle;

    /*
     * This is where code for tests goes.
     * Recommended: Divide tests of methods/functionality into separate helper methods.
     */
    public void test() {
        // TODO: tests and/or user interactions for a round of the game
        // ...

        puzzle = new PyramidPuzzle(4);
        System.out.println(puzzle.toString());

        puzzle.makeMove(Peg.LEFT, Peg.RIGHT);
        System.out.println(puzzle.toString());

        puzzle.makeMove(Peg.LEFT, Peg.MIDDLE);
        System.out.println(puzzle.toString());

        // ...
    }

    private void testIsAllowedMove() {
        // add code to test isAllowedMove (when implemented) ...
    }

    public static void main(String[] args) {
        PyramidPuzzleTests tests = new PyramidPuzzleTests();
        tests.test();
    }
}
