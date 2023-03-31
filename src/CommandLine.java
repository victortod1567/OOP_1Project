import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class CommandLine {

   Commands commands=new Commands();
    String[] readLine;

    public CommandLine() {
    }

    public void initLine() throws IOException {

       Scanner userInput = new Scanner(System.in);
       while (true) {
           readLine = userInput.nextLine().split(" ");
           switch (readLine[0]) {
               case "help":
                   commands.help();
                   break;
               case "load":
                    commands.load(readLine[1]);
                   break;
               case "changeSession":
                   commands.changeSession(Integer.parseInt(readLine[1]));
                   break;
               case "open":
                   commands.open(readLine[1]);
                   break;
               case "close":
                   commands.close();
                   break;
               case "exit":
                   commands.exit();
                   break;

           }
       }
   }
}
