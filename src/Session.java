import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Session {

    private Map<Integer, List<Image>> session;

    public Session(Map<Integer, List<Image>> session) {
        this.session = session;
    }

    public void setSession(Map<Integer, List<Image>> session) {
        this.session = session;
    }

    public Map<Integer, List<Image>> getSession() {
        return session;
    }

}
