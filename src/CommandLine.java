import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class CommandLine {
    //инициализира командния ред
   Commands commands=new Commands();
    String[] readLine;

    public CommandLine() {
    }

    public void initLine() throws IOException {
    try {
        Scanner userInput = new Scanner(System.in); //скенер за входни данни от потребителя
        while (true) {
            readLine = userInput.nextLine().split(" ");//масив, съдържаш командата и съответните и аргументи ако има такива
            //при разпозната команда се извиква съответния за него метод, в противен случай се извежда съобщение за
            //неразпозната команда
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
                case "saveAs":
                    commands.saveAs(readLine[1]);
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
                case "collage":
                    commands.collage(readLine[1],readLine[2],readLine[3],readLine[4]);
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

