import java.util.Arrays;

/**
 * Created by nicho_000 on 2/20/2016.
 */
public class Supervisors
{
    private static int[][] supervisorPreferences = {{1,2,3,4,5,6,7}, {2,1,3,4,5,6,7}, {3,1,2,4,5,6,7}, {4,1,2,3,5,6,7},
                                                    {6,1,2,3,4,5,7}, {6,1,2,3,4,5,7}, {6,1,2,3,4,5,7}};
    private static int[][] employeePreferences = {{1,2,3,4,5,6,7}, {2,1,3,4,5,6,7}, {3,1,2,4,5,6,7}, {4,1,2,3,5,6,7},
                                                    {6,1,2,3,4,5,7}, {6,1,2,3,4,5,7}, {6,1,2,3,4,5,7}};
//    private static int[][] employeePreferences = {{1,2,3,4}, {1,2,3,4}, {1,2,3,4}};
//    private static int[][] supervisorPreferences = {{1,2,3,4}, {1,2,3,4}, {1,2,3,4}};
    private static int[][] possibleDistributions = new int[5040][];
    private static int[] totalDifferences = new int[5040];

    public static void main(String[] args)
    {
        int[] seed = {1,2,3,4,5,6,7};
        generateDistributions(seed);
//        for(int[] i : possibleDistributions)
//        {
//            for(int j : i)
//            {
//                System.out.print(j);
//            }
//            System.out.println();
//        }
        calculateDifferences();
//        for(int i : totalDifferences)
//        {
//            System.out.println(i);
//        }
        int min = findMin();
        printPossibleParings(min);
    }

    private static void generateDistributions(int[] seed)
    {
        possibleDistributions[0] = Arrays.copyOf(seed, seed.length);
        for(int i = 1; i < possibleDistributions.length; i++)
        {
            possibleDistributions[i] = Permutations.nextPermutation(possibleDistributions[i-1]);
        }
    }

    private static void calculateDifferences()
    {
        int currentDifference, r, c;
        for(r = 0; r < possibleDistributions.length; r++)
        {
            currentDifference = 0;
            for(c = 0; c < possibleDistributions[r].length; c++)
            {
                currentDifference += testAgainstPreference(c, possibleDistributions[r][c]);
                currentDifference += testEmployeePreference(possibleDistributions[r][c]-1, c+1);
            }
            totalDifferences[r] = currentDifference;
        }
    }

    private static int testAgainstPreference(int c, int employee)
    {
        for(int i = 0; i < supervisorPreferences[c].length; i++)
        {
            if(supervisorPreferences[c][i] == employee)
            {
                return i;
            }
        }
        return -1;
    }

    private static int testEmployeePreference(int employee, int supervisor)
    {
        for(int i = 0; i < employeePreferences[employee].length; i++)
        {
            if(employeePreferences[employee][i] == supervisor)
            {
                return i;
            }
        }
        return -1;
    }

    private static int findMin()
    {
        int min = 100000;
        for(int i = 0; i < totalDifferences.length; i++)
        {
            if(totalDifferences[i] < min)
            {
                min = totalDifferences[i];
            }
        }
        return min;
    }


    private static void printPossibleParings(int bestDifference)
    {
        int pairingNum = 1;
        for(int i = 0; i < totalDifferences.length; i++)
        {
            if(totalDifferences[i] == bestDifference)
            {
                System.out.println("Best Pairing " + pairingNum);
                for(int j = 0; j < possibleDistributions[i].length; j++)
                {
                    int supervisorNum = j+1;
                    System.out.println("Supervisor " + supervisorNum + " with Employee " + possibleDistributions[i][j]);
                }
                pairingNum++;
            }
        }
    }

}
