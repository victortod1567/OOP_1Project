import java.io.File;

public class Commands {
    public void help()
    {
        System.out.println("Available commands:" +
                "\nload             Loads a new session" +
                "\nopen             Opens a file. If such doesn't exist an empty one is created" +
                "\nclose            Closes a file" +
                "\nsave             Saves a file" +
                "\nsaveAs           Saves file as" +
                "\nchangeSession    Changes the current user session" +
                "\nhelp             Lists available commands" +
                "\nexit             Exits the program");
    }

    public File open(String fileName)
    {
        File file=new File(fileName);
        return file;
    }

    public void changeSession(int id)
    {
        Main.currentSession=id;
    }


}
