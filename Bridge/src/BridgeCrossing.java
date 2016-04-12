import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by Nicholas Witmer for CSCD316 3/14/2016
 */
public class BridgeCrossing
{
    private static ArrayList<Integer> farSide;
    private static ArrayList<Integer> crossed;
    private static ArrayList<String> crossings;
    private static int time;

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        int cases = input.nextInt(), count = 0, people;


        while(count < cases)
        {
            setUp(input);
            people = input.nextInt();
            for(int i = 0; i < people; i++)
            {
                farSide.add(input.nextInt());
            }
            if(people == 1)
            {
                time+=farSide.get(0);
                crossings.add("" + farSide.get(0));
            }
            else
            {
                Collections.sort(farSide);
                getHome();
            }
            printCrossings();
            count++;
        }
    }

    private static void getHome()
    {
        String temp;
        int man1 = 0, returned = -1;
        int man2 = 1;
        int[] smallestDifference;
        while(!farSide.isEmpty())
        {
            time+=farSide.get(man2);
            temp = farSide.get(man1) + " " + farSide.get(man2);
            crossings.add(temp);
            returned = crossBridge(farSide.get(man1), farSide.get(man2));
            if(returned == -1)
            {
                break;
            }
            time+=returned;
            crossings.add(returned+"");
            farSide.add(returned);
            Collections.sort(farSide);
            smallestDifference = findSmallestDifference();
            man1 = smallestDifference[0];
            man2 = smallestDifference[1];
        }
    }

    private static int[] findSmallestDifference()
    {
        int[] smallest = new int[2];
        int difference = Integer.MAX_VALUE, tempDifference;
        if(farSide.size() == 2)
        {
            smallest[0] = 0;
            smallest[1] = 1;
        }
        else
        {
            for(int i = 0; i < farSide.size()-1; i++)
            {
                tempDifference = Math.abs(farSide.get(i) - farSide.get(i+1));

                if(tempDifference == 0 && difference == 0)
                {
                    if(farSide.get(i) > farSide.get(smallest[0]))
                    {
                        smallest[0] = i;
                        smallest[1] = i+1;
                    }
                }
                else if(tempDifference < difference)
                {
                    if(tempDifference == 0)
                    {
                        if(crossed.get(0) < farSide.get(i))
                        {
                            smallest[0] = i;
                            smallest[1] = i+1;
                            difference = tempDifference;
                        }
                    }
                    else if(!(farSide.get(smallest[0]) < farSide.get(i)) && !(farSide.get(smallest[1]) < farSide.get(i+1)))
                    {
                        smallest[0] = i;
                        smallest[1] = i+1;
                        difference = tempDifference;
                    }
                    else
                    {
                        smallest[0] = i;
                        smallest[1] = i+1;
                        difference = tempDifference;
                    }
                }
            }
        }

        return smallest;
    }

    private static int crossBridge(int man1, int man2)
    {
        crossed.add(man1);
        crossed.add(man2);
        int location = farSide.indexOf(man1);
        farSide.remove(location);
        location = farSide.indexOf(man2);
        farSide.remove(location);
        int temp;
        if(farSide.size() == 0)
        {
            temp = -1;
        }
        else
        {
            Collections.sort(crossed);
            temp = crossed.get(0);
            crossed.remove(0);
        }
        return temp;
    }

    private static void printCrossings()
    {
        System.out.println(time);
        for(String s : crossings)
        {
            System.out.println(s);
        }
        System.out.println();
    }

    private static void setUp(Scanner input)
    {
        input.nextLine();
        farSide = new ArrayList<>();
        crossed = new ArrayList<>();
        crossings = new ArrayList<>();
        time = 0;
    }
}
