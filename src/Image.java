import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public abstract class Image {

    private int width;
    private int height;
    private int[][] pixels;

    public Image(int width, int height, int[][] pixels) {
        this.width = width;
        this.height = height;
        this.pixels = pixels;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[][] getPixels() {
        return pixels;
    }

    public static Image read(String fileName) throws IOException
    {
        BufferedReader reader=new BufferedReader(new FileReader("./resources/"+fileName));
        String currentLine= reader.readLine();
        if (currentLine.equals("P1") || currentLine.equals("P4"))
            return PBM.read(fileName);
        return null;
    }

    //public abstract void write(Image img, String fileName);
}