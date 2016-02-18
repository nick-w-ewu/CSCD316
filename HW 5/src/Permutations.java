import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by nicho on 2/6/2016.
 */
public class Permutations
{
    private static int[][] testVals = {{1}, {1,2}, {1,3,2}, {1,3,4,2}, {2,1,3,4,5}, {3,1,6,4,5,2},
                        {6,2,7,4,3,1,5}, {8,7,6,5,4,2,3,1}, {8,7,6,5,4,3,2,1}, {7,6,5,2,4,9,3,1,8},
                        {0,1,2,3,4,5,6,7,8,9}, {4,6,2}, {20,125,100,40}};
    public static void main(String[] args)
    {
        int[] result;
        for(int i = 0; i < testVals.length; i++)
        {
            System.out.print("Input Array: ");
            printArray(testVals[i]);
            result = nextPermutation(testVals[i]);
            System.out.print("Next Permutation: ");
            printArray(result);
        }
    }

    private static void printArray(int[] a)
    {
        for(int i : a)
        {
            System.out.print(i + ",");
        }
        System.out.println();
    }


    private static int[] nextPermutation(int[] current)
    {
        if(current.length == 1)
        {
            return current;
        }
        if(current.length == 2)
        {
            swap(current, 0, 1);
            return current;
        }
        if(current[current.length-2] < current[current.length-1])
        {
            swap(current, current.length-2, current.length-1);
            return current;
        }
        else if(isDecending(current, 0))
        {
            Arrays.sort(current);
            return current;
        }
        else
        {
            int indexToSwap;
            int high;
            for(int i = 0; i < current.length; i++)
            {
                if(isDecending(current, i+1))
                {
                    high = findNext(current, current[i]);
                    indexToSwap = find(current, high);
                    swap(current, i, indexToSwap);
                    Arrays.sort(current, i+1, current.length);
                    break;
                }
            }
        }
        return current;
    }

    private static int findNext(int[] current, int i)
    {
        int[] temp = Arrays.copyOf(current, current.length);
        Arrays.sort(temp);
        int index = Arrays.binarySearch(temp, i);
        return temp[index+1];
    }

    private static int find(int[] current, int num)
    {
        int toReturn = -1;
        for(int i = 0; i < current.length; i++)
        {
            if(current[i] == num)
            {
                toReturn = i;
            }
        }
        return toReturn;
    }

    private static boolean isDecending(int[] current, int start)
    {
        for(int i = start; i < current.length-1; i++)
        {
            if(current[i] < current[i+1])
            {
                return false;
            }
        }
        return true;
    }


    private static void swap(int[] current, int ix1, int ix2)
    {
        int temp = current[ix1];
        current[ix1] = current[ix2];
        current[ix2] = temp;
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

    private static boolean isInteger(String input)
    {
        try
        {
            int test = Integer.parseInt(input);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

}
