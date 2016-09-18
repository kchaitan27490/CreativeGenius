package krishna.structure.table;

import krishna.structure.Headerable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import krishna.structure.data.DoubleData;
import krishna.system.progress.progress;

public interface DoubleDataTable extends Headerable, Cloneable 
{
	/**
     * Returns the number of objects.
     *
     * @return Number of objects.
     */
    public int noOfObjects();

    /**
     * Adds a data object to this table.
     *
     * @param obj The object to be added.
     */
    public void add(DoubleData obj);

    /**
     * Removes a data object from this table.
     *
     * @param obj  The object to be removed.
     * @return     True, if the object was found and removed from this table,
     *             false otherwise.
     */
    public boolean remove(DoubleData obj);

    /**
     * Returns collection of all objects from this table.
     *
     * @return Collection of all objects from this table.
     */
    public ArrayList<DoubleData> getDataObjects();

    /**
     * Returns the basic statistics of a given numerical attribute.
     *
     * @return Statistics of a given numerical attribute.
     */
   /**public NumericalStatistics getNumericalStatistics(int attr);*/

    /**
     * Returns the distribution of decision values in this table if the decision is nominal.
     * Array indices correspond to local decision codes from this data header.
     *
     * @return Distribution of decisions in this table.
     */
    public int[] getDecisionDistribution();

    /**
     * Returns the distribution of values in this table for a nominal attribute.
     * Array indices correspond to local value codes for a given attibute.
     *
     * @param attrInd	Index of the attribute.
     * @return			Distribution of values in this table.
     */
    public int[] getValueDistribution(int attrInd);

    /**
     * Random split of this table into 2 data collections
     * with the splitting ratio noOfPartsForLeft to noOfPartsForRight.
     *
     * @param noOfPartsForLeft  Number of parts for the table returned at the position 0.
     * @param noOfPartsForRight Number of parts for the table returned at the position 1.
     * @return                  Table splitted into 2 data collections.
     */
    public ArrayList<DoubleData>[] randomSplit(int noOfPartsForLeft, int noOfPartsForRight);

    /**
     * Random partition of this table into a given number of parts of equal sizes.
     *
     * @param noOfParts Number of parts to be generated.
     * @return          Table divided into noOfParts collections.
     */
    public ArrayList<DoubleData>[] randomPartition(int noOfParts);
 
    /**
     * Create and return a copy of this object.
     * 
     * @return Copy of this object.
     */
    public Object clone();

    /**
     * Saves this object to a file.
     *
     * @param outputFile File to be used for storing this object.
     * @param prog       Progress object for progress reporting.
     * @throws IOException If an I/O error has occured.
     * @throws InterruptedException If user has interrupted saving object.
     */
    public void store(File outputFile, progress prog) throws IOException, InterruptedException;

    /**
     * Saves this object to a file in arff format.
     *
     * @param outputFile File to be used for storing this object.
     * @param prog       Progress object for progress reporting.
     * @throws IOException If an I/O error has occured.
     * @throws InterruptedException If user has interrupted saving object.
     */
    public void storeArff(String name, File outputFile, progress prog) throws IOException, InterruptedException;
}
