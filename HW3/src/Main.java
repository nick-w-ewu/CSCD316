import java.util.*;

/**
 * Created by Nicholas Witmer on 1/23/2016 for CSCD316 at EWU.
 */
class Main
{
    public static void main(String[] args)
    {
        String str1, str2, permutation;
        char[] word1, word2;
        Scanner input = new Scanner(System.in);

        while(input.hasNext())
        {
            str1 = input.nextLine();
            str2 = input.nextLine();
            word1 = str1.toCharArray();
            word2 = str2.toCharArray();
            permutation = findPermutations(word1, word2);
            System.out.println(permutation);
        }
    }

    private static String findPermutations(char[] a, char[] b)
    {
        String temp = "";
        ArrayList<String> permutations = new ArrayList<>();
        HashMap<String, Integer> map1 = generateMap(a);
        HashMap<String, Integer> map2 = generateMap(b);
        Set<String> word1 = map1.keySet();
        Set<String> word2 = map2.keySet();
        int count = 1, count1, count2;

        for(String str : word1)
        {
            if(word2.contains(str))
            {
                count1 = map1.get(str);
                count2 = map2.get(str);
                if(count1 == count2 || count1 < count2)
                {
                    count = count1;
                }
                else if(count1 > count2)
                {
                    count = count2;
                }
                for(int i = 0; i < count; i++)
                {
                    permutations.add(str);
                }
            }
        }
        Collections.sort(permutations);
        for(String str : permutations)
        {
            temp+=str;
        }
        return temp;
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
