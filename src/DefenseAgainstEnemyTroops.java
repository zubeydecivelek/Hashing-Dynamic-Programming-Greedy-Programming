import java.util.ArrayList;

/**
 * This class accomplishes Mission Nuke'm
 */
public class DefenseAgainstEnemyTroops {
    private ArrayList<Integer> numberOfEnemiesArrivingPerHour;
    private int totalHour;

    public DefenseAgainstEnemyTroops(ArrayList<Integer> numberOfEnemiesArrivingPerHour){
        this.numberOfEnemiesArrivingPerHour = numberOfEnemiesArrivingPerHour;
        totalHour = numberOfEnemiesArrivingPerHour.size();
    }

    public ArrayList<Integer> getNumberOfEnemiesArrivingPerHour() {
        return numberOfEnemiesArrivingPerHour;
    }

    private int getRechargedWeaponPower(int hoursCharging){
        return hoursCharging*hoursCharging;
    }

    /**
     *     Function to implement the given dynamic programming algorithm
     *     SOL(0) <- 0
     *     HOURS(0) <- [ ]
     *     For{j <- 1...N}
     *         SOL(j) <- max_{0<=i<j} [ (SOL(i) + min[ E(j), P(j − i) ] ]
     *         HOURS(j) <- [HOURS(i), j]
     *     EndFor
     *
     * @return OptimalEnemyDefenseSolution
     */
    public OptimalEnemyDefenseSolution getOptimalDefenseSolutionDP(){
        // TODO: YOUR CODE HERE

        int[] sol = new int[totalHour+1];
        sol[0]  = 0;

        ArrayList<Integer>[] hours = new ArrayList[totalHour+1];
        hours[0] = new ArrayList<>();

        for (int j = 1; j <= totalHour; j++){
            int max = 0;
            for (int i = 0; i<j;i++){
                int compare = sol[i] + Math.min(numberOfEnemiesArrivingPerHour.get(j-1), getRechargedWeaponPower(j-i));
                if (compare > max){
                    max = compare;
                    sol[j] = max;
                    ArrayList<Integer> newList = new ArrayList<>(hours[i]);
                    newList.add(j);
                    hours[j] = newList;
                }
            }
        }
        return new OptimalEnemyDefenseSolution(sol[totalHour], hours[totalHour]);
    }
}
