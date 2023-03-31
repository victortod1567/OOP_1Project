import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

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

    public Image open(String fileName) throws IOException {
        Image img=Image.read(fileName);
        Main.session.getSession().get(Main.currentSession).add(img);
        return img;
    }

    public void close()
    {
        Main.session.getSession().get(Main.currentSession).clear();
    }
    public void changeSession(int id)
    {
        Main.currentSession=id;
    }

    public void exit() throws IOException {
        PBM.invert((PBM) Main.session.getSession().get(Main.currentSession).get(0));
        System.exit(0);
    }
    public void load(String line) throws IOException {
        Main.session.getSession().put(++Main.id, new ArrayList<>());
        Main.currentSession=Main.id;
        Main.session.getSession().get(Main.id).add(open(line));
    }

}
