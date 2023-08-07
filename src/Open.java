



import java.io.IOException;

public class Open {
    public void open(String fileName) throws IOException {
        try
        {
            Image img = Image.read(fileName);
            System.out.println("File "+fileName+" successfully loaded");
            Main.session.getSession().get(Main.id).add(img);
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

    }
}
