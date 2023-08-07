

public class Close {
    public void close() {
        Main.session.getSession().get(Main.currentSession).clear();
    }
}
