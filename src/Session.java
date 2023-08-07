

import java.util.List;
import java.util.Map;

public class Session {
    //класът създава обект съдържащ сесиите и техните ID


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
