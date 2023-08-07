



import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Save {
    public void save() throws IOException {
        try {
            List<Image> imgs = Main.session.getSession().get(Main.currentSession);
            for (Image img : imgs) {
                img.write(img.getFileName());
            }
            System.out.println("Successfully saved!");
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void saveAs(String fileName) throws IOException {
        System.out.println("Enter the name of the file to save as: ");
        Scanner userInput = new Scanner(System.in);
        String name=userInput.nextLine();
        List<Image> imgs = Main.session.getSession().get(Main.currentSession);
        for (Image img : imgs) {
            if (img.getFileName().equals(name)) {
                img.write(fileName);
                System.out.println("Successfully saved!");
            }
            else System.out.println("No such file found!");
        }
    }
}
