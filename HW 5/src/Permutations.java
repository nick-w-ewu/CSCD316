import java.util.Arrays;

/**
 * Created by Nicholas Witmer on 2/6/2016 for CSCD316.
 */
public class Permutations
{
    private static int[][] testVals = {{1,5,4,3,2}};

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
                    high = findNext(current, current[i], i);
                    indexToSwap = find(current, high);
                    swap(current, i, indexToSwap);
                    Arrays.sort(current, i+1, current.length);
                    break;
                }
            }
        }
        return current;
    }

    private static int findNext(int[] current, int i, int fromIndex)
    {
        int[] temp = Arrays.copyOfRange(current, fromIndex, current.length);
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
