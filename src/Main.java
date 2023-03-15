import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static Integer id=0;
    static Integer currentSession;
    public static void main(String[] args) {
        Session session = new Session(new HashMap<>());
        Commands commands = new Commands();
        String[] readLine;
        Scanner userInput = new Scanner(System.in);
        while (true) {
            readLine = userInput.nextLine().split(" ");
            switch (readLine[0]) {
                case "help":
                    commands.help();
                    break;
                case "load":
                    session.getSession().put(++id, new ArrayList<>());
                    session.getSession().get(id).add(commands.open(readLine[1]));
                    currentSession=id;
                    break;
                case "changeSession":
                    commands.changeSession(Integer.parseInt(readLine[1]));
                    break;
                case "open":
                    session.getSession().get(currentSession).add(commands.open(readLine[1]));
                    break;
                case "close":
                    session.getSession().get(currentSession).clear();
                    break;
                case "exit":
                    System.exit(0);
                    break;

            }
        }
    }
}