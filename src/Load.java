

import java.io.IOException;
import java.util.ArrayList;

public class Load {
    public void load(String line) {
        try {
            Main.session.getSession().put(++Main.id, new ArrayList<>());
            Main.currentSession = Main.id;
            Open open=new Open();
            open.open(line);
            System.out.println("Session with ID:" + Main.currentSession + " started");
        }
        catch  (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
