import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by nicho_000 on 2/20/2016.
 */
public class Supervisors
{
    private static int[][] supervisorPreferences = new int[7][];
    private static int[][] employeePreferences = new int[7][];
    private static int[][] possibleDistributions = new int[5040][];
    private static int[] totalDifferences;

    public static void main(String[] args)
    {
        Scanner input = openInputFile("supervisors_official.in");
        int dataSets = input.nextInt();
        int count = 1;
        int[] seed = {1,2,3,4,5,6,7};
        generateDistributions(seed);

        while(count <= dataSets)
        {
            input.nextLine();
            String temp;
            for(int i = 0; i < 7; i++)
            {
                temp = input.nextLine();
                supervisorPreferences[i] = convertToIntArray(temp.split(" "));
            }
            for(int i = 0; i < 7; i++)
            {
                temp = input.nextLine();
                employeePreferences[i] = convertToIntArray(temp.split(" "));
            }
            totalDifferences  = new int[5040];
            calculateDifferences();
            int min = findMin();
            double difference = (double)min/14.0;
            System.out.printf("Data Set %d, Best average difference: %.06f\n", count, difference);
            printPossibleParings(min);
            System.out.println();
            count++;
        }
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

    private static int[] convertToIntArray(String[] splitOriginal)
    {
        int[] toReturn = new int[splitOriginal.length];
        for(int i = 0; i < splitOriginal.length; i++)
        {
            toReturn[i] = Integer.parseInt(splitOriginal[i]);
        }
        return toReturn;
    }

    private static Scanner openInputFile(String fileName)
    {
        Scanner fileScanner = null;
        File fileHandle;

        try
        {
            fileHandle = new File(fileName);

            fileScanner = new Scanner(fileHandle);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File " + fileName + " was not found. The program will accept console input if you wish to continue");
            fileScanner = new Scanner(System.in);
        }

        return fileScanner;
    }
}
