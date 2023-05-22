import java.io.IOException;
import java.util.*;

public class Main {

    static Integer id=0;        //брояч за ID на сесиите
    static Integer currentSession;  //съдържа ID на текущата сесия
    static Session session = new Session(new HashMap<>());  //създава обект Session с Map, в който се записват сесиите
    static Map<Integer, List<Image>> backup=new HashMap<>(); //съдържа backup на текущата сесия

    public static void main(String[] args) throws IOException {
        CommandLine commandLine=new CommandLine();  //инициализира командния ред
        commandLine.initLine();

    }
}