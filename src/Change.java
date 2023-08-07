
import java.util.Set;

public class Change {
    public void change(int id)
    {
        Set<Integer> ids= Main.session.getSession().keySet();
        if (ids.contains(id)==false) System.out.println("No session with such id!");
        for (Integer integer : ids) {
            if (integer==id) {
                Main.currentSession = id;
                break;
            }
        }
    }
}
