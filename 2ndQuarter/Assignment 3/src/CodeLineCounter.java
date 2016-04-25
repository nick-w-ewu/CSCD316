import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by nicho on 4/20/2016.
 * Test first two characters and last two characters for multi-line comments
 * If a line has the end of a multi-line comment followed by code it is counted;
 */


public class CodeLineCounter
{
    static int lineCount;
    static int fileCount;
    static boolean startedMultiLine = false;

    public static void main(String[] args)
    {
        String path = "C:\\Users\\nicho_000\\Google Drive\\Eastern\\CSCD 316\\testing";

        File directory = new File(path);
        Filter filter = new Filter("java");
        File[] files = directory.listFiles(filter);
        if (files == null)
        {
            System.out.println("Error reading directory, or there were no files with the specified extension. Now exiting...");
            System.exit(-1);
        }
        processFiles(files);
        System.out.println(fileCount + " files processes with " + lineCount + " lines");
    }

    private static void processFiles(File[] files)
    {
        for (File file : files)
        {
            if (!file.isDirectory())
            {
                processFile(file);
                fileCount++;
            }
        }
    }

    private static void processFile(File file)
    {
        try
        {
            Scanner fin = new Scanner(file);
            String line;
            while (fin.hasNextLine())
            {
                line = fin.nextLine();
                if (countedLine(line))
                {
                    lineCount++;
                }
            }
        }
        catch (FileNotFoundException e)
        {

        }
    }

    private static boolean countedLine(String line)
    {
        boolean toReturn = true;
        if (line.equals("") || startedMultiLine)
        {
            return false;
        }
        if (line.length() <= 2)
        {
            if (line.equals("{") || line.equals("}"))
            {
                return true;
            }
            if (line.equals("//"))
            {
                return false;
            }
            if (line.equals("/*"))
            {
                startedMultiLine = true;
                return false;
            }
            if(line.equals("*/"))
            {
                startedMultiLine = false;
                return false;
            }
        }
        else
        {
            String test = line.substring(0, 2);
            if (test.equals("//"))
            {
                toReturn = false;
            }
            if (test.equals("/*"))
            {
                toReturn = false;
                startedMultiLine = true;
            }
            if (test.equals("*/"))
            {
                startedMultiLine = false;
                if (line.length() > 2 && countedLine(line.substring(2)))
                {
                    toReturn = true;
                }
            }
            String end = line.substring(line.length() - 2);
            if (end.equals("/*"))
            {
                startedMultiLine = true;
            }
            else if (end.equals("*/"))
            {
                startedMultiLine = false;
            }
        }
        return toReturn;
    }
}
