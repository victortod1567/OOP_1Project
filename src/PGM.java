import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PGM extends Image {

    private int maxGreyVal;

    public PGM(String flag, int width, int height, int[][] pixels, int maxGreyVal) {
        super(flag, width, height, pixels);
        this.maxGreyVal=maxGreyVal;
    }

    public void invert() throws IOException {
        int width=this.getWidth();
        int height=this.getHeight();
        int[][] pixels=this.getPixels();

        int [][] negativePixels=new int[height][width];
        for (int y=0;y<height;y++)
        {
            for (int x=0;x<width;x++)
            {
                negativePixels[y][x]=maxGreyVal-pixels[y][x];
            }
        }
        this.setPixels(negativePixels);
        this.write(getPixels(),"negPgm.pgm");
    }

    public void write(int[][] pixels,String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write("P2\n");
        writer.write(pixels[0].length + " " + pixels.length + "\n");
        writer.write(maxGreyVal + "\n");
        for (int[] row : pixels) {
            for (int pixel : row) {
                writer.write(pixel + " ");
            }
            writer.write("\n");
        }
        writer.close();
    }

}
