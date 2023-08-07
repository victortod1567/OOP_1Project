import java.io.IOException;
import java.util.Scanner;

public class CommandLine {
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
                        Help help=new Help();
                        help.help();
                        break;
                    case "load":
                        Load load=new Load();
                        load.load(readLine[1]);
                        break;
                    case "open":
                        Open open=new Open();
                        open.open(readLine[1]);
                        break;
                    case "close":
                        Close close=new Close();
                        close.close();
                        break;
                    case "negative":
                        Negative negative=new Negative();
                        negative.negative();
                        break;
                    case "grayscale":
                        Grayscale grayscale=new Grayscale();
                        grayscale.gray();
                        break;
                    case "rotate":
                        Rotate rotate=new Rotate();
                        rotate.rotate(readLine[1]);
                        break;
                    case "monochrome":
                        Monochrome monochrome=new Monochrome();
                        monochrome.mon();
                        break;
                    case "save":
                        Save save=new Save();
                        save.save();
                        break;
                    case "saveAs":
                        Save saveAs=new Save();
                        saveAs.saveAs(readLine[1]);
                        break;
                    case "undo":
                        Undo undo=new Undo();
                        undo.undo();
                        break;
                    case "session_info":
                        Info info=new Info();
                        info.info();
                        break;
                    case "changeSession":
                        Change change=new Change();
                        change.change(Integer.parseInt(readLine[1]));
                        break;
                    case "collage":
                        Collage collage=new Collage();
                        collage.collage(readLine[1],readLine[2],readLine[3],readLine[4]);
                        break;
                    case "exit":
                        Exit exit=new Exit();
                        exit.exit();
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

