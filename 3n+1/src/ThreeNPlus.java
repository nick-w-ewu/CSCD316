import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class ThreeNPlus
{
	private static int count;
	public static void main(String[] args)
	{
		Scanner input = null;
		int i, j;
		if(args.length == 0)
		{
			input = openInputFile("input.txt");
		}
		else
		{
			input = openInputFile(args[0]);
		}

		while(input.hasNext())
		{
			i = input.nextInt();
			j = input.nextInt();
			int cycle = threeNHelper(i, j);
			System.out.printf("%d %d %d\n", i, j, cycle);
		}
	}

	private static int threeNHelper(int i, int j)
	{
		int[] cycleLengths = new int[(j - i)+1];
		for(int k = 0, m = i; k < cycleLengths.length; k++, m++)
		{
			count = 0;
			threeNPlus1(m);
			cycleLengths[k] = count;
		}
		Arrays.sort(cycleLengths);
		return cycleLengths[cycleLengths.length-1];
	}

	private static void threeNPlus1(int n)
	{
		int currentN;
		if(n == 1)
		{
			count++;
			return;
		}
		if(n < 1)
		{
			return;
		}

		if(n%2 == 0)
		{
			count++;
			currentN = n/2;
			threeNPlus1(currentN);
		}
		else
		{
			count++;
			currentN = 3*n + 1;
			threeNPlus1(currentN);
		}
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
			System.out.println("File" + fileName + " was not found. The program will accept console input if you wish to continue");
			fileScanner = new Scanner(System.in);
		}

		return fileScanner;
	}
}
