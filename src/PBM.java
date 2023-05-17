import java.io.*;

public class PBM extends Image {

    public PBM(String fileName,String flag, int width, int height, int[][] pixels) {
        super(fileName,flag, width, height, pixels);
    }


    public void invert()
    {
        int invertedPixels[][] = new int[this.getHeight()][this.getWidth()];

        for (int y = 0; y < this.getHeight(); y++) {
            for (int x = 0; x < this.getWidth(); x++) {
                if (this.getPixels()[y][x] == 0)
                    invertedPixels[y][x] = 1;
                else invertedPixels[y][x] = 0;
            }
        }
        this.setPixels(invertedPixels);
    }
    public void writePBM(int[][] pixels,String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write(getFlag()+"\n");
        writer.write(pixels[0].length + " " + pixels.length + "\n");
        for (int y = 0; y < pixels.length; y++) {
            for (int x = 0; x < pixels[y].length; x++) {
                writer.write((pixels[y][x] == 1 ? 0 : 1) + " ");
            }
            writer.write("\n");
        }

        writer.close();


    }

    public void rotate(String direction)
    {
        if (direction.equals("right")) {
            int[][] rotatedP = new int[this.getWidth()][this.getHeight()];
            for (int y = 0; y < this.getHeight(); y++) {
                for (int x = 0; x < this.getWidth(); x++)
                    rotatedP[x][this.getHeight() - y - 1] = this.getPixels()[y][x];
            }
            this.setPixels(rotatedP);
        }
        else if (direction.equals("left"))
        {
            int[][] rotatedP = new int[this.getWidth()][this.getHeight()];
            for (int y = 0; y < this.getHeight(); y++) {
                for (int x = 0; x < this.getWidth(); x++)
                    rotatedP[this.getWidth()-x-1][y] = this.getPixels()[y][x];
            }
            this.setPixels(rotatedP);
        }
        else System.out.printf("Unknown direction. Available: right,left");
        }
    }

