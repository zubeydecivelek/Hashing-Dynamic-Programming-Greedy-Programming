import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * This class accomplishes Mission Exterminate
 */
public class OptimalFinalDefenseGP
{
    private ArrayList<Integer> bombWeights;

    public OptimalFinalDefenseGP(ArrayList<Integer> bombWeights) {
        this.bombWeights = bombWeights;
    }

    public ArrayList<Integer> getBombWeights() {
        return bombWeights;
    }

    /**
     *
     * @param maxNumberOfAvailableAUAVs the maximum number of available AUAVs to be loaded with bombs
     * @param maxAUAVCapacity the maximum capacity of an AUAV
     * @return the minimum number of AUAVs required using first fit approach over reversely sorted items.
     * Must return -1 if all bombs can't be loaded onto the available AUAVs
     */
    public int getMinNumberOfAUAVsToDeploy(int maxNumberOfAvailableAUAVs, int maxAUAVCapacity)
    {
        // First sort all weights in decreasing order
        bombWeights.sort(Collections.reverseOrder());
        // Initialize result (Count of AUAVs)
        int result = 0;
        // Create an array to store remaining space in AUAVs, there can be at most maxNumberOfAvailableAUAVs AUAVs
        int[] auavArray = new int[maxNumberOfAvailableAUAVs];
        Arrays.fill(auavArray,maxAUAVCapacity);

        for (int i = 0; i < bombWeights.size(); i++){

            if (bombWeights.get(i) > maxAUAVCapacity)
                return -1;

            int index = findAUAVIndex(auavArray,maxNumberOfAvailableAUAVs,bombWeights.get(i),maxAUAVCapacity);

            if (index == -1)
                return -1;

            auavArray[index] -= bombWeights.get(i);
        }

        for (int capacity: auavArray)
            if (capacity != maxAUAVCapacity)
                result++;

        return result;
    }
    public void printFinalDefenseOutcome(int maxNumberOfAvailableAUAVs, int AUAV_CAPACITY){
        int minNumberOfAUAVsToDeploy = this.getMinNumberOfAUAVsToDeploy(maxNumberOfAvailableAUAVs, AUAV_CAPACITY);
        if(minNumberOfAUAVsToDeploy!=-1) {
            System.out.println("The minimum number of AUAVs to deploy for complete extermination of the enemy army: " + minNumberOfAUAVsToDeploy);
        }
        else{
            System.out.println("We cannot load all the bombs. We are doomed.");
        }
    }

    public int findAUAVIndex(int[] array, int maxNumberOfAvailableAUAVs, int bombWeight, int maxAUAVCapacity){
        for (int i = 0; i < maxNumberOfAvailableAUAVs; i++)
            if (array[i] >= bombWeight)
                return i;

        return -1;
    }

}
