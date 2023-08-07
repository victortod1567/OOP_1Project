import java.util.List;

public class Info {
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
}
