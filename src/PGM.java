import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PGM extends Image implements Cloneable{

    private int maxGreyVal;

    public PGM(String fileName,String flag, int width, int height, int[][] pixels, int maxGreyVal) {
        super(fileName,flag, width, height, pixels);
        this.maxGreyVal=maxGreyVal;
    }

    public int getMaxGreyVal() {
        return maxGreyVal;
    }

    public void setMaxGreyVal(int maxGreyVal) {
        this.maxGreyVal = maxGreyVal;
    }

    @Override
    public Image clone() throws CloneNotSupportedException {
        PGM copyPGM= (PGM) super.clone();
        int[][] clonedPixels = new int[this.getHeight()][this.getWidth()];
        for (int i = 0; i < this.getHeight(); i++) {
            clonedPixels[i] = this.getPixels()[i].clone();
        }
        copyPGM.setPixels(clonedPixels);
        copyPGM.setMaxGreyVal(this.getMaxGreyVal());

        return copyPGM;
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

    @Override
    public Image collage(String direction, Image image1, Image image2, String fileName3) throws IOException {

        if (direction.equals("horizontal")) {
            int totalWidth = image1.getWidth() + image2.getWidth();
            int[][] collagePixels = new int[image1.getHeight()][totalWidth];

            for (int y = 0; y < image1.getHeight(); y++) {
                for (int x = 0; x < image1.getWidth(); x++) {
                    collagePixels[y][x] = image1.getPixels()[y][x];
                }
            }

            for (int y = 0; y < image2.getHeight(); y++) {
                for (int x = 0; x < image2.getWidth(); x++) {
                    collagePixels[y][x + image1.getWidth()] = image2.getPixels()[y][x];
                }
            }
            return new PGM(fileName3, image1.getFlag(), totalWidth, image1.getHeight(), collagePixels, this.maxGreyVal);
        }
        else if(direction.equals("vertical")) {
            int totalHeight=image1.getHeight() + image2.getHeight();
            int[][] collagePixels = new int[totalHeight][image1.getWidth()];

            for (int y = 0; y < image1.getHeight(); y++) {
                for (int x = 0; x < image1.getWidth(); x++) {
                    collagePixels[y][x] = image1.getPixels()[y][x];
                }
            }

            for (int y = 0; y < image2.getHeight(); y++) {
                for (int x = 0; x < image2.getWidth(); x++) {
                    collagePixels[y + image1.getHeight()][x] = image2.getPixels()[y][x];
                }
            }

            return new PGM(fileName3, image1.getFlag(), image1.getWidth(), totalHeight,collagePixels,this.maxGreyVal);
        }
        else throw new IllegalArgumentException("Unknown direction. Available: horizontal, vertical.");
    }
    }

