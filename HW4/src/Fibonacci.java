import java.util.Scanner;

/**
 * Created by nicho_000 on 2/3/2016.
 */
public class Fibonacci
{
    private static long[] memory = new long[100];

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        long start, fib, end, memoStart, memoFib, memoEnd, milTime1, milTime2;
        double time, memoTime;
        System.out.println("Enter a Fibonacci number to calculate");
        int fibNumber = input.nextInt();
        while(fibNumber != 0)
        {
            start = System.currentTimeMillis();
            fib = fib(fibNumber);
            end = System.currentTimeMillis();
            memoStart = System.currentTimeMillis();
            memoFib = memoFib(fibNumber);
            memoEnd = System.currentTimeMillis();
            milTime1 = end - start;
            time = milTime1 / 1000.0;
            milTime2 = memoEnd - memoStart;
            memoTime = milTime2 / 1000.0;
            System.out.printf("The Fibonacci number for %d is %d and without Memoization it took %.5f seconds to compleate\n", fibNumber, fib, time);
            System.out.printf("The Fibonacci number for %d is %d and with Memoization it took %.5f seconds to compleate\n\n", fibNumber, memoFib, memoTime);
            System.out.println("Enter a Fibonacci number to calculate");
            fibNumber = input.nextInt();
        }
    }

    public static long fib(int n)
    {
        if (n <= 1)
        {
            return n;
        }
        else
        {
            return fib(n - 1) + fib(n - 2);
        }
    }

    public static long memoFib(int n)
    {
        if (n == 1 || n == 2)
        {
            return 1;
        }
        else
        {
            if(memory[n] != 0)
            {
                return memory[n];
            }
            else
            {
                memory[n] = memoFib(n-1) + memoFib(n-2);
                return memory[n];
            }
        }
    }
}
