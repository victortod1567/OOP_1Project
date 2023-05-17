import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PGM extends Image {

    private int maxGreyVal;

    public PGM(String fileName,String flag, int width, int height, int[][] pixels, int maxGreyVal) {
        super(fileName,flag, width, height, pixels);
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
        this.writePGM(getPixels(),"negPgm.pgm");
    }

    public void writePGM(int[][] pixels,String filename) throws IOException {
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

    public void rotate(String direction)
    {
        if (direction.equals("right"))
        {
            int[][] rotatedP=new int[this.getWidth()][this.getHeight()];
            for (int y=0;y<this.getHeight();y++)
            {
                for (int x=0;x<this.getWidth();x++)
                    rotatedP[x][this.getHeight()-y-1]=this.getPixels()[y][x];
            }
            this.setPixels(rotatedP);
        }

        else if (direction.equals("left"))
        {
            int[][] rotatedP=new int[this.getWidth()][this.getHeight()];
            for (int y=0;y<this.getHeight();y++)
            {
                for (int x=0;x<this.getWidth();x++)
                    rotatedP[this.getWidth()-x-1][y]=this.getPixels()[y][x];
            }
            this.setPixels(rotatedP);
        }
        else System.out.println("Unknown direction. Available: right,left");
    }

    public void monochrome()
    {
        int monochromeVal=this.maxGreyVal/2;
        int[][] pixels=this.getPixels();

        for (int row=0;row<this.getHeight();row++)
            for (int col=0;col<this.getWidth();col++)
            {
                int pixel=pixels[row][col];
                if (pixel<monochromeVal) pixels[row][col]=0;
                else pixels[row][col]=this.maxGreyVal;
            }

        this.setPixels(pixels);
    }

}
