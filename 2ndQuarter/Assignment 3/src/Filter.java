import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by nicho on 4/20/2016.
 */
public class Filter implements FilenameFilter
{
    private String userExtention;
    public Filter(String s)
    {
        this.userExtention = s;
    }

    @Override
    public boolean accept(File dir, String name)
    {
        String[] extention = name.split("\\.");
        if(extention[1].equals(this.userExtention))
        {
            return true;
        }
        return false;
    }
}
