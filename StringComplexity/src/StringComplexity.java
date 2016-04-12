import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Nicholas Witmer for CSCD316 3/14/2016.
 */

public class StringComplexity
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        String test;
        HashMap<String, Integer> letters;
        while(input.hasNext())
        {
            test = input.nextLine();
            letters = generateMap(test.toCharArray());
            if(letters.size() > 2)
            {
                System.out.println(Math.abs(2 - letters.size()));
            }
            else
            {
                System.out.println(0);
            }
        }
    }

    private static HashMap<String, Integer> generateMap(char[] word)
    {
        HashMap<String, Integer> toReturn = new HashMap<>();
        Integer count;
        for(int i = 0; i < word.length; i++)
        {
            count = toReturn.get("" + word[i]);
            if(count != null)
            {
                toReturn.put("" + word[i], count+1);
            }
            else
            {
                toReturn.put("" + word[i], 1);
            }
        }
        return toReturn;
    }
}
