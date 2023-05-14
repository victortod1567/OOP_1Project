import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PPM extends Image{

    private int maxGreyVal;
    private int[] red;
    private int[] green;
    private int[] blue;

    public PPM(String flag, int width, int height, int maxGreyVal, int[] red, int[] green, int[] blue) {
        super(flag, width, height);
        this.maxGreyVal = maxGreyVal;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getMaxGreyVal() {
        return maxGreyVal;
    }

    public int[] getRed() {
        return red;
    }

    public int[] getGreen() {
        return green;
    }

    public int[] getBlue() {
        return blue;
    }

    public void writePPM(String fileName, int width, int height, int[] red, int[] green, int[] blue, int maxGreyVal) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

        writer.write("P3");
        writer.newLine();
        writer.write("# new img");
        writer.newLine();
        writer.write(width + " " + height);
        writer.newLine();
        writer.write(maxGreyVal + "");
        writer.newLine();

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                int pixelIndex = row * width + col;
                writer.write(red[pixelIndex] + " ");
                writer.write(green[pixelIndex] + " ");
                writer.write(blue[pixelIndex] + " ");
            }
            writer.newLine();
        }
    }
}
