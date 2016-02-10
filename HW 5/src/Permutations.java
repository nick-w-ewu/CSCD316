import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by nicho on 2/6/2016.
 */
public class Permutations
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter a series of numbers you would like to generate the next permutation for");
        String original = input.nextLine();
        String[] splitOriginal;
        int[] converted;

        while(!original.equalsIgnoreCase("Exit"))
        {
            splitOriginal = original.split(" ");
            converted = convertToIntArray(splitOriginal);
            nextPermutation(converted);
            for(int i : converted)
            {
                System.out.print(i + " ");
            }
            System.out.println("\nPlease enter a series of numbers, seperated by a space you would like to " +
                                "generate the next permutation for");
            original = input.nextLine();
        }
    }

    private static void nextPermutation(int[] current)
    {
        if(current[current.length-2] < current[current.length-1])
        {
            swap(current, current.length-2, current.length-1);
            return;
        }
        else if(isDecending(current, 0))
        {
            Arrays.sort(current);
            return;
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
                    return;
                }
            }
        }
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
