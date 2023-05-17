import java.io.IOException;
import java.util.*;

public class Commands {
    public void help() {
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

    public void open(String fileName) throws IOException {
        Image img = Image.read(fileName);
        System.out.println("File "+fileName+" successfully loaded");
        Main.session.getSession().get(Main.id).add(img);

    }

    public void close() {
        Main.session.getSession().get(Main.currentSession).clear();
    }


    public void exit()  {

        System.exit(0);

    }

    public void load(String line) throws IOException {
        try {
            Main.session.getSession().put(++Main.id, new ArrayList<>());
            Main.currentSession = Main.id;
            open(line);
            System.out.println("Session with ID:" + Main.currentSession + " started");
        }
        catch  (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void negative() throws IOException, CloneNotSupportedException {
        backUp();
        List<Image> imgs = Main.session.getSession().get(Main.currentSession);
        for (Image img : imgs) {
            img.invert();
        }

    }

    public void gray() throws IOException, CloneNotSupportedException {
        backUp();
        List<Image> imgs = Main.session.getSession().get(Main.currentSession);
        for (Image img : imgs) {
            if (img instanceof PPM)
            {((PPM) img).grayscale(((PPM) img).getRed(),((PPM) img).getGreen(),((PPM) img).getBlue());
                ((PPM) img).writePPM("ppmgray.ppm", img.getWidth(), img.getHeight(),  ((PPM) img).getRed(), ((PPM) img).getGreen(), ((PPM) img).getBlue(), ((PPM) img).getMaxGreyVal());

            }
        }

    }

    public void rotate(String direction) throws IOException, CloneNotSupportedException {
        backUp();
        List<Image> imgs = Main.session.getSession().get(Main.currentSession);
        for (Image img : imgs) {
            img.rotate(direction);
        }

    }

    public void mon() throws CloneNotSupportedException {
        backUp();
        List<Image> imgs = Main.session.getSession().get(Main.currentSession);
        for (Image img : imgs) {
            img.monochrome();
        }

    }
    public void save() throws IOException {
        try {
            List<Image> imgs = Main.session.getSession().get(Main.currentSession);
            for (Image img : imgs) {
                img.write();
            }
            System.out.println("Successfully saved!");
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void backUp() throws CloneNotSupportedException {
        Map<Integer, List<Image>> sessionCopy = new HashMap<>();
        for (Map.Entry<Integer, List<Image>> entry : Main.session.getSession().entrySet()) {
            int key = entry.getKey();
            List<Image> value = new ArrayList<>();
            for (Image image : entry.getValue()) {
                Image imageCopy = image.clone();
                value.add(imageCopy);
            }
            sessionCopy.put(key, value);
        }

        Main.backup = new HashMap<>(sessionCopy);
    }
    public void undo() throws CloneNotSupportedException {
        Map<Integer, List<Image>> backupCopy = new HashMap<>();
        for (Map.Entry<Integer, List<Image>> entry : Main.backup.entrySet()) {
            int key = entry.getKey();
            List<Image> value = new ArrayList<>();
            for (Image image : entry.getValue()) {
                Image imageCopy = image.clone();
                value.add(imageCopy);
            }
            backupCopy.put(key, value);
        }

        Main.session.setSession(backupCopy);

    }

    public void info()
    {
        try {
            System.out.println("Session ID:" + Main.currentSession);
            System.out.println("Files in session:");
            List<Image> imgs = Main.session.getSession().get(Main.currentSession);
            for (Image img : imgs) {
                System.out.println(img.getFileName());
            }
        }
        catch (NullPointerException e)
        {
            System.out.println("No valid sessions. Type help for additional info.");
        }
    }

    public void change(int id)
    {
        Set<Integer> ids=Main.session.getSession().keySet();
        if (ids.contains(id)==false) System.out.println("No session with such id!");
        for (Integer integer : ids) {
            if (integer==id) {
                Main.currentSession = id;
                break;
            }
        }
    }
}
