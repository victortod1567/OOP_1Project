import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Session {

    private Map<Integer, List<File>> session;

    public Session(Map<Integer, List<File>> session) {
        this.session = session;
    }

    public Map<Integer, List<File>> getSession() {
        return session;
    }
}
