import java.util.Arrays;

/**
 * The state and logic for the Pyramid Puzzle game.
 */
public class PyramidPuzzle {

    private final int[][] state;
    private final int numofDisks;
    private int numofMoves;

    /**
     * Create a new PyramidPuzzle object with the indicated number of disks.
     *
     * @param numDisks the number of disks
     */
    public PyramidPuzzle(int numDisks) {
        this.state= new int[Peg.values().length][numDisks];
        this.numofDisks= numDisks;
        this.numofMoves=0;

        initNewRound();
    }

    /**
     * Create a new PyramidPuzzle object with four disks.
     */
    public PyramidPuzzle() {
        this(4);
    }

    /**
     * Initialize a new round, i.e. reset numMoves and the values in the state matrix.
     */
    public void initNewRound() {


        for(int peg=Peg.LEFT.getIndex();peg<Peg.RIGHT.getIndex();peg++){
            for(int pos=0;pos<numofDisks;pos++){
                state[peg][pos]=0;
            }
        }
        // add disks
        for(int pos=0;pos<numofDisks;pos++){
            state[Peg.LEFT.getIndex()][pos]=numofDisks-pos;
        }
    }



    /**
     * @return the number of disks
     */
    public int getNumDisks() {
        return 0;
    }

    /**
     * @return the current number of moves
     */
    public int getNumMoves() {

        return numofMoves;
    }

    /**
     * Return whether the suggested move is allowed or not.
     * NB! This method does not move the disk.
     *
     * @param src  the peg to move a disk from
     * @param dest the disc to move the disk to
     * @return true if the move is allowed, false otherwise
     */
    public boolean isAllowedMove(Peg src, Peg dest) {
        int srcTopPos = getTopIndex(src);
        int destTopsize = getTopSize(dest);
        int diskSize = getTopSize(src);

      if(destTopsize==0)
      {

          return true;
      }
         else if(diskSize < destTopsize)
         {
             return true;
         }
      else
      {
          return false;
      }
    }

    /**
     * Move a disk and update the number of moves.
     *
     * @param src  the peg to move a disk from
     * @param dest the disc to move the disk to
     * @return true if the move is alloed and performed
     */
    public boolean makeMove(Peg src, Peg dest) {

       if( isAllowedMove( src,dest)==true){

            int srcTopPos = getTopIndex(src);
            int destTopPos = getTopIndex(dest);
            int diskSize = getTopSize(src);

            state[dest.getIndex()][destTopPos + 1] = diskSize;
            state[src.getIndex()][srcTopPos] = 0;

            numofMoves++;

        }else {
           System.out.println("illegal move");
           return false;
       }


      if( isSolved()==true){
          System.out.println("Spelet löst");
      }
        return true;

    }

    /**
     * Return true if all disks are at the right peg in ascending order, i.e. the puzzle
     * check if move is allowed
     * is solved, otherwise return false.
     *
     * @return true if the puzzle is solved
     */
    public boolean isSolved() {

       // if(state[Peg.RIGHT.getIndex()][getTopSize(Peg.RIGHT)]==1 && getTopSize(Peg.RIGHT)==(numofDisks-1))
        if(getTopSize(Peg.RIGHT)==1 && getTopIndex(Peg.RIGHT)==(numofDisks-1))
        {
            return true;
        }else
        {
            return false;
        }
    }

    /**
     * Return a <em>copy</em> of the internal state, a two-dimensional int
     * array, dimensions [num of pegs][num of disks]. The value of a single
     * field represents the size of the disk; zero represents an empty position.
     *
     * @return a copy of the internal state
     */
    public int[][] getCopyOfState() {
    int[][] cpyOfState = new int[3][numofDisks];
       // int [][]cpyOfState= Arrays.copyOf(state,numofDisks);
    //    int [][]cpyOfState=  state.clone();??

        for(int i=0;i<3;i++){
            for(int j=0;j< numofDisks;j++){
                cpyOfState[i][j]=this.state[i][j];
            }
        }

        return cpyOfState;
    }

    /**
     * Return a string with format [values at left peg], [values at middle peg], [values at left peg].
     * Example: [4,3,2,0], [0,0,0,0], [1,0,0,0], 1
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        StringBuilder builder=new StringBuilder();

        for(Peg peg : Peg.values()){

            builder.append(Arrays.toString(state[peg.getIndex()]));
            builder.append(",");
        }

        builder.append(numofMoves);
        return builder.toString();
    }

    // 0 represents empty peg
    private int getTopSize(Peg peg) {
        int PegInd= peg.getIndex();

        for(int i =numofDisks-1;i>=0;i--){

            if(state[PegInd][i]!=0){
                return state[PegInd][i];
            }
        }

        return 0;
    }

    // -1 represents empty peg
    private int getTopIndex(Peg peg) {
        int PegInd= peg.getIndex();

        for(int i =numofDisks-1;i>=0;i--){

            if(state[PegInd][i]!=0){
                return i;
            }
        }

        return -1;
    }
}

