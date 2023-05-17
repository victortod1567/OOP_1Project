import java.io.IOException;
import java.util.*;

public class Main {

    static Integer id=0;
    static Integer currentSession;
    static Session session = new Session(new HashMap<>());
    static Map<Integer, List<Image>> backup=new HashMap<>();

    public static void main(String[] args) throws IOException {
        CommandLine commandLine=new CommandLine();
        commandLine.initLine();

    }
}