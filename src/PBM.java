import java.io.*;

public class PBM extends Image implements Cloneable{

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

    @Override
    public Image clone() throws CloneNotSupportedException {
        PBM copyPBM= (PBM) super.clone();
        int[][] clonedPixels = new int[this.getHeight()][this.getWidth()];
        for (int i = 0; i < this.getHeight(); i++) {
            clonedPixels[i] = this.getPixels()[i].clone();
        }
            copyPBM.setPixels(clonedPixels);

        return copyPBM;
    }


    @Override
    public Image collage(String direction,Image image1, Image image2, String fileName3) throws IOException {

        if (direction.equals("horizontal")) {
            int width1 = image1.getWidth();
            int width2 = image2.getWidth();
            int height = image1.getHeight();
            int totalWidth = width1 + width2;

            int[][] collagePixels = new int[height][totalWidth];

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width1; x++) {
                    collagePixels[y][x] = image1.getPixels()[y][x];
                }
            }

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width2; x++) {
                    collagePixels[y][x + width1] = image2.getPixels()[y][x];
                }
            }
            return new PBM(fileName3, this.getFlag(), totalWidth, height, collagePixels);
        }
        else if(direction.equals("vertical")) {

            int width = image1.getWidth();
            int height1 = image1.getHeight();
            int height2 = image2.getHeight();
            int totalHeight = height1 + height2;

            int[][] collagePixels = new int[totalHeight][width];

            for (int y = 0; y < height1; y++) {
                for (int x = 0; x < width; x++) {
                    collagePixels[y][x] = image1.getPixels()[y][x];
                }
            }

            for (int y = 0; y < height2; y++) {
                for (int x = 0; x < width; x++) {
                    collagePixels[y + height1][x] = image2.getPixels()[y][x];
                }
            }
            return new PBM(fileName3,this.getFlag(),width,totalHeight,collagePixels);
        }
        else throw new IllegalArgumentException("Unknown direction. Available: horizontal, vertical.");
        }
    }


