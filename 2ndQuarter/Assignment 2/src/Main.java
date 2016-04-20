import java.util.*;

/**
 * Created by Nicholas Witmer on 4/10/2016 for CSCD316. This solves the Contest Scoreboard problem.
 */
public class Main
{
    public class Contestant implements Comparable<Contestant>
    {
        private int contestant_num;
        private int numProblems;
        private int time;
        private Hashtable<Integer, Integer> problems = new Hashtable<>();
        private Hashtable<Integer, Integer> startTimes = new Hashtable<>();

        public Contestant(int n)
        {
            contestant_num = n;
            numProblems = 0;
            time = 0;
        }

        public int addTime(int t, int p)
        {
            Integer temp = problems.get(p);
            if(temp == null)
            {
                temp = 0;
            }
            else if(temp == -1)
            {
                return-1;
            }
            temp+=t;
            this.problems.put(p, temp);
            return 1;
        }

        public void compleateProblem(int p)
        {
            int temp = problems.get(p);
            Integer temp2 = startTimes.get(p);
            if(temp2 != null)
            {
                this.time-=temp2;
            }
            this.time+=temp;
            this.numProblems++;
            this.problems.put(p, -1);
        }

        public void startProblem(int p, int t)
        {
            Integer temp = startTimes.get(p);
            if(temp == null)
            {
                this.startTimes.put(p, t);
            }
        }

        @Override
        public int compareTo(Contestant that)
        {
            if(this.numProblems == that.numProblems && this.time == that.time)
            {
                return this.contestant_num-that.contestant_num;
            }
            if(this.numProblems == that.numProblems)
            {
                return this.time-that.time;
            }
            return that.numProblems-this.numProblems;
        }

        public String toString()
        {
            return this.contestant_num + " " + this.numProblems + " " + this.time;
        }
    }
    private Hashtable<Integer, Contestant> contestants = new Hashtable<>();
    private ArrayList<String> ignoredSubmissions = new ArrayList<>();

    public static void main(String[] args)
    {
        Main main = new Main();
        main.ignoredSubmissions.add("R");
        main.ignoredSubmissions.add("U");
        main.ignoredSubmissions.add("E");
        main.scoreContest();
    }

    private void scoreContest()
    {
        Scanner input = new Scanner(System.in);
        int cases = input.nextInt(), count = 0;
        String line;
        boolean first = true;
        input.nextLine();
        input.nextLine();
        while(count < cases)
        {
            line = input.nextLine();
            while(input.hasNext())
            {
                processLine(line);
                line = input.nextLine();
                if(line.equals(""))
                {
                    break;
                }
            }
            printResults(first);
            this.contestants.clear();
            count++;
            first = false;
        }
    }

    private void processLine(String line)
    {
        String[] split = line.split(" ");
        int contestant = Integer.parseInt(split[0]);
        int problem = Integer.parseInt(split[1]);
        int time = Integer.parseInt(split[2]);
        boolean missing = false;
        Contestant temp = this.contestants.get(contestant);
        if(temp == null)
        {
            temp = new Contestant(contestant);
            missing = true;
        }
        if(split[3].equals("I"))
        {
            temp.startProblem(problem, time);
            time+=20;
            temp.addTime(time, problem);
            this.contestants.put(contestant, temp);
        }
        else if(split[3].equals("C"))
        {
            int test = temp.addTime(time, problem);
            if(test != -1)
            {
                temp.compleateProblem(problem);
                this.contestants.put(contestant, temp);
            }
        }
        else if(this.ignoredSubmissions.contains(split[3]) && missing)
        {
            temp.addTime(0, problem);
            this.contestants.put(contestant, temp);
        }
    }

    private void printResults(boolean first)
    {
        ArrayList<Contestant> temp = new ArrayList<>();
        Enumeration<Contestant> temp2 = this.contestants.elements();
        while(temp2.hasMoreElements())
        {
            temp.add(temp2.nextElement());
        }
        Collections.sort(temp);
        if(!first)
        {
            System.out.println();
        }
        for(Contestant c : temp)
        {
            System.out.println(c);
        }
    }
}
