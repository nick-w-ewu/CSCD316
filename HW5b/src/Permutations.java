import java.util.Arrays;

/**
 * Created by Nicholas Witmer on 2/6/2016 for CSCD316.
 */
public class Permutations
{
    public static int[] nextPermutation(int[] current)
    {
        int[] temp = Arrays.copyOf(current, current.length);
        if(temp.length == 1)
        {
            return temp;
        }
        if(temp.length == 2)
        {
            swap(temp, 0, 1);
            return temp;
        }
        if(temp[temp.length-2] < temp[temp.length-1])
        {
            swap(temp, temp.length-2, temp.length-1);
            return temp;
        }
        else if(isDecending(temp, 0))
        {
            Arrays.sort(temp);
            return temp;
        }
        else
        {
            int indexToSwap;
            int high;
            for(int i = 0; i < temp.length; i++)
            {
                if(isDecending(temp, i+1))
                {
                    high = findNext(temp, temp[i], i);
                    indexToSwap = find(temp, high);
                    swap(temp, i, indexToSwap);
                    Arrays.sort(temp, i+1, temp.length);
                    break;
                }
            }
        }
        return temp;
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
}
