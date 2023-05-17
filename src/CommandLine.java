import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class CommandLine {

   Commands commands=new Commands();
    String[] readLine;

    public CommandLine() {
    }

    public void initLine() throws IOException {
    try {
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
                case "open":
                    commands.open(readLine[1]);
                    break;
                case "close":
                    commands.close();
                    break;
                case "negative":
                    commands.negative();
                    break;
                case "grayscale":
                    commands.gray();
                    break;
                case "rotate":
                    commands.rotate(readLine[1]);
                    break;
                case "monochrome":
                    commands.mon();
                    break;
                case "save":
                    commands.save();
                    break;
                case "undo":
                    commands.undo();
                    break;
                case "session_info":
                    commands.info();
                    break;
                case "changeSession":
                    commands.change(Integer.parseInt(readLine[1]));
                    break;
                case "exit":
                    commands.exit();
                    break;
                default:
                    System.out.println("Unknown command. Type help for additional info");
                    break;
                }


            }
        }

            catch (IOException e)
            {
                System.out.println(e.getMessage());
            } catch (CloneNotSupportedException e) {
        System.out.println(e.getMessage());
    }
    }
   }

