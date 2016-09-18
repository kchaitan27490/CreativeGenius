package krishna.util.random;
import java.util.Random;
public class RandomSelection {
	/** Random number generator. */
    private static final Random RANDOM_GENERATOR = new Random();

    /**
     * Selects a random fraction of indices from a given range
     * with the ratio noOfPartsToBeSelected to noOfPartsToBeLeft.
     *
     * @param range                  Range of indices for selection.
     * @param noOfPartsToBeSelected  Number of parts to be selected in the given range.
     * @param noOfPartsToBeLeft      Number of parts to be left in the given range.
     * @return                       Boolean array with selected indices set to true.
     */
    public static boolean[] subset(int range, int noOfPartsToBeSelected, int noOfPartsToBeLeft)
    {
        boolean[] assigned = new boolean[range];
        int selected = 0;
        while (selected*(noOfPartsToBeSelected+noOfPartsToBeLeft) < assigned.length*noOfPartsToBeSelected)
        {
            int ind = RANDOM_GENERATOR.nextInt(assigned.length);
            while (assigned[ind]) ind = RANDOM_GENERATOR.nextInt(assigned.length);
            assigned[ind] = true;
            selected++;
        }
        return assigned;

    }

}
